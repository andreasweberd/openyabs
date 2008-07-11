/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mp4.klassen.objekte;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import mp3.database.util.Query;
import mp3.classes.utils.Log;
import mp3.classes.layer.PostenTableModel;
import mp4.utils.datum.DateConverter;

/**
 *
 * @author anti43
 */
public class Angebot extends mp3.classes.layer.Things implements mp3.classes.interfaces.Structure {

    private String Angebotnummer = "";
    private Integer KundenId = 0;
    private boolean rechnung = false;
    private boolean auftrag = false;
    private Date Datum = new Date();
    private Date bisDatum = new Date();
    private AngebotPosten[] bp;
    private Query query;
    private String[][] products;
    private List labelsOfGetAllWithD;

    public Angebot(Query query) {
        super(query.clone(TABLE_ORDERS));
        this.query = query;
    }

    /**
     * 
     * @param query
     * @param id
     */
    public Angebot(Query query, Integer id) {
        super(query.clone(TABLE_ORDERS));
        this.id = Integer.valueOf(id);
        this.explode(this.selectLast(ALL, ID, id.toString(), true));
        this.query = query;
        bp = getProducts(query);
    }

    public Angebot expose() {

        System.out.println(collect());
        return this;
    }

    public String getBisFDatum() {
          return DateConverter.getDefDateString(getBisDatum());
    }

    public String getFDatum() {
        return DateConverter.getDefDateString(getDatum());
    }

    public boolean hasId() {
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }
//  public String getFDatum() {
//      return DateConverter.getDefDateString(getDatum());
//    }

    /**
     * 
     * @return id,Anzahl,Posten,Mehrwertsteuer,Nettopreis,Bruttopreis
     */
    public Object[][] getProductlistAsArray() {

        DecimalFormat n = new DecimalFormat("0.00");

        String lab = "id,Anzahl,Posten,Mehrwertsteuer,Nettopreis,Bruttopreis";
        Object[][] nstr = new Object[products.length][6];
//Class[] types = new Class[]{java.lang.Integer.class, java.lang.Double.class, java.lang.String.class,
//        java.lang.Double.class, java.lang.Double.class, java.lang.Double.class};
//Log.Debug(prods);
        try {

            for (int i = 0; i < nstr.length; i++) {
                try {
                    nstr[i][0] = Integer.valueOf(products[i][0]);
                    nstr[i][1] = Double.valueOf(products[i][2]);
                    nstr[i][2] = String.valueOf(products[i][3]);

                    nstr[i][3] = Double.valueOf(products[i][5]);


                    nstr[i][4] = Double.valueOf(products[i][4]);

                    nstr[i][5] = Double.valueOf(
                            (Double.valueOf(products[i][4]) *
                            (((Double.valueOf(products[i][5])) / 100) + 1)));

                } catch (NumberFormatException numberFormatException) {
                    nstr[i][4] = Double.valueOf("0");
                    nstr[i][5] = Double.valueOf("0");

//                    Popup.error(numberFormatException.getMessage(), Popup.ERROR);
                }
            }


        } catch (Exception exception) {
            Log.Debug(exception);
//               Popup.error(exception.getMessage(), Popup.ERROR);
        }

        return nstr;

    }

    private void explode(String[] select) {

        this.setOrdernummer(select[1]);
        this.setKundenId(Integer.valueOf(select[2]));

        this.setDatum(DateConverter.getDate(select[3]));
        if (select[4].equals("1")) {
            this.setAuftrag(true);
        }
        this.setBisDatum(DateConverter.getDate(select[5]));
        if (select[6].equals("1")) {
            this.setRechnung(true);
        }

    }

    private String collect() {



        String str = "";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getOrdernummer() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + this.getKundenId() + "(;;,;;)";
//        str = str + this.getDeleted();
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getDatum() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        if (this.isAuftrag()) {
            str = str + "1" + "(;;,;;)";
        } else {
            str = str + "0" + "(;;,;;)";
        }
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getBisDatum() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        if (this.hasRechnung()) {
            str = str + "1";
        } else {
            str = str + "0";
        }

        return str;


    }

    public void save() {


        if (id > 0 && !isSaved) {
            Log.Debug(this.collect());
            this.update(TABLE_ORDERS_FIELDS, this.collect(), id.toString());

            isSaved = true;
        } else if (id == 0 && !isSaved) {
            this.insert(TABLE_ORDERS_FIELDS, this.collect());

            this.id = this.getMyId();

            isSaved = true;
        } else {

//            mp3.layer.Popup.warn("no_data_to_save", Popup.WARN);
        }
    }

