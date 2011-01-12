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
package mpv5.handler;

import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.Formattable;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.ReturnValue;
import mpv5.db.objects.Contact;
import mpv5.db.objects.Expense;
import mpv5.db.objects.Item;
import mpv5.db.objects.Product;
import mpv5.db.objects.Revenue;
import mpv5.globals.GlobalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.usermanagement.MPSecurityManager;

/**
 *
 */
public class FormatHandler {

    public static final int TYPE_BILL = 0;
    public static final int TYPE_OFFER = 1;
    public static final int TYPE_ORDER = 2;
    public static final int TYPE_CONTACT = 3;
    public static final int TYPE_CUSTOMER = 4;
    public static final int TYPE_MANUFACTURER = 5;
    public static final int TYPE_SUPPLIER = 6;
    public static final int TYPE_PRODUCT = 7;
    public static final int TYPE_SERVICE = 8;
    public static final int TYPE_EXPENSE = 9;
    public static final int TYPE_REVENUE = 10;

    public static enum TYPES implements MPEnum {

        TYPE_BILL(FormatHandler.TYPE_BILL, Messages.TYPE_BILL.getValue()),
        TYPE_OFFER(FormatHandler.TYPE_OFFER, Messages.TYPE_OFFER.getValue()),
        TYPE_ORDER(FormatHandler.TYPE_ORDER, Messages.TYPE_ORDER.getValue()),
        TYPE_CONTACT(FormatHandler.TYPE_CONTACT, Messages.TYPE_CONTACT.getValue()),
        TYPE_CUSTOMER(FormatHandler.TYPE_CUSTOMER, Messages.TYPE_CUSTOMER.getValue()),
        TYPE_MANUFACTURER(FormatHandler.TYPE_MANUFACTURER, Messages.TYPE_MANUFACTURER.getValue()),
        TYPE_SUPPLIER(FormatHandler.TYPE_SUPPLIER, Messages.TYPE_SUPPLIER.getValue()),
        TYPE_PRODUCT(FormatHandler.TYPE_PRODUCT, Messages.TYPE_PRODUCT.getValue()),
        TYPE_SERVICE(FormatHandler.TYPE_SERVICE, Messages.TYPE_SERVICE.getValue()),
        TYPE_REVENUE(FormatHandler.TYPE_REVENUE, Messages.TYPE_REVENUE.getValue()),
        TYPE_EXPENSE(FormatHandler.TYPE_EXPENSE, Messages.TYPE_EXPENSE.getValue());
        int ids;
        String names;

        TYPES(int id, String name) {
            ids = id;
            names = name;
        }

        @Override
        public Integer getId() {
            return ids;
        }

        @Override
        public String getName() {
            return names;
        }
    }
    public static String INTEGERPART_IDENTIFIER = "{0,number,000000}";
    private DatabaseObject source = null;
    private Integer startCount = null;
    public static MessageFormat DEFAULT_FORMAT = new MessageFormat(INTEGERPART_IDENTIFIER);
    /**
     * This string identifies potential start values from the format string. Use as
     * START_VALUE_IDENTIFIERstartvalueSTART_VALUE_IDENTIFIERformat
     */
    public static String START_VALUE_IDENTIFIER = "@SV@";

    /**
     * Determines the format type of the given {@link DatabaseObject}
     * @param obj
     * @return An int value representing the format type
     */
    public synchronized static int determineType(DatabaseObject obj) {
        if (obj instanceof Item) {
            if (((Item) obj).__getInttype() == Item.TYPE_BILL) {
                return TYPE_BILL;
            } else if (((Item) obj).__getInttype() == Item.TYPE_OFFER) {
                return TYPE_OFFER;
            } else if (((Item) obj).__getInttype() == Item.TYPE_ORDER) {
                return TYPE_ORDER;
            }
        } else if (obj instanceof Contact) {
            if (((Contact) obj).__getIscustomer()) {
                return TYPE_CUSTOMER;
            } else if (((Contact) obj).__getIsmanufacturer()) {
                return TYPE_MANUFACTURER;
            } else if (((Contact) obj).__getIssupplier()) {
                return TYPE_SUPPLIER;
            } else {
                return TYPE_CONTACT;
            }
        } else if (obj instanceof Product) {
            if (((Product) obj).__getInttype() == Product.TYPE_PRODUCT) {
                return TYPE_PRODUCT;
            } else {
                return TYPE_SERVICE;
            }
        } else if (obj instanceof Revenue) {
            return TYPE_REVENUE;
        } else if (obj instanceof Expense) {
            return TYPE_EXPENSE;
        }
        return -1;
    }

    /**
     *
     * @param forObject
     */
    public FormatHandler(DatabaseObject forObject) {
        this.source = forObject;
    }

    @Override
    public String toString() {
        return "Format: " + getFormat().format(43) + " for " + source + " (" + determineType(source) + ")";
    }

    private FormatHandler() {
    }

