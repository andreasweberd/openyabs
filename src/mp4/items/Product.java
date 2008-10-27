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
package mp4.items;

import mp4.items.handler.ProductGroupHandler;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import mp4.interfaces.Countable;
import mp4.datenbank.verbindung.ConnectionHandler;

import mp4.datenbank.verbindung.Query;
import mp4.items.handler.NumberFormatHandler;
import mp4.logs.Log;
import mp4.utils.datum.DateConverter;

/**
 *
 * @author anti43
 */
public class Product extends mp4.items.Things implements mp4.datenbank.installation.Tabellen, Countable {

    private String Ean = "00000000";
    private String Name = "";
    private String Bestellnr = "";
    private String Herstellernr = "";
    private String Liefernr = "";
    private String Bestelldatum = "";
    private Double Bestellmenge = 0d;
    private Double Lagermenge = 0d;
    private Date Datum = new Date();
    private String url = "";
    private String Text = "";
    private Double VK = 0d;
    private Double EK = 0d;
    private Integer SteuersatzId = 1;
    private Integer WarengruppenId = 0;
    private Query query;
    public boolean isvalid = false;

    private Hersteller hersteller = new Hersteller(1);
    private Lieferant lieferant = new Lieferant(1);
    
    private ProductImage image = new ProductImage();
    private URL ProductImageURL = null;


    public void destroy() {
        if (!readonly) {
        this.delete(this.id);
        this.id = 0;}
    }

    public Product() {
        super(ConnectionHandler.instanceOf().clone(TABLE_PRODUCTS));
        lieferant = new Lieferant();
        this.query = ConnectionHandler.instanceOf();
        nfh = new NumberFormatHandler(this, new Date());

    }

    public Product(String text, Double parseDezimal) {
        super(ConnectionHandler.instanceOf().clone(TABLE_PRODUCTS));
        this.setName(text);
        this.setVK(parseDezimal);
        nfh = new NumberFormatHandler(this, new Date());
    }

    public Product(Query query) {
        super(query.clone(TABLE_PRODUCTS));
        lieferant = new Lieferant(query);
        this.query = query;
        nfh = new NumberFormatHandler(this, new Date());
    }

    /**
     * 
     * @param id
     */
    public Product(Integer id) {
        super(ConnectionHandler.instanceOf().clone(TABLE_PRODUCTS));
        this.id = Integer.valueOf(id);
        readonly = !lock();
        try {
            this.explode(this.selectLast("*", "id", id.toString(), true));
        } catch (Exception ex) {
             Log.Debug(this,ex);
        }
//
//        if (!this.getLieferantenId().equals(0)) {
//            this.lieferant = new Lieferant(this.getLieferantenId());
//        }
//
//        if (!this.getHerstellerId().equals(0)) {
//            this.hersteller = new Hersteller(this.getHerstellerId());
//        }

        this.isvalid = true;
        this.query = ConnectionHandler.instanceOf();
        this.nfh = new NumberFormatHandler(this, new Date());

        fetchImage();

    }