    public String getid() {
        return id.toString();
    }

    public String getOrdernummer() {
        return Angebotnummer;
    }

    public void setOrdernummer(String Ordernummer) {
        this.Angebotnummer = Ordernummer;
        this.isSaved = false;
    }

    public Integer getKundenId() {
        return KundenId;
    }

    public void setKundenId(Integer KundenId) {
        this.KundenId = KundenId;
        this.isSaved = false;
    }

    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date Datum) {
        this.Datum = Datum;
        this.isSaved = false;
    }

    public PostenTableModel getProductlistAsTableModel() {

        DecimalFormat n = new DecimalFormat("0.00");

        String lab = "id,Anzahl,Posten,Mehrwertsteuer,Nettopreis,Bruttopreis";
        Object[][] nstr = new Object[products.length][6];

        try {

            for (int i = 0; i < nstr.length; i++) {

                nstr[i][0] = Integer.valueOf(products[i][0]);
                nstr[i][1] = Double.valueOf(products[i][2]);
                nstr[i][2] = String.valueOf(products[i][3]);

                nstr[i][3] = Double.valueOf(products[i][5]);

                try {
                    nstr[i][4] = Double.valueOf(products[i][4]);

                    nstr[i][5] = Double.valueOf(
                            (Double.valueOf(products[i][4]) *
                            (((Double.valueOf(products[i][5])) / 100) + 1)));

                } catch (NumberFormatException numberFormatException) {
                    nstr[i][4] = Double.valueOf("0");
                    nstr[i][5] = Double.valueOf("0");

//                    Popup.error(numberFormatException.getMessage(), Popup.ERROR);
                }
            }


        } catch (Exception exception) {
            Log.Debug(exception);
//               Popup.error(exception.getMessage(), Popup.ERROR);
        }

        return new PostenTableModel(nstr, lab.split(","));

    }

    private Integer getMyId() {

        String[] str = this.selectLast("id", "Auftragnummer", this.getOrdernummer(), false);

        return Integer.valueOf(str[0]);
    }

    private AngebotPosten[] getProducts(Query query) {

        Query q = query.clone(TABLE_ORDERS_DATA);

        String[] wher = {"auftragid", this.getId().toString(), ""};

        products = q.select(ALL, wher);
        AngebotPosten[] prof = null;
//
//        for (int t = 0; t < str.length; t++) {
//
//            prof[t] = new BillProducts(query, str[0][t]);
//        }
        return prof;


    }

    public Integer getNextBillNumber() {
        return query.getNextIndex("auftragnummer");
    }

//    public BillProduct[] getBp() {
//        return bp;
//    }
    /**
     * 
     * @return
     */
    public String[][] getAllWithDepencies() {

        Query q = query.clone(TABLE_ORDERS);

        String[][] prods = q.select(ALL, null, TABLE_CUSTOMERS, "kundenid");

        setLabelsOfAllWithDepencies(q);
        return prods;

    }

    /**
     * 
     * @param table1fields
     * @return 
     */
    public String[][] getWithDepencies(String table1fields) {
        Query q = query.clone(TABLE_ORDERS);

        String[][] prods = q.select(table1fields, null, TABLE_CUSTOMERS, "kundenid");

        setLabelsOfAllWithDepencies(q);
        return prods;
    }

    public void setLabelsOfAllWithDepencies(Query q) {

        labelsOfGetAllWithD = q.getLabels();

    }

    /**
     * 
     * @return 
     */
    public String[] getLabelsOfAllWithDepencies() {

        String[] stray = new String[labelsOfGetAllWithD.size()];

        for (int i = 0; i < labelsOfGetAllWithD.size(); i++) {

            stray[i] = (String) labelsOfGetAllWithD.get(i);

        }

        return stray;

    }

    public String[][] getAll() {

        Query q = query.clone(TABLE_ORDERS);

        String[][] prods = q.select(ALL, null);

        return prods;
    }

    public boolean isAuftrag() {
        return auftrag;
    }

    public void setAuftrag(boolean auftrag) {
        this.auftrag = auftrag;
    }

    public Date getBisDatum() {
        return bisDatum;
    }

    public void setBisDatum(Date bisDatum) {
        this.bisDatum = bisDatum;
    }

    public void setRechnung(boolean rechnung) {
        this.rechnung = rechnung;
    }

    public boolean hasRechnung() {
        return rechnung;
    }
}