    /**
     *
     * @return
     */
    public synchronized MessageFormat getFormat() {
        QueryCriteria c = new QueryCriteria();
        c.addAndCondition("usersids", mpv5.db.objects.User.getCurrentUser().__getIDS());
        c.addAndCondition("inttype", determineType(source));
        try {
            Object[][] frm = QueryHandler.instanceOf().clone(Context.getFormats()).select("cname, ids", c);
            if (frm.length > 0) {
                String val = frm[frm.length - 1][0].toString();
                try {
                    int id = Integer.valueOf(frm[frm.length - 1][1].toString());

                    if (val.startsWith(START_VALUE_IDENTIFIER)) {
                        startCount = Integer.valueOf(val.split(START_VALUE_IDENTIFIER)[1]);
                        val = val.split(START_VALUE_IDENTIFIER)[2];
                        QueryHandler.instanceOf().clone(Context.getFormats()).update("cname", id, val);
                        Log.Debug(this, "Setting start count to: " + startCount);
                    } 
                } catch (Exception numberFormatException) {
                    Log.Debug(this, numberFormatException);
                    return DEFAULT_FORMAT;
                }
                try {
                    MessageFormat mf =  new MessageFormat(val);
                    Log.Debug(this, "Format found: " + mf.toPattern());
                    return mf;
                } catch (Exception e) {
                    Log.Debug(this, e);
                    return DEFAULT_FORMAT;
                }
            } else {
                Log.Debug(this, "Format not found, using default format instead!");
                return DEFAULT_FORMAT;
            }
        } catch (Exception ex) {
            Log.Debug(ex);
            Log.Debug(this, "Format not found, using default format instead!");
            return DEFAULT_FORMAT;
        }
    }
    /**
     * Contains all formattable Contexts
     */
    public static List<Context> FORMATTABLE_CONTEXTS = new Vector<Context>(Arrays.asList(new Context[]{
                Context.getContact(), Context.getCustomer(), Context.getManufacturer(), 
                Context.getSupplier(), Context.getProduct(), Context.getItem(),
                Context.getExpense(), Context.getRevenue(), Context.getOffer(),
                Context.getOrder(), Context.getInvoice(), Context.getCompany()
            }));

    /**
     * Fetches the next number from the database
     * @param format
     * @return
     */
    public synchronized int getNextNumber(MessageFormat format) {

        if (startCount == null) {
            int newN = 0;
            DatabaseObject forThis = source;
            if (FORMATTABLE_CONTEXTS.contains(forThis.getContext())) {

                String query = "";
                if (forThis.getContext().equals(Context.getItem())
                        || forThis.getContext().equals(Context.getInvoice())
                        || forThis.getContext().equals(Context.getOffer())
                        || forThis.getContext().equals(Context.getOrder())) {
                    query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE inttype ="
                            + ((Item) forThis).__getInttype() + ")";
                } else if (forThis.getContext().equals(Context.getProduct())) {
                    query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE inttype ="
                            + ((Product) forThis).__getInttype() + ")";
                } else if (forThis.getContext().equals(Context.getContact())
                        || forThis.getContext().equals(Context.getCompany())
                        || forThis.getContext().equals(Context.getContactsCompanies())
                        || forThis.getContext().equals(Context.getCustomer())
                        || forThis.getContext().equals(Context.getSupplier())
                        || forThis.getContext().equals(Context.getManufacturer())) {
                    if (((Contact) forThis).__getIscustomer()) {
                        query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE iscustomer = 1)";
                    } else if (((Contact) forThis).__getIsmanufacturer()) {
                        query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE ismanufacturer = 1)";
                    } else if (((Contact) forThis).__getIssupplier()) {
                        query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE issupplier = 1)";
                    } else {
                        query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + " WHERE issupplier = 0  AND ismanufacturer = 0 AND iscustomer = 0)";
                    }
                } else {
                    query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE ids = (SELECT MAX(ids) from " + forThis.getDbIdentity() + ")";
                }

                ReturnValue val = QueryHandler.getConnection().freeQuery(
                        query, MPSecurityManager.VIEW, null);

