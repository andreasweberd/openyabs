package mpv5.db.migration;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.ProgressMonitor;
import mpv5.db.common.ConnectionTypeHandler;
import mpv5.db.common.DatabaseInstallation;
import mpv5.db.common.DatabaseUpdater;
import mpv5.globals.Messages;
import mpv5.utils.files.FileReaderWriter;

/**
 * 
 * @author Develop
 */
public class DerbyToMySQL {

    private final String DRV = "org.apache.derby.jdbc.EmbeddedDriver";
    private String charset = "";
    private String engine = "";
    private String lateAutoIncr = "";
    private Connection con = null;
    private FileReaderWriter fw = null;
    private File file = null;
    private ProgressMonitor pm;
    private String HEXES = "0123456789ABCDEF";
    private StringBuilder result = new StringBuilder();
    private DatabaseMetaData Meta = null;
    private int stms = 0;
    private String[] Install_MySQL;
    Map<Double, String[]> Updates_MySQL;
    ResultSet tables;

    public DerbyToMySQL(String Url) throws ClassNotFoundException,
            InstantiationException,
            SQLException,
            IllegalAccessException {
        Class.forName(DRV).newInstance();
        con = DriverManager.getConnection(Url);
    }

    public DerbyToMySQL(Connection con) throws SQLException {
        this.con = con;
        Meta = con.getMetaData();
    }

    public void writeDerbyToMySQLDump(File f, JFrame app, String Message) throws SQLException, IOException {
        file = f;
        stms = 0;
        String[] types = {"TABLE"};
        tables = Meta.getTables(null, "APP", "%", types);
        int i = 0;
        while (tables.next()) {
            i++;
        }
        tables = Meta.getTables(null, "APP", "%", types);
        Install_MySQL = DatabaseInstallation.getStructureForMigration(ConnectionTypeHandler.MYSQL);
        Updates_MySQL = new DatabaseUpdater().getStructureForMigration(ConnectionTypeHandler.MYSQL);
        pm = new ProgressMonitor(app, Message, "", 0, i + Install_MySQL.length + Updates_MySQL.size());
        if (!pm.isCanceled()) {
            explodeTables();
        }
        fixloginsettings();
        pm.close();
    }

    private void explodeTables() throws SQLException, IOException {
        while (tables.next() && !pm.isCanceled()) {
            result = new StringBuilder();
            String tableName = tables.getString("TABLE_NAME");
            tableName = tableName.toLowerCase();
            pm.setProgress(stms++);
            pm.setNote("Calculating " + tableName);
            result.append("\n\n#-- Table:").append(tableName);
            result.append("\nDROP TABLE IF EXISTS ").append(tableName).append(" ;");
            result.append("\nCREATE TABLE ").append(tableName);
            result.append(" (\n");
            StringBuilder primaryKeyColumns = new StringBuilder();
//            tableName = tableName.toUpperCase();
            ResultSet cols = Meta.getColumns(null, null, tableName, "%");
            while (cols.next()) {
                result.append("    ");
                result.append(cols.getString("COLUMN_NAME").toLowerCase());
                result.append("         ");
                result.append(cols.getString("TYPE_NAME").replace("BIGINT", "BIGINT(20)"));
                if (cols.getString("TYPE_NAME").equalsIgnoreCase("VARCHAR")) {
                    result.append("(").append(cols.getString("COLUMN_SIZE")).append(")").append(" ");
                }
                if (cols.getString("COLUMN_DEF") != null
                        && !cols.getString("COLUMN_DEF").contains("AUTOINCREMENT")
                        && !cols.getString("COLUMN_DEF").contains("GENERATED_BY_DEFAULT")) {
                    result.append(" DEFAULT ");
                    result.append("'").append(cols.getString("COLUMN_DEF")).append("'");
                }
                if (cols.getString("IS_NULLABLE").equalsIgnoreCase("NO")) {
                    result.append(" NOT NULL");
                } else {
                    if (cols.getString("COLUMN_DEF") == null) {
                        result.append(" DEFAULT ");
                        result.append(cols.getString("COLUMN_DEF"));
                    }
                }
                if (cols.getString("IS_AUTOINCREMENT").equalsIgnoreCase("YES")) {
                    if (primaryKeyColumns.length() > 0) {
                        primaryKeyColumns.append(", ");
                    }
                    primaryKeyColumns.append(cols.getString("COLUMN_NAME"));
                    lateAutoIncr = "\n\nALTER TABLE " + tableName
                            + " CHANGE COLUMN " + cols.getString("COLUMN_NAME").toLowerCase()
                            + " " + cols.getString("COLUMN_NAME").toLowerCase()
                            + " " + cols.getString("TYPE_NAME") + " AUTO_INCREMENT ;";
                }
                result.append(",\n");
            }
            cols.close();

            if (primaryKeyColumns.length() > 0) {
                result.append("    PRIMARY KEY");
                result.append(" (");
                result.append(primaryKeyColumns.toString());
                result.append(")");
            } else {
                int len = result.length();
                result.deleteCharAt(len - 2);
            }
            result.append("\n)");
            result.append(" ENGINE=");
            result.append(engine);
            result.append(" DEFAULT");
            result.append(" CHARSET=");
            result.append(charset);
            result.append(";\n");
            dumpTable(con, tableName);
            result.append(lateAutoIncr);
            WriteSQLDumpToFile(result.toString());
        }
        tables.close();
        appendStandards();
    }

