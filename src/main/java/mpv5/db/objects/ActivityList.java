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
package mpv5.db.objects;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.swing.JComponent;

import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.Formattable;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.Templateable;
import mpv5.globals.Constants;
import mpv5.globals.GlobalSettings;
import mpv5.globals.Messages;
import mpv5.handler.FormatHandler;
import mpv5.handler.TemplateHandler;
import mpv5.logging.Log;
import mpv5.ui.panels.ActivityConfirmationPanel;
import mpv5.utils.export.ODTFile2;
import mpv5.utils.images.MPIcon;
import mpv5.utils.numberformat.FormatNumber;
import mpv5.utils.text.TypeConversion;

/**
 *
 *
 */
public class ActivityList extends DatabaseObject implements Formattable, Templateable {

    private static final long serialVersionUID = 1L;
    private String cnumber;
    private int contactsids;
    private int orderids;
    private BigDecimal totalamount = BigDecimal.ZERO;
    private boolean isbilled;
    private FormatHandler formatHandler;

    public ActivityList() {
        setContext(Context.getActivityList());
    }

    @Override
    public JComponent getView() {
        return new ActivityConfirmationPanel();
    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return new MPIcon("/images/22/folder_documents.png");
    }

    @Override
    public boolean save(boolean b) {
        if (getCname().length() == 0) {
            setCname("<unnamed>");
        }
        return super.save(b);
    }

    public int __getContactsids() {
        return contactsids;
    }

    public void setContactsids(int contactsids) {
        this.contactsids = contactsids;
    }

    public int __getOrderids() {
        return orderids;
    }

    public void setOrderids(int orderids) {
        this.orderids = orderids;
    }

    public BigDecimal __getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public String __getCNumber() {
        return cnumber;
    }

    public void setCNumber(String cnumber) {
        this.cnumber = cnumber;
    }

    public boolean __getIsBilled() {
        return isbilled;
    }

    public void setIsBilled(boolean billed) {
        this.isbilled = billed;
    }

    /**
     * @return the formatHandler
     */
    @Override
    public synchronized FormatHandler getFormatHandler() {
        if (formatHandler == null) {
            formatHandler = new FormatHandler(this);
        }
        return formatHandler;
    }

    @Override
    public int templateType() {
        return Constants.TYPE_ACTIVITY;
    }

    @Override
    public int templateGroupIds() {
        return __getGroupsids();
    }

    @Override
    public void defineFormatHandler(FormatHandler handler) {
        formatHandler = handler;
    }

    @Override
    public void ensureUniqueness() {
        setCNumber(getFormatHandler().next());
    }

    @Override
    public java.util.Map<String, Object> resolveReferences(java.util.Map<String, Object> map) {
        Contact dbo;
        if (map.containsKey("contactsids")) {
            try {
                try {
                    dbo = (Contact) DatabaseObject.getObject(Context.getContact(), Integer.valueOf(map.get("contactsids").toString()));
                    map.put("contact", dbo);
                    //map.putAll(dbo.getValues4());
                    map.remove("contactsids");
                } catch (NodataFoundException ex) {
                    map.put("contact", null);
                    Log.Debug(this, ex.getMessage());
                }
            } catch (NumberFormatException numberFormatException) {
                //already resolved?
            }
        }

        List<ActivityListSubItem> data;
        List<String[]> data2;
        ArrayList<String[]> list = new ArrayList<>();

        try {
            data = DatabaseObject.getReferencedObjects(this, Context.getActivityListItems(), new ActivityListSubItem());
            Collections.sort(data, ActivityListSubItem.ORDER_COMPARATOR);

            for (int i = 0; i < data.size(); i++) {
                ActivityListSubItem t = data.get(i);
                list.add(t.toStringArray(TemplateHandler.loadTemplate(this.templateGroupIds(), this.templateType())));
                if (GlobalSettings.getBooleanProperty("org.openyabs.exportproperty.pdftable", false)) {
                    data2 = t.getValues3();
                    for (int j = 0; j < data2.size(); j++) {
                        String[] strings = data2.get(j);
                        map.put("subactivity" + i + "." + strings[0].toLowerCase(), strings[1]);
                    }
                }
            }
        } catch (NodataFoundException ex) {
            Log.Debug(this, ex.getMessage());
        }

        map.put(ODTFile2.KEY_TABLE + "1", list);

        //date format localization
        if (mpv5.db.objects.User.getCurrentUser().getProperties().hasProperty("item.date.locale")) {
            Locale l = null;
            try {
                l = TypeConversion.stringToLocale(mpv5.db.objects.User.getCurrentUser().getProperties().getProperty("item.date.locale"));
            } catch (Exception e) {
            }
            if (l != null) {
                map.put("dateadded", DateFormat.getDateInstance(DateFormat.MEDIUM, l).format(__getDateadded()));
            } else {
                Log.Debug(this, "Error while using item.date.locale");
            }
        }
        map.put("grosvaluef", FormatNumber.formatLokalCurrency(__getTotalamount()));
        return super.resolveReferences(map);
    }

    public SubItem getDataForInvoice() {
        SubItem s = SubItem.getDefaultItem();
        s.setQuantityvalue(BigDecimal.ONE);
        s.setDescription(Messages.ActivityList_toSubItem.toString() + this.__getCname());
        s.setExternalvalue(this.__getTotalamount());
        return s;
    }

    public List<ActivityListSubItem> getPositions() throws NodataFoundException {
        
        List<ActivityListSubItem> data = DatabaseObject.getReferencedObjects(this, Context.getActivityListItems(), new ActivityListSubItem());
        Collections.sort(data, ActivityListSubItem.ORDER_COMPARATOR);

        return data;
    }
}