                if (val.hasData()) {
                    Log.Debug(FormatHandler.class, "Last number found: " + val.getData()[0][0]);
                    try {
                        newN = ((Formattable) forThis).getFormatHandler().getIntegerPartOf(format, val.getData()[0][0].toString());
                    } catch (Exception e) {
                        Log.Debug(e);
                    }
                    Log.Debug(FormatHandler.class, "Counter part: " + newN);
                    return getNextNumber(format, newN);
                } else {
                    return 1;
                }
            } else {
                throw new UnsupportedOperationException("FormatHandler#getNextNumber is not defined for " + forThis.getContext());
            }
        } else {
            int tmp = startCount.intValue();
            startCount = null;
            Log.Debug(FormatHandler.class, "Startcount: " + tmp);
            return tmp;
        }
    }

    private synchronized int getNextNumber(MessageFormat format, int lastNumber) {
        DatabaseObject forThis = source;

        String query = "";
        String cnumber = toString(format, lastNumber + 1);
        if (forThis.getContext().equals(Context.getItem())
                || forThis.getContext().equals(Context.getInvoice())
                || forThis.getContext().equals(Context.getOffer())
                || forThis.getContext().equals(Context.getOrder())) {
            query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE cnumber = '" + cnumber + "' AND inttype ="
                    + ((Item) forThis).__getInttype();
        } else if (forThis.getContext().equals(Context.getProduct())) {
            query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE cnumber = '" + cnumber + "' AND inttype ="
                    + ((Product) forThis).__getInttype();
        } else if (forThis.getContext().equals(Context.getContact())
                || forThis.getContext().equals(Context.getCompany())
                || forThis.getContext().equals(Context.getContactsCompanies())
                || forThis.getContext().equals(Context.getCustomer())
                || forThis.getContext().equals(Context.getSupplier())
                || forThis.getContext().equals(Context.getManufacturer())) {
            
                query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE cnumber = '" + cnumber + "'";
            
        } else {
            query = "SELECT cnumber FROM " + forThis.getDbIdentity() + " WHERE cnumber = '" + cnumber + "'";
        }

        Log.Debug(FormatHandler.class, "Checking : " + cnumber);
        ReturnValue val2 = QueryHandler.getConnection().freeQuery(
                query, MPSecurityManager.VIEW, null);
        if (val2.hasData()) {
            Log.Debug(FormatHandler.class, "Already existing..: " + val2.getData()[0][0]);
            return getNextNumber(format, lastNumber + 1);
        } else {
            int nu = lastNumber + 1;
            Log.Debug(FormatHandler.class, "Not existing yet : " + cnumber + ", returning next one: " + nu);
            return nu;
        }
    }

    /**
     * Formats a given number by the determined number format, <br/>if the {@link setStartCount(Integer) } has not been set.
     * Returns the defined start value then.
     * @param format
     * @param number
     * @return A formatted number
     */
    public synchronized String toString(MessageFormat format, int number) {
        String x = VariablesHandler.parse(format.format(new Object[]{number}), source);
        if(x.length() == 0) {
            x = "0000" + number;
        }
        return x;
    }

    /**
     * Returns the user defined (if defined) String representation of the parent object
     * @return A formatted number
     */
    public synchronized String toUserString() {

        String s = mpv5.db.objects.User.getCurrentUser().getProperties().getProperty("saveformat");
        if (s != null && s.length() > 0) {
            if (s.contains("/")) {
                s = s.substring(s.lastIndexOf("/") + 1);
            }
        } else {
            s = source.toString();
        }


        return VariablesHandler.parse(s, source);
    }

    /**
     * @return the type
     */
    public int getType() {
        return determineType(source);
    }

    /**
     * @return the startCount
     */
    public Integer getStartCount() {
        return startCount;
    }

    /**
     * @param startCount the startCount to set
     */
    public void setStartCount(Integer startCount) {
        this.startCount = startCount;
    }

//    /**
//     * @param format the format to set
//     */
//    public void setFormat(MessageFormat format) {
//        this.format = format;
//    }
//
//    /**
//     * @param formatPattern the format to set, as String pattern
//     */
//    public void setFormat(String formatPattern) {
////        Pattern escaper = Pattern.compile("(['{])");
//        formatPattern = VariablesHandler.parse(formatPattern, source);
////        formatPattern = escaper.matcher(formatPattern).replaceAll("''$1");
////        Log.Debug(this, formatPattern);
//        this.format = new MessageFormat(formatPattern);
//    }
    /**
     *
     * @param string
     * @return
     */
    private synchronized int getIntegerPartOf(MessageFormat format, String string) {

        int startindex = 0;
        String prop = GlobalSettings.getProperty(format.toPattern() + "_startposition");
        if (prop != null && !prop.equals("null")) {
            try {
                startindex = Integer.valueOf(prop);
            } catch (NumberFormatException numberFormatException) {
                Log.Debug(numberFormatException);
            }
        }

        try {
            Number n = null;
            MessageFormat f;
            try {
                Log.Debug(this, format.toPattern());
                f = new MessageFormat((VariablesHandler.parse(format.toPattern(), source)));
                n = (Number) f.parse(string, new ParsePosition(startindex))[0];
                Log.Debug(this, "Pattern: " + f.toPattern() + " for String: " + string);
            } catch (Exception e) {
                //Its 0?
//                Log.Debug(this, e);
            }
            if (n == null) {
                n = 0;
            }
            return n.intValue();
        } catch (Exception ex) {
            Log.Debug(ex);
            return 0;
        }
    }

    /**
     * Returns the next determined number String
     * @return
     */
    public synchronized String next() {
        MessageFormat format = getFormat();
        return toString(format, getNextNumber(format));
    }
}
