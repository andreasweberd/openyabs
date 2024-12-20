package mpv5.utils.export;

import dtaus.DTAus;
import dtaus.Konto;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.objects.*;
import mpv5.globals.Messages;
import mpv5.handler.TemplateHandler;
import mpv5.handler.VariablesHandler;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.utils.date.DateConverter;
import mpv5.utils.files.FileDirectoryHandler;
import mpv5.utils.files.FileReaderWriter;
import mpv5.utils.jobs.Waitable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EmailBatch extends Exportable implements Waitable {

    public EmailBatch(HashMap<String, Object> map) {
        super(FileDirectoryHandler.getTempFile("export-" + DateConverter.getTodayDBDate(), "email-log.txt").getAbsolutePath());
        setData(map);
    }

    @Override
    void run() {
        try {

            Log.Debug(this, "run: ");
            mpv5.YabsViewProxy.instance().setWaiting(true);
            HashMap<String, Object> datas = getData();

            List<String> log = new ArrayList<>();
            Template batchTemplate = null;
            int lastGroupId = -1;
            MailMessage m = null;

            for (Iterator<String> it = datas.keySet().iterator(); it.hasNext(); ) {
                Item dataOwner = (Item) datas.get(it.next());

                if (m == null) {
                    try {
                        QueryCriteria c = new QueryCriteria("usersids", mpv5.db.objects.User.getCurrentUser().__getIDS());
                        m = (MailMessage) Popup.SelectValue(DatabaseObject.getObjects(Context.getMessage(), c), Messages.SELECT_A_TEMPLATE);
                    } catch (Exception ex) {
                        Log.Debug(Export.class, ex.getMessage());
                        log.add(dataOwner.getCname() + "->" + Messages.NO_MAIL_TEMPLATE_DEFINED + "\n");
                    }
                }

                if (lastGroupId != dataOwner.templateGroupIds() || batchTemplate == null) {
                    lastGroupId = dataOwner.templateGroupIds();
                    batchTemplate = TemplateHandler.loadTemplate(dataOwner.templateGroupIds(), dataOwner.__getInttype());
                }

                if (TemplateHandler.isLoaded((long) dataOwner.templateGroupIds(), dataOwner.__getInttype())) {
                    try {
                        Contact cont = (Contact) (Contact.getObject(Context.getContact(), dataOwner.__getContactsids()));
                        Export.mail(batchTemplate, dataOwner, cont, log, m);
                    } catch (NodataFoundException ex) {
                        Log.Debug(ex);
                    }
                } else {
                    log.add(dataOwner.getCname() + ": " + Messages.NO_TEMPLATE_LOADED + " (" + mpv5.db.objects.User.getCurrentUser() + ")\n");
                }
            }

            FileReaderWriter w = new FileReaderWriter(this);
            w.writeOnce(log.toString());
            Popup.notice(log, Messages.DONE);

        } catch (Exception ex) {
            Log.Debug(ex);
        } finally {
            mpv5.YabsViewProxy.instance().setWaiting(false);
        }
    }

    @Override
    public Exception waitFor() {
        try {
            run();
        } catch (Exception e) {
            return e;
        }
        return null;
    }
}
