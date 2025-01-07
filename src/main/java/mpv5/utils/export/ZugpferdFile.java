/*
 *  This file is part of YaBS.
 *
 *      YaBS is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      YaBS is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.utils.export;

import com.lowagie.text.FontFactory;
import fr.opensagres.odfdom.converter.pdf.PdfConverter;
import fr.opensagres.odfdom.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.odt.ODTConstants;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.itext.extension.font.ITextFontRegistry;
import fr.opensagres.xdocreport.template.FieldExtractor;
import fr.opensagres.xdocreport.template.FieldsExtractor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import fr.opensagres.xdocreport.template.formatter.NullImageBehaviour;
import mpv5.db.common.DatabaseObject;
import mpv5.globals.Constants;
import mpv5.globals.GlobalSettings;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Notificator;
import mpv5.utils.files.FileDirectoryHandler;
import mpv5.utils.images.YabsQRCodeGenerator;
import mpv5.utils.xdocreport.YabsFontFactoryImpl;
import mpv5.utils.xdocreport.YabsODTPreprocessor;
import org.mustangproject.*;
import org.mustangproject.ZUGFeRD.Profiles;
import org.mustangproject.ZUGFeRD.ZUGFeRD2PullProvider;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * New templating system
 */
public class ZugpferdFile extends Exportable {

    private static final long serialVersionUID = 1L;

    public ZugpferdFile(String pathToFile) throws Exception {
        super(pathToFile);
        if (!exists()) {
            try {
                createNewFile();
            } catch (IOException ex) {
                Log.Debug(ex);
            }
        }
    }

    @Override
    public void run() {
        try {
            final File target = getTarget();
            Log.Debug(this, "run zugpferd run: " + this + " to file " + target);
            mpv5.YabsViewProxy.instance().setWaiting(true);

            DatabaseObject dob = ((Export) getTemplate().getData()).getDob();
            mpv5.db.objects.Item item = null;
            if(dob instanceof mpv5.db.objects.Item
            && ((mpv5.db.objects.Item) dob).__getInttype() == Constants.TYPE_INVOICE){
                item = (mpv5.db.objects.Item) dob;
            }

            if(item ==null) throw new RuntimeException("Not an invoice: " +  dob);

            Invoice i = new Invoice()
                    .setDueDate(item.__getDatetodo())
                    .setIssueDate(item.__getDateadded())
                    .setDeliveryDate(item.__getDateadded())
                    .setSender(new TradeParty("Test company","teststr","55232","teststadt","DE")
                            .addTaxID("DE4711")
                            .addVATID("DE0815")
                            .setContact(new Contact("Hans Test","+49123456789","test@example.org"))
                            .addBankDetails(new BankDetails("DE12500105170648489890","COBADEFXXX")))
                    .setRecipient(item.getContact().asZugpferdTradeparty())
                    .setReferenceNumber("991-01484-64")//leitweg-id
                    .setNumber(item.getCname());

            Arrays.stream(item.getSubitems()).forEachOrdered(it->{
                i.addItem(it.asZugpferdSubitem());
            });

            ZUGFeRD2PullProvider zf2p = new ZUGFeRD2PullProvider();
            zf2p.setProfile(Profiles.getByName("XRechnung"));
            zf2p.generateXML(i);
            String theXML = new String(zf2p.getXML());
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(getTarget()));
                writer.write(theXML);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Log.Debug(this, "Replaced Fields of xml file: " + this + " to " + target);
        } catch (Exception ex) {
            Log.Debug(ex);
        } finally {
            mpv5.YabsViewProxy.instance().setWaiting(false);
        }
    }


}