    private void dumpTable(Connection dbConn, String tableName) throws SQLException, IOException {

        PreparedStatement stmt = dbConn.prepareStatement("SELECT * FROM " + tableName);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        result.append("\n\n#-- Tabledata ").append(tableName).append("\n");
        result.append("LOCK TABLES ").append(tableName).append(" WRITE;\n");
        Blob blob;
        byte[] bytarr;
        int bytes;

        while (rs.next()) {
            boolean isblob = false;
            result.append("INSERT INTO ").append(tableName).append(" VALUES (");
            for (int i = 0; i < columnCount; i++) {
                if (i > 0) {
                    result.append(", ");
                }
                Object value = rs.getObject(i + 1);
                if (value == null) {
                    result.append("NULL");
                } else {
                    if (metaData.getColumnType(i + 1) == java.sql.Types.BLOB) {
                        blob = (Blob) value;
                        bytes = (int) blob.length();
                        bytarr = blob.getBytes(1, bytes);
                        result.append("0x");
                        result.append(bytesToHex(bytarr));
                        isblob = true;
                    } else if (metaData.getColumnType(i + 1) == java.sql.Types.LONGVARBINARY) {
                        bytarr = (byte[]) value;
                        result.append("0x");
                        result.append(bytesToHex(bytarr));
                        isblob = true;
                    } else {
                        String outputValue = value.toString();
                        outputValue = outputValue.replaceAll("'", "\\'");
                        result.append("'").append(outputValue).append("'");
                    }
                }
            }
            result.append(");\n");
            if (isblob) {
//                Daten gleich schreiben da sonst ein Heapsize "out-of-memory" droht ...
                WriteSQLDumpToFile(result.toString());
                result = new StringBuilder();
            }
        }
        rs.close();
        result.append("UNLOCK TABLES;");
        stmt.close();
    }

    private void WriteSQLDumpToFile(String SQL) {
        if (fw == null) {
            fw = new FileReaderWriter(file, "ISO-8859-15");
            fw.write("#-- This DUMP was generated by DerbyToMysql Export tool\n");
            fw.write("#-- Developed by Jan Hahnisch");
            fw.write("#-- www.jan-hahnisch.de");
            fw.write("#-- TIME :" + new Date().toString() + "\n");
            fw.write("#-- ***************************************************\n");
        }
        fw.write(SQL);
    }

    public String getCharSet() {
        return charset;
    }

    public void setCharSet(String charset) {
        this.charset = charset;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    private void buildAppend(StringBuilder result, String sql[], boolean withMsg) {
        if (!pm.isCanceled()) {
            for (int i = 0; i < sql.length; i++) {
                if (!sql[i].startsWith("CREATE TABLE")
                        && !sql[i].startsWith("INSERT INTO")) {
                    result.append(sql[i]).append(";\n\n");
                }
                if (withMsg) {
                    try {
                        pm.setProgress(stms + i);
                        pm.setNote(Messages.ACTION_CALCULATING.toString() + " Install line " + i);
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                    }
                }
            }
            if (withMsg) {
                stms = stms + sql.length;
            }
        }
    }

    private void appendStandards() {
        result = new StringBuilder();
        buildAppend(result, Install_MySQL, true);
        for (Iterator<Double> keys = Updates_MySQL.keySet().iterator(); keys.hasNext();) {
            Double vers = keys.next();
            String[] val = Updates_MySQL.get(vers);
            pm.setProgress(stms++);
            try {
                pm.setNote(Messages.ACTION_CALCULATING.toString() + " Update to Version" + vers);
                Thread.sleep(50);
            } catch (InterruptedException ex) {
            }
            buildAppend(result, val, false);
        }
        WriteSQLDumpToFile(result.toString());
    }

    private String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (final byte b : bytes) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    private void fixloginsettings() {
        WriteSQLDumpToFile("UPDATE users SET password='5F4DCC3B5AA765D61D8327DEB882CF99' where IDS = 1;");
        WriteSQLDumpToFile("UPDATE users SET isloggedin=0 where isloggedin = 1;");
    }
}