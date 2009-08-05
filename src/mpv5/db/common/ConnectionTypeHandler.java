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
package mpv5.db.common;

import mpv5.logging.Log;
import java.io.File;
import java.io.IOException;
import mpv5.globals.LocalSettings;

/**
 * This class handles the different DB connection types (derby, mysql, custom)
 *  
 */
public class ConnectionTypeHandler {

    /**
     * Use embedded derby database
     */
    public static final int DERBY = 0;
    public static String DERBY_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    /**
     * Use myql database driver
     */
    public static final int MYSQL = 1;
    public static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * Use custom database driver
     */
    public static final int CUSTOM = 2;

    //Available Drivers
    public static String CUSTOM_DRIVER = "custom.driver";// (specify path with type declaration jdbc:sql://<path>:port)
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String[] DRIVERS = {DERBY_DRIVER, MYSQL_DRIVER, CUSTOM_DRIVER};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static File DERBY_FILE = null;
    public static File MYSQL_FILE = null;
    public static File CUSTOM_FILE = null;



    public static String getDriverName() {
        return CONNECTION_STRING;
    }

    public static int getDriverType() {
        return PREDEFINED_DRVER;
    }
    private static String CONNECTION_STRING = null;
    private static Integer PREDEFINED_DRVER = null;

    private static String URL;
    private static String DBNAME;


    /**
     * Constructs a new ConnHandler
     */
    public ConnectionTypeHandler() {

        setDRIVER(LocalSettings.getProperty(LocalSettings.DBDRIVER));
        ConnectionTypeHandler.URL = LocalSettings.getProperty( LocalSettings.DBPATH);
        ConnectionTypeHandler.DBNAME = LocalSettings.getProperty( LocalSettings.DBNAME);
    }

    /**
     * Constructs a new ConnHandler with predefind ConnectionTypeHandler.Driver
     * @param driverset 
     */
    public ConnectionTypeHandler(int driverset) {
        ConnectionTypeHandler.PREDEFINED_DRVER = driverset;
    }

    /**
     * 
     * @param withCreate Shall we create a new database?
     * @return The DB connection string
     */
    public String getConnectionString(boolean withCreate) {

        switch (PREDEFINED_DRVER) {
            case DERBY:
                String cstring = "jdbc:derby:" + getURL() + File.separator + DBNAME + ";";
                if (withCreate) {
                    cstring += "create=true;";
                }
                setConnectionString(cstring);
                break;
            case MYSQL:
                setConnectionString("jdbc:mysql://" + getURL() + "/" + DBNAME);
                if (withCreate) {
                    Log.Debug(this, "Sie müssen die MYSQL Datenbank " + DBNAME + " manuell anlegen.");
                }
                break;
            case CUSTOM:
                setConnectionString(getURL() + "/" + DBNAME);
                if (withCreate) {
                    Log.Debug(this, "Sie müssen die SQL Datenbank " + DBNAME + " manuell anlegen.");
                }
                break;
        }
        return CONNECTION_STRING;
    }

    /**
     * Get the SQL command for creating the tables - for the choosen driver
     * @return The SQL command for creating the tables
     */
    public String[] getTableCreating_SQLCommand() {
        File filen = null;
        switch (PREDEFINED_DRVER) {
            case DERBY:
                filen = DERBY_FILE;
                break;
            case MYSQL:
                filen = MYSQL_FILE;
                break;
            case CUSTOM:
                filen = CUSTOM_FILE;
                break;
        }
        try {

            Log.Debug(this, "SQL Datei: " + filen.getCanonicalPath());
        } catch (IOException ex) {
            Log.Debug(this, ex);
        }
//        if (filen.exists()) {
//            return new FileReaderWriter(filen).readLines();
//        } else {
//            Log.Debug(this, "SQL Datei " + filen.getName() + " not found. Trying in-build SQL Script.");
//            return Struktur.SQL_COMMAND;
//        }
        return null;
    }

    /**
     * Override the JDBC connection string
     * @param conn_string
     */
    public void setConnectionString(String conn_string) {
        ConnectionTypeHandler.CONNECTION_STRING = conn_string;
    }

    /**
     * 
     * @return The DB location
     */
    public String getURL() {
        return URL;
    }

    /**
     * Set the DB location. May be file path, or network path
     * @param URL
     */
    public void setURL(String URL) {
        ConnectionTypeHandler.URL = URL;
    }

    /**
     * Override the JDBC driver string
     * @param predefinedDriver
     */
    public void setDRIVER(int predefinedDriver) {
        ConnectionTypeHandler.PREDEFINED_DRVER = predefinedDriver;
    }

    /**
     * 
     * @return The JDBC driver string
     */
    public String getDriver() {
        switch (PREDEFINED_DRVER) {
            case DERBY:
                return DERBY_DRIVER;
            case MYSQL:
                return MYSQL_DRIVER;
            case CUSTOM:
                return CUSTOM_DRIVER;
        }
        return null;
    }

    /**
     *
     * @param predefinedDriver
     */
    public void setDRIVER(String predefinedDriver) {
        if (predefinedDriver.equalsIgnoreCase(DERBY_DRIVER)) {
            PREDEFINED_DRVER = DERBY;
        } else if (predefinedDriver.equalsIgnoreCase(MYSQL_DRIVER)) {
            PREDEFINED_DRVER = MYSQL;
        } else {
            PREDEFINED_DRVER = CUSTOM;
            CUSTOM_DRIVER = predefinedDriver;
        }
    }

    public void setDBName(String dbname) {
       ConnectionTypeHandler.DBNAME = dbname;
    }
}