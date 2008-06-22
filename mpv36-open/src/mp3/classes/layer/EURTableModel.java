/*
 * 
 *  *  This file is part of MP by anti43 /GPL.
 *  *  
 *  *      MP is free software: you can redistribute it and/or modify
 *  *      it under the terms of the GNU General Public License as published by
 *  *      the Free Software Foundation, either version 3 of the License, or
 *  *      (at your option) any later version.
 *  *  
 *  *      MP is distributed in the hope that it will be useful,
 *  *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 * *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *      GNU General Public License for more details.
 *  *  
 *  *      You should have received a copy of the GNU General Public License
 *  *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 *  
 */
package mp3.classes.layer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mp3.database.util.Query;
import mp3.classes.interfaces.Constants;
import mp3.classes.interfaces.ProtectedStrings;
import mp3.classes.interfaces.Strings;
import mp3.classes.interfaces.Structure;
import mp3.classes.objects.bill.Bill;
import mp3.classes.utils.Formater;
import mp3.classes.utils.Log;

/**
 *
 * @author anti43
 */
public class EURTableModel implements ProtectedStrings, Constants, Strings, Structure {

    private Object[][] data;
    String[] head = new String[]{"Beschreibung", "Wert"};
    Double vorsteuerausgabe = 0d;
    Double gesamtausgabe = 0d;
    Double vorsteuereinnahme = 0d;
    Double gesamteinnahme = 0d;
    Double konto1110 = 0d; // Waren, Rohstoffe und Hilfsstoffe einschl. der Nebenkosten",
    Double konto1120 = 0d; // Bezogene Leistungen (z.B. Fremdleistungen)Double konto,
    Double konto1130 = 0d; // Ausgaben f�r eigenes Personal Double konto,
    Double konto1140 = 0d; // Aufwendungen f�r geringwertige Wirtschaftsg�terDouble konto,
    Double konto1150 = 0d; // Miete / Pacht f�r Gesch�ftsr�ume und betrieblich genutzte Grundst�ckeDouble konto,
    Double konto1160 = 0d; // Sonstige Aufwendungen f�r betrieblich genutzte Grundst�ckeDouble konto,
    Double konto1170 = 0d; // Abziehbare Aufwendungen f�r ein h�usliches ArbeitszimmerDouble konto,
    Double konto1180 = 0d; // Reisekosten,Aufwendungen f�r doppelte Haushaltsf�hrungDouble konto,
    Double konto1190 = 0d; // Geschenke � abziehbarDouble konto,
    Double konto1200 = 0d; // Geschenke � nicht abziehbarDouble konto,
    Double konto1210 = 0d; // Bewirtung � abziehbarDouble konto,
    Double konto1220 = 0d; // Bewirtung � nicht abziehbarDouble konto,
    Double konto1230 = 0d; // �brige BetriebsausgabenDouble konto,
    Double konto1240 = 0d; // Fortbildung und FachliteraturDouble konto,
    Double konto1250 = 0d; // Rechts- und Steuerberatung, Buchf�hrungDouble konto,
    Double konto1260 = 0d; // Porto, Telefon, B�romaterialDouble konto,
    Double konto1270 = 0d; // An das Finanzamt gezahlte und ggf. verrechnete UmsatzsteuerDouble konto,
    Double konto2100 = 0d; // Betriebseinnahmen als umsatzsteuerlicher KleinunternehmerDouble konto,
    Double konto2110 = 0d; // Umsatzsteuerpflichtige BetriebseinnahmenDouble konto,
    Double konto2120 = 0d; // Sonstige Sach-, Nutzungs- und LeistungsentnahmenDouble konto,
    Double konto2130 = 0d; // Private Kfz-NutzungDouble konto,
    Double konto2140 = 0d; // Vom Finanzamt erstattete und ggf. verrechnete Umsatzsteuer";
  