    public boolean hasImage() {
        if (getImage() != null && getImage().hasImage()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTaxID(int taxID) {
        this.setSteuersatzId(taxID);
    }

    private ProductImage fetchImage() {
        this.image = new ProductImage().searchImage(this.getId());
        return image;
    }

    public ProductImage getImage() {
        if (image != null) {
            return image;
        } else {
            return new ProductImage();
        }
    }

    public URI getImagePath() {
        if (image != null) {
            return this.image.getURI();
        }
        return null;
    }

    public boolean isValid() {
        if (this.id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
//        this.HerstellerId = hersteller.getId();
    }

    private void explode(String[] select) {

        this.setNummer(select[1]);
        this.setName(select[2]);

        this.setText(select[3]);
        this.setVK(Double.valueOf(select[4]));
        this.setEK(Double.valueOf(select[5]));

        this.setSteuersatzId(Integer.valueOf(select[6]));
        this.setHersteller(new Hersteller(Integer.valueOf(select[7])));
        this.setLieferant(new Lieferant(Integer.valueOf(select[8])));
        this.setWarengruppenId(Integer.valueOf(select[9]));

        this.setDatum(DateConverter.getDate(select[10]));
        this.setUrl(select[11]);
        this.setEan(select[12]);

        this.setBestellnr(select[12 + 1]);
        this.setHerstellernr(select[14]);
        this.setLiefernr(select[15]);
        this.setBestelldatum(select[16]);
        this.setBestellmenge(Double.valueOf(select[17]));
        this.setLagermenge(Double.valueOf(select[18]));

    }

    public String[][] getAll() {

        Query q = query.clone(TABLE_PRODUCTS);

        String[][] str = q.selectFreeQuery("SELECT produkte.id, produkte.Produktnummer AS Nummer,produkte.Name,produkte.text," +
                "produkte.VK,produkte.EK,steuersaetze.wert,hersteller.firma AS Hersteller,Lieferanten.firma AS Lieferant," +
                "Warengruppenid,produkte.Datum,produkte.Url,produkte.EAN FROM produkte " +
                "LEFT OUTER JOIN  steuersaetze ON produkte.steuersatzid = steuersaetze.id " +
                "LEFT OUTER JOIN  lieferanten ON produkte.lieferantenid = lieferanten.id " +
                "LEFT OUTER JOIN  hersteller ON produkte.herstellerid = hersteller.id " +
                "LEFT OUTER JOIN  warengruppengruppen ON produkte.warengruppenid = warengruppengruppen.id " +
                "WHERE produkte.deleted = 0", null);

        return str;
    }

    private String collect() {
        String str = "";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getProduktNummer() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getName() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getText() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + this.getVK() + "(;;,;;)";
        str = str + this.getEK() + "(;;,;;)";
        str = str + this.getSteuersatzId() + "(;;,;;)";
        str = str + this.getHersteller().getId() + "(;;,;;)";
        str = str + this.getLieferant().getId() + "(;;,;;)";
        str = str + this.getWarengruppenId() + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + DateConverter.getSQLDateString(this.getDatum()) + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getUrl() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getEan() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";

        str = str + "(;;2#4#1#1#8#0#;;)" + this.getBestellnr() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getHerstellernr() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getLiefernr() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + "(;;2#4#1#1#8#0#;;)" + this.getBestelldatum() + "(;;2#4#1#1#8#0#;;)" + "(;;,;;)";
        str = str + this.getBestellmenge() + "(;;,;;)";
        str = str + this.getLagermenge();


        return str;
    }

    public boolean save() {

        if(getNummer() == null || getNummer().length() == 0) {
            setNummer(getNfh().getNextNumber());
        }
        
        if (id > 0 && !readonly) {
            this.update(TABLE_PRODUCTS_FIELDS, this.collect(), id);
            isSaved = true;
            fetchImage();
            return true;
        } else if (id == 0) {
            this.id = this.insert(TABLE_PRODUCTS_FIELDS, this.collect(),new int[]{0});
            lock();
            fetchImage();
            return true;
        }
        return false;
    }

    public Integer getWarengruppenId() {
        return WarengruppenId;
    }

    public String getProductgroupPath() {
        return ProductGroupHandler.instanceOf().getHierarchyPath(this.getWarengruppenId());
    }

    public void setWarengruppenId(Integer Warengruppenid) {
        this.WarengruppenId = Warengruppenid;
        this.isSaved = false;
    }


    public Lieferant getLieferant() {
        return lieferant;
    }

    public void setLieferant(mp4.items.Lieferant supplier) {
        this.lieferant = supplier;
        this.isSaved = false;
    }

    public String getProduktNummer() {
        return getNummer();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public Date getDatum() {
        return Datum;
    }

    public void setDatum(Date Datum) {
        this.Datum = Datum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public Double getVK() {
        return VK;
    }

    public void setVK(Double VK) {
        this.VK = VK;
    }

    public Double getEK() {
        return EK;
    }

    public void setEK(Double EK) {
        this.EK = EK;
    }

    public Double getTaxValue() {
        return Double.valueOf(ConnectionHandler.instanceOf().clone(TABLE_TAXES).select("wert", new String[]{"id", this.getSteuersatzId().toString(), ""})[0][0]);
    }

    public String getEan() {
        try {
            if (Long.valueOf(Ean) > 0) {
                return Ean;
            } else {
                return "";
            }
        } catch (NumberFormatException numberFormatException) {
            return Ean;
        }
    }

    public void setEan(String Ean) {
        this.Ean = Ean;
    }

    public String[][] getPrintModel() {

        Query q = query.clone(TABLE_PRODUCTS);

        String[][] str = q.selectFreeQuery("SELECT produkte.Produktnummer AS Nummer,produkte.Name,produkte.Text," +
                "produkte.VK,produkte.EK,steuersaetze.wert,Hersteller.name,Lieferanten.firma AS Lieferant," +
                "Warengruppenid,produkte.Datum,produkte.EAN FROM produkte " +
                "LEFT OUTER JOIN  lieferanten ON produkte.lieferantenid = lieferanten.id " +
                "LEFT OUTER JOIN  steuersaetze ON produkte.steuersatzid = steuersaetze.id " +
                "LEFT OUTER JOIN  hersteller ON produkte.herstellerid = hersteller.id " +
                "LEFT OUTER JOIN  warengruppengruppen ON produkte.warengruppenid = warengruppengruppen.id", null);

        return str;
    }


    public Integer getSteuersatzId() {
        return SteuersatzId;
    }

    public void setSteuersatzId(Integer SteuersatzId) {
        this.SteuersatzId = SteuersatzId;
    }
//
//    private void setHerstellerId(Integer valueOf) {
//        this.HerstellerId = valueOf;
//    }

    public Hersteller getHersteller() {
        return hersteller;
    }

    public String getBestellnr() {
        return Bestellnr;
    }

    public void setBestellnr(String Bestellnr) {
        this.Bestellnr = Bestellnr;
    }

    public String getHerstellernr() {
        return Herstellernr;
    }

    public void setHerstellernr(String Herstellernr) {
        this.Herstellernr = Herstellernr;
    }

    public String getLiefernr() {
        return Liefernr;
    }

    public void setLiefernr(String Liefernr) {
        this.Liefernr = Liefernr;
    }

    public String getBestelldatum() {
        return Bestelldatum;
    }

    public void setBestelldatum(String Bestelldatum) {
        this.Bestelldatum = Bestelldatum;
    }

    public Double getBestellmenge() {
        return Bestellmenge;
    }

    public void setBestellmenge(Double Bestellmenge) {
        this.Bestellmenge = Bestellmenge;
    }

    public Double getLagermenge() {
        return Lagermenge;
    }

    public void setLagermenge(Double Lagermenge) {
        this.Lagermenge = Lagermenge;
    }

    public String getTable() {
        return TABLE_PRODUCTS;
    }

    public String getCountColumn() {
        return "produktnummer";
    }
}