    public EURTableModel(String year) {

        data = new Object[KONTEN_GRUPPEN.length][2];

        for (int i = 0; i < KONTEN_GRUPPEN.length; i++) {

            data[i][0] = KONTEN_GRUPPEN[i];

        }


        Query q = null;
        String[][] ausgaben = q.select("kontenid, preis, tax, datum", null);
        Integer x = 0;
        double net = 0d;
        try {
            q = QueryClass.instanceOf().clone(TABLE_DUES);
        } catch (Exception ex) {
           Popup.warn(ex.getMessage(), Popup.ERROR);
        }



        for (int h = 0; h < ausgaben.length; h++) {
             x = 0;
                    net = 0d;
            if (ausgaben[h][3] != null && ausgaben[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(ausgaben[h][0]);
                    if(ausgaben[h][1]!=null && Double.valueOf(ausgaben[h][1])>0){
                    net = (Double.valueOf(ausgaben[h][1]) / ((Double.valueOf(ausgaben[h][2]) / 100) + 1));
                    vorsteuerausgabe = vorsteuerausgabe + (Double.valueOf(ausgaben[h][1]) - net);

                    gesamtausgabe = gesamtausgabe + Double.valueOf(ausgaben[h][1]);}

                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }


                switch (x) {


                    case 1:
                    case 2:
                    case 3:
                        konto1110 = konto1110 + net;
                        break;

                    case 4:
                    case 5:
                    case 6:
                        konto1120 = konto1120 + net;
                        break;

                    case 7:
                        konto1130 = konto1130 + net;
                        break;
                    case 8:
                        konto1140 = konto1140 + net;
                        break;
                    case 9:
                        konto1150 = konto1150 + net;
                        break;
                    case 10:
                        konto1160 = konto1160 + net;
                        break;
                    case 11:
                        konto1170 = konto1170 + net;
                        break;

                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        konto1180 = konto1180 + net;
                        break;

                    case 16:
                        konto1190 = konto1190 + net;
                        break;
                    case 17:
                        konto1200 = konto1200 + net;
                        break;
                    case 18:
                        konto1210 = konto1210 + net;
                        break;

                    case 19:
                        konto1220 = konto1220 + net;
                        break;

                    case 20:
                    case 21:
                    case 22:
                        konto1230 = konto1230 + net;
                        break;
                    case 23:
                        konto1240 = konto1240 + net;
                        break;
                    case 24:
                        konto1250 = konto1250 + net;
                        break;
                    case 25:
                        konto1260 = konto1260 + net;
                        break;
                    case 26:
                        konto1270 = konto1270 + net;
                        break;
                }
            }
        }

        data[0][1] = Formater.formatMoney(konto1110);
        data[1][1] = Formater.formatMoney(konto1120);
        data[2][1] = Formater.formatMoney(konto1130);
        data[3][1] = Formater.formatMoney(konto1140);
        data[4][1] = Formater.formatMoney(konto1150);
        data[5][1] = Formater.formatMoney(konto1160);
        data[6][1] = Formater.formatMoney(konto1170);
        data[7][1] = Formater.formatMoney(konto1180);
        data[8][1] = Formater.formatMoney(konto1190);
        data[9][1] = Formater.formatMoney(konto1200);
        data[10][1] = Formater.formatMoney(konto1210);
        data[11][1] = Formater.formatMoney(konto1220);
        data[12][1] = Formater.formatMoney(konto1230);
        data[13][1] = Formater.formatMoney(konto1240);
        data[14][1] = Formater.formatMoney(konto1250);
        data[15][1] = Formater.formatMoney(konto1260);
        data[16][1] = Formater.formatMoney(konto1270);

        data[18][1] = Formater.formatMoney(vorsteuerausgabe);

        data[20][1] = Formater.formatMoney(gesamtausgabe);


        try {
            q = QueryClass.instanceOf().clone(TABLE_INCOME);
        } catch (Exception exception) {
            Popup.warn(exception.getMessage(), Popup.ERROR);
        }

        String[][] einnahmen = q.select("kontenid, preis, tax, datum", null);

        for (int h = 0; h < einnahmen.length; h++) {
             x = 0;
             net = 0d;
            if (einnahmen[h][3] != null && einnahmen[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(einnahmen[h][0]);
                    if(einnahmen[h][1]!=null && Double.valueOf(einnahmen[h][1])>0){
                    net = (Double.valueOf(einnahmen[h][1]) / ((Double.valueOf(einnahmen[h][2]) / 100) + 1));
                    vorsteuereinnahme = vorsteuereinnahme + (Double.valueOf(einnahmen[h][1]) - net);

                    gesamteinnahme = gesamteinnahme + Double.valueOf(einnahmen[h][1]);}

                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }

                switch (x) {


                    case 27:
                        konto2100 = konto2100 + net;
                        break;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                        konto2110 = konto2110 + net;
                        break;

                    case 33:
                    case 34:
                    case 35:
                        konto2120 = konto2120 + net;
                        break;
                    case 36:
                        konto2130 = konto2130 + net;
                        break;
                    case 37:
                        konto2140 = konto2140 + net;
                        break;
                }


            }
        }


        einnahmen = new Bill(q).getPaidEUR();

        for (int h = 0; h < einnahmen.length; h++) {
             x = 0;
                    net = 0d;
            if (einnahmen[h][3] != null && einnahmen[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(einnahmen[h][0]);
                    if(einnahmen[h][1]!=null && Double.valueOf(einnahmen[h][1])>0){
                    net = (Double.valueOf(einnahmen[h][1]) / ((Double.valueOf(einnahmen[h][2]))));
                    vorsteuereinnahme = vorsteuereinnahme + (Double.valueOf(einnahmen[h][1]) - net);
                    gesamteinnahme = gesamteinnahme + Double.valueOf(einnahmen[h][1]);}

                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }


                switch (x) {


                    case 27:
                        konto2100 = konto2100 + net;
                        break;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                        konto2110 = konto2110 + net;
                        break;

                    case 33:
                    case 34:
                    case 35:
                        konto2120 = konto2120 + net;
                        break;
                    case 36:
                        konto2130 = konto2130 + net;
                        break;
                    case 37:
                        konto2140 = konto2140 + net;
                        break;
                }


            }
        }
        
        data[22][1] = Formater.formatMoney(konto2100);
        data[23][1] = Formater.formatMoney(konto2110);
        data[24][1] = Formater.formatMoney(konto2120);
        data[25][1] = Formater.formatMoney(konto2130);
        data[26][1] = Formater.formatMoney(konto2140);
        
        data[28][1] = Formater.formatMoney(vorsteuereinnahme);
        
        data[30][1] = Formater.formatMoney(gesamteinnahme);
        
        data[33][1] = Formater.formatMoney(gesamteinnahme);
        data[34][1] = Formater.formatMoney(gesamtausgabe);
        data[35][1] = Formater.formatMoney(gesamteinnahme - gesamtausgabe);

    }

    public EURTableModel(String year, String month) {

       year = month+"."+year;
        
       data = new Object[KONTEN_GRUPPEN.length][2];

        for (int i = 0; i < KONTEN_GRUPPEN.length; i++) {

            data[i][0] = KONTEN_GRUPPEN[i];

        }


        Query q = null;

        String[][] ausgaben = q.select("kontenid, preis, tax, datum", null);
        Integer x = 0;
        double net = 0d;
        try {
            q = QueryClass.instanceOf().clone(TABLE_DUES);
        } catch (Exception ex) {
            Popup.warn(ex.getMessage(), Popup.ERROR);
        }



        for (int h = 0; h < ausgaben.length; h++) {
             x = 0;
                    net = 0d;
            if (ausgaben[h][3] != null && ausgaben[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(ausgaben[h][0]);
                    
                    if(ausgaben[h][1]!=null && Double.valueOf(ausgaben[h][1])>0){
                    net = (Double.valueOf(ausgaben[h][1]) / ((Double.valueOf(ausgaben[h][2]) / 100) + 1));
                    vorsteuerausgabe = vorsteuerausgabe + (Double.valueOf(ausgaben[h][1]) - net);

                    gesamtausgabe = gesamtausgabe + Double.valueOf(ausgaben[h][1]);}

                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }


                switch (x) {


                    case 1:
                    case 2:
                    case 3:
                        konto1110 = konto1110 + net;
                        break;

                    case 4:
                    case 5:
                    case 6:
                        konto1120 = konto1120 + net;
                        break;

                    case 7:
                        konto1130 = konto1130 + net;
                        break;
                    case 8:
                        konto1140 = konto1140 + net;
                        break;
                    case 9:
                        konto1150 = konto1150 + net;
                        break;
                    case 10:
                        konto1160 = konto1160 + net;
                        break;
                    case 11:
                        konto1170 = konto1170 + net;
                        break;

                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        konto1180 = konto1180 + net;
                        break;

                    case 16:
                        konto1190 = konto1190 + net;
                        break;
                    case 17:
                        konto1200 = konto1200 + net;
                        break;
                    case 18:
                        konto1210 = konto1210 + net;
                        break;

                    case 19:
                        konto1220 = konto1220 + net;
                        break;

                    case 20:
                    case 21:
                    case 22:
                        konto1230 = konto1230 + net;
                        break;
                    case 23:
                        konto1240 = konto1240 + net;
                        break;
                    case 24:
                        konto1250 = konto1250 + net;
                        break;
                    case 25:
                        konto1260 = konto1260 + net;
                        break;
                    case 26:
                        konto1270 = konto1270 + net;
                        break;
                }
            }
        }

        data[0][1] = Formater.formatMoney(konto1110);
        data[1][1] = Formater.formatMoney(konto1120);
        data[2][1] = Formater.formatMoney(konto1130);
        data[3][1] = Formater.formatMoney(konto1140);
        data[4][1] = Formater.formatMoney(konto1150);
        data[5][1] = Formater.formatMoney(konto1160);
        data[6][1] = Formater.formatMoney(konto1170);
        data[7][1] = Formater.formatMoney(konto1180);
        data[8][1] = Formater.formatMoney(konto1190);
        data[9][1] = Formater.formatMoney(konto1200);
        data[10][1] = Formater.formatMoney(konto1210);
        data[11][1] = Formater.formatMoney(konto1220);
        data[12][1] = Formater.formatMoney(konto1230);
        data[13][1] = Formater.formatMoney(konto1240);
        data[14][1] = Formater.formatMoney(konto1250);
        data[15][1] = Formater.formatMoney(konto1260);
        data[16][1] = Formater.formatMoney(konto1270);

        data[18][1] = Formater.formatMoney(vorsteuerausgabe);

        data[20][1] = Formater.formatMoney(gesamtausgabe);


        try {
            q = QueryClass.instanceOf().clone(TABLE_INCOME);
        } catch (Exception exception) {
            Popup.warn(exception.getMessage(), Popup.ERROR);
        }
        String[][] einnahmen = q.select("kontenid, preis, tax, datum", null);

        for (int h = 0; h < einnahmen.length; h++) {
             x = 0;
                    net = 0d;
            if (einnahmen[h][3] != null && einnahmen[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(einnahmen[h][0]);
                    if(einnahmen[h][1]!=null && Double.valueOf(einnahmen[h][1])>0){
                    net = (Double.valueOf(einnahmen[h][1]) / ((Double.valueOf(einnahmen[h][2]) / 100) + 1));
                    vorsteuereinnahme = vorsteuereinnahme + (Double.valueOf(einnahmen[h][1]) - net);

                    gesamteinnahme = gesamteinnahme + Double.valueOf(einnahmen[h][1]);}

                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }


                switch (x) {


                    case 27:
                        konto2100 = konto2100 + net;
                        break;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                        konto2110 = konto2110 + net;
                        break;

                    case 33:
                    case 34:
                    case 35:
                        konto2120 = konto2120 + net;
                        break;
                    case 36:
                        konto2130 = konto2130 + net;
                        break;
                    case 37:
                        konto2140 = konto2140 + net;
                        break;
                }


            }
        }

       


        einnahmen = new Bill(q).getPaidEUR();

        for (int h = 0; h < einnahmen.length; h++) {
             x = 0;
                    net = 0d;
            if (einnahmen[h][3] != null && einnahmen[h][3].endsWith(year)) {
                try {

                    x = Integer.valueOf(einnahmen[h][0]);
                  if(einnahmen[h][1]!=null && Double.valueOf(einnahmen[h][1])>0){
                    net = (Double.valueOf(einnahmen[h][1]) / ((Double.valueOf(einnahmen[h][2]))));
                    Log.Debug(net+" = "+einnahmen[h][1]+" /" + einnahmen[h][2]);
                    vorsteuereinnahme = vorsteuereinnahme + (Double.valueOf(einnahmen[h][1]) - net);
                   
                    gesamteinnahme = gesamteinnahme + Double.valueOf(einnahmen[h][1]);}
                   
                } catch (NumberFormatException ex) {

                    x = 0;
                    net = 0d;
                    Log.Debug(ex);
                }


                switch (x) {


                    case 27:
                        konto2100 = konto2100 + net;
                        break;
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                        konto2110 = konto2110 + net;
                        break;

                    case 33:
                    case 34:
                    case 35:
                        konto2120 = konto2120 + net;
                        break;
                    case 36:
                        konto2130 = konto2130 + net;
                        break;
                    case 37:
                        konto2140 = konto2140 + net;
                        break;
                }


            }
        }
        
        data[22][1] = Formater.formatMoney(konto2100);
        data[23][1] = Formater.formatMoney(konto2110);
        data[24][1] = Formater.formatMoney(konto2120);
        data[25][1] = Formater.formatMoney(konto2130);
        data[26][1] = Formater.formatMoney(konto2140);
        data[28][1] = Formater.formatMoney(vorsteuereinnahme);
        data[30][1] = Formater.formatMoney(gesamteinnahme);
        data[33][1] = Formater.formatMoney(gesamteinnahme);
        data[34][1] = Formater.formatMoney(gesamtausgabe);
        data[35][1] = Formater.formatMoney(gesamteinnahme - gesamtausgabe);

    }

    public DefaultTableModel getModel() {
        return new DefaultTableModel(data, head);
    }
}

