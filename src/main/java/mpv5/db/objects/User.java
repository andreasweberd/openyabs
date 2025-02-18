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

import mpv5.usermanagement.*;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.JComponent;
import mpv5.Main;
import mpv5.data.PropertyStore;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseObjectLock;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryHandler;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.handler.VariablesHandler;
import mpv5.i18n.LanguageManager;
import mpv5.logging.Log;
import mpv5.mail.MailConfiguration;
import mpv5.pluginhandling.YabsPluginLoader;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Fonts;
import dtaus.Konto;
import javax.swing.UIManager;
import mpv5.YabsViewProxy;
import mpv5.db.common.QueryCriteria2;
import mpv5.db.common.QueryParameter;
import mpv5.pluginhandling.YabsPlugin;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.utils.text.TypeConversion;

/**
 *
 *  
 */
public class User extends DatabaseObject {

    private static User currentUser;
    private static final long serialVersionUID = 1L;

    public static User getCurrentUser() {
        if (currentUser == null) {
            Log.Debug(User.class, "There is no user logged in here, using default user.");
            try {
                currentUser = User.DEFAULT;
                return currentUser;
            } catch (Exception ex) {
                Log.Debug(User.class, "Default user is missing.");
                return new User();
            }
        } else {
            return currentUser;
        }
    }

    /**
     * Set the current logged in user
     * @param usern
     */
    public static void _setUser(User usern) {
        currentUser = usern;
//        predefTitle = (" (" + usern.getName() + ")");
    }

    /**
     *
     * @param ford
     * @return The Users default save directory, if defined in the user properties. User.home instead.
     */
    public static File getSaveDir(DatabaseObject ford) {

        String s = mpv5.db.objects.User.getCurrentUser().getProperties().getProperty("saveformat");
        if (s != null && s.length() > 0) {
            if (s.contains("/")) {
                s = s.substring(0, s.lastIndexOf("/"));
            }
        }

        String s2 = LocalSettings.getProperty(LocalSettings.BASE_DIR);
        File basedir = null;
        if (s2 != null && !s2.equals("null")) {
            basedir = new File(s2);
        } else {
            basedir = new File(System.getProperty("user.home"));
        }

        File subdirs = null;
        if (s != null) {
            subdirs = new File(s); 
        }

        File savedir = basedir;
        if (subdirs != null) {
            savedir = new File(VariablesHandler.parse(basedir.getPath() + File.separator + subdirs.getPath(), ford));
        }
        
        Log.Debug(User.class, savedir);

        savedir.mkdirs();
        return savedir;
    }
    private String password = "";
    private String fullname = "";
    private String laf = "";
    private String locale = "en_UK";
    private String defcountry = "";
    private String mail = "";
    private String language = "buildin_en";
    private int inthighestright = 4;
    private int intdefaultaccount = 1;
    private int intdefaultstatus = 1;
    private int compsids = 0;
    private boolean isenabled = true;
    private boolean isloggedin = true;
    private boolean isrgrouped = false;
    public static int ADMIN_ID = 1;
    private Date datelastlog = new Date();
    public static User DEFAULT = new User("Default User", "nobody", MPSecurityManager.RIGHT_TO_VIEW, 4343);
    public static HashMap<String, String> userCache = new HashMap<String, String>();
    public static HashMap<String, String> userCache2 = new HashMap<String, String>();
    private PropertyStore properties = new PropertyStore();
    /**
     * Properties added to this store will override the users properties stored in the database upon login
     */
    public static PropertyStore PROPERTIES_OVERRIDE = new PropertyStore();
    private MailConfiguration mailConfiguration;
    private DTAConfig dtaconf;

    /**
     * Caches all available usernames and IDs, no logins performed
     */
    public static void cacheUser() {
        userCache.clear();
        userCache.put(Integer.toBinaryString(DEFAULT.__getIDS()), DEFAULT.__getCname());
        userCache2.clear();
        userCache2.put(Integer.toBinaryString(DEFAULT.__getIDS()), DEFAULT.fullname);
        try {
            ArrayList<DatabaseObject> data = DatabaseObject.getObjects(Context.getUser());
            for (int i = 0; i < data.size(); i++) {
                DatabaseObject databaseObject = data.get(i);
                userCache.put(Integer.toBinaryString(databaseObject.__getIDS()), databaseObject.__getCname());
                userCache2.put(Integer.toBinaryString(databaseObject.__getIDS()), ((User) databaseObject).__getFullname());
            }
        } catch (NodataFoundException ex) {
            Log.Debug(User.class, ex.getMessage());
        }
    }

    /**
     * Tries to find the given ID in the DB
     * @param forId
     * @return A username if existing, else "unknown"
     */
    public static String getUsername(int forId) {
        if (userCache.containsKey(Integer.toBinaryString(forId))) {
            return userCache.get(Integer.toBinaryString(forId));
        } else {
            Log.Debug(User.class, "User not found in cache: " + Integer.toBinaryString(forId));
            return "unknown";
        }
    }

    /**
     * Tries to find the given ID in the DB
     * @param forId
     * @return A Fullname if existing, else "unknown"
     */
    public static String getFullName(int forId) {
        if (userCache2.containsKey(Integer.toBinaryString(forId))) {
            return userCache2.get(Integer.toBinaryString(forId));
        } else {
            Log.Debug(User.class, "User not found in cache: " + Integer.toBinaryString(forId));
            return "unknown";
        }
    }

    /**
     * Tries to find an User with the given name
     * @param username
     * @return
     */
    public static int getUserId(String username) {

        if (userCache.containsValue(username)) {
            Iterator<Entry<String, String>> data = userCache.entrySet().iterator();

            while (data.hasNext()) {
                Entry<String, String> entry = data.next();
                if (entry.getValue().equals(username)) {
                    Log.Debug(User.class, "Cached user id found: " + Integer.parseInt(entry.getKey(), 2));
                    return Integer.parseInt(entry.getKey(), 2);
                }
            }
        } else {
            cacheUser();
            if (userCache.containsValue(username)) {
                Iterator<Entry<String, String>> data = userCache.entrySet().iterator();

                while (data.hasNext()) {
                    Entry<String, String> entry = data.next();
                    if (entry.getValue().equals(username)) {
                        Log.Debug(User.class, "Cached user id found: " + Integer.parseInt(entry.getKey(), 2));
                        return Integer.parseInt(entry.getKey(), 2);
                    }
                }
            }
        }
        return DEFAULT.ids;
    }

    /**
     * Tries to find the given ID in the DB
     * @param userid
     * @throws mpv5.db.common.NodataFoundException
     */
    public User(int userid) throws NodataFoundException {
        this();
        this.fetchDataOf(userid);
        setProperties();
    }

    public User() {
        setContext(Context.getUser());
    }

    private User(String fullname, String userid, int highright, int IDS) {
        this();
        this.fullname = fullname;
        setCname(userid);
        this.inthighestright = highright;
        this.setIDS(IDS);

    }

    public Integer getID() {
        return __getIDS();
    }

    public String getName() {
        return getCname();
    }

    /**
     * Return this users plugins
     * @return 
     */
    public YabsPlugin[] getPlugins() {
        return YabsPluginLoader.getPlugins();
    }

    /**
     *
     * @return True if this user IS User.DEFAULT
     */
    public boolean isDefault() {
        if (getName().equals(DEFAULT.__getCname()) && __getIDS().intValue() == DEFAULT.__getIDS().intValue()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAdmin() {
        if (__getIDS().intValue() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Logs in this user into yabs (sets user defined properties etc); does not check the authentication. Use
     * {@link mpv5.usermanagement.MPSecurityManager#checkAuth(java.lang.String, java.lang.String) }
     * before logging in the User.
     */
    public void login() {
        Log.Debug(this, "releaseAllObjectsFor: " + this);
        DatabaseObjectLock.releaseAllObjectsFor(this);
        if (isenabled) {
            _setUser(this);
            setProperties();
            try {
                Locale.setDefault(TypeConversion.stringToLocale(__getLocale()));
                Log.Debug(this, "Locale.setDefault() for: " + Locale.getDefault());
                ControlPanel_Fonts.applyFont(Font.decode(LocalSettings.getProperty(LocalSettings.DEFAULT_FONT)));
                if (UIManager.getLookAndFeel().getName() == null ? __getLaf() != null : !UIManager.getLookAndFeel().getName().equals(__getLaf())) {
                    Main.setLaF(__getLaf());
                }
                defineMailConfig();
                defineDTAConfig();
                Log.Debug(this, "LanguageManager.getBundle() for: " + this);
                LanguageManager.getBundle();
            } catch (Exception e) {
                Log.Debug(e);
            }
            final User t = this;

            Runnable runnable = new Runnable() {

                @Override
                public void run() {
//                    setTitle();
                    setDatelastlog(new Date());
                    setIsloggedin(true);
                    save();
                    DatabaseObject.cacheObjects();//re-cache for user
                    YabsViewProxy.instance().addMessage(Messages.CACHED_OBJECTS);
                    try {
                        layoutinfo.putAll(ValueProperty.getProperty(t, "layoutinfo").getValue(new HashMap<String, String>()));
                    } catch (Exception ex) {
                        Log.Debug(this, ex.getMessage());
                    }
                }
            };

            new Thread(runnable).start();
        } else {
            Popup.warn(Messages.NOT_POSSIBLE.toString() + Messages.USER_DISABLED);
        }
    }

    /**
     * Logs out this user
     */
    public void logout() {
        DatabaseObjectLock.releaseAllObjectsFor(this);
        saveProperties();
        _setUser(DEFAULT);
        if (!isDefault()) {
            setIsloggedin(false);
            save();
        }
    }

    @Override
    public boolean save() {
        if (!isDefault()) {
            return super.save();
        } else {
            Popup.notice(Messages.DEFAULT_USER);
            return false;
        }
    }

    @Override
    public boolean delete() {
        if (!isAdmin()) {
            return super.delete();
        } else {
            Popup.notice(Messages.ADMIN_USER);
            return false;
        }
    }

    /**
     * @return the password
     */
    public String __getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the laf
     */
    public String __getLaf() {
        return laf;
    }

    /**
     * @param laf the laf to set
     */
    public void setLaf(String laf) {
        this.laf = laf;
    }

    /**
     * @return the locale
     */
    public String __getLocale() {
        return locale;
    }

    /**
     * @param locale the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the mail
     */
    public String __getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the language
     */
    public String __getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the isenabled
     */
    public boolean __getIsenabled() {
        return isenabled;
    }

    /**
     * @param isenabled the isenabled to set
     */
    public void setIsenabled(boolean isenabled) {
        this.isenabled = isenabled;
    }

    @Override
    public String toString() {
        return getCname();
    }

    /**
     * @return the lastlogdate
     */
    public Date __getDatelastlog() {
        return datelastlog;
    }

    /**
     * @param lastlogdate the lastlogdate to set
     */
    public void setDatelastlog(Date lastlogdate) {
        this.datelastlog = lastlogdate;
    }

    /**
     * @return the isloggedin
     */
    public boolean __getIsloggedin() {
        return isloggedin;
    }

    /**
     * @param isloggedin the isloggedin to set
     */
    public void setIsloggedin(boolean isloggedin) {
        this.isloggedin = isloggedin;
    }

    /**
     * @return the fullname
     */
    public String __getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the defcountry
     */
    public String __getDefcountry() {
        return defcountry;
    }

    /**
     * @param defcountry the defcountry to set
     */
    public void setDefcountry(String defcountry) {
        this.defcountry = defcountry;
    }

    /**
     * If the usernames & ids match, return true
     * @param n
     * @return
     */
    public boolean equalTo(User n) {
        if ((n).__getCname().equals(this.__getCname()) && (n).__getIDS().intValue() == this.__getIDS().intValue()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JComponent getView() {
        return new mpv5.ui.dialogs.subcomponents.ControlPanel_Users(this);
    }

    /**
     * Set a property for this user
     * @param key
     * @param value
     */
    public void changeProperty(String key, String value) {
        properties.changeProperty(key, value);
    }

    /**
     * Retrieve all properties from this user
     * @return 
     */
    public PropertyStore getProperties() {
        return properties;
    }

    /**
     * Saves the user properties
     */
    public void saveProperties() {
        synchronized (properties) {
            List<String[]> l = properties.getList();

            for (String[] d : l) {
                UserProperty p = new UserProperty();
                p.setValue(d[1]);
                p.setCname(d[0]);
                p.setUsersids(getID());
                p.setGroupsids(__getGroupsids());
                p.save();
            }
        }
    }

    /**
     * Delete the users properties
     */
    public void deleteProperties() {
        QueryCriteria c = new QueryCriteria("usersids", __getIDS());
        QueryHandler.instanceOf().clone(Context.getUserProperties()).delete(c);
    }

    /**
     * Apply the user settings to this user (usually called during the login process)
     */
    public void setProperties() {

        QueryCriteria2 criteria = new QueryCriteria2();
        criteria.and(new QueryParameter(Context.getUserProperties(), "usersids", ids, QueryParameter.EQUALS),
                     new QueryParameter(Context.getUserProperties(), "groupsids", __getGroupsids(), QueryParameter.EQUALS));
        properties = new PropertyStore();
        try {
            properties.addAll(QueryHandler.instanceOf().clone(Context.getUserProperties()).select("cname, value", criteria).getData());
            properties.addAll(PROPERTIES_OVERRIDE);
            properties.setChanged(false);
        } catch (NodataFoundException ex) {
            Log.Debug(this, ex.getMessage());
        }
    }

    @Override
    public java.util.Map<String, Object> resolveReferences(java.util.Map<String, Object> map) {
        
        List<Company> data;
        try {
            data = DatabaseObject.getReferencedObjects(this, Context.getCompany(), new Company());
            //Presumably 0 or 1
            for (int i = 0; i < data.size(); i++) {
                map.put("company", data.get(i));
            }
        } catch (Exception ex) {
            Log.Debug(this, ex.getMessage());
        }
//TODO  check for usage
        return super.resolveReferences(map);
    }

    /**
     * @return the isrgrouped
     */
    public boolean __getIsrgrouped() {
        return isrgrouped;
    }

    /**
     * @param isrgrouped the isrgrouped to set
     */
    public void setIsrgrouped(boolean isrgrouped) {
        this.isrgrouped = isrgrouped;
    }

    /**
     * @return the inthighestright
     */
    public int __getInthighestright() {
        return inthighestright;
    }

    /**
     * @param inthighestright the inthighestright to set
     */
    public void setInthighestright(int inthighestright) {
        this.inthighestright = inthighestright;
    }

    /**
     * @return the intdefaultaccount
     */
    public int __getIntdefaultaccount() {
        return intdefaultaccount;
    }

    /**
     * @param intdefaultaccount the intdefaultaccount to set
     */
    public void setIntdefaultaccount(int intdefaultaccount) {
        this.intdefaultaccount = intdefaultaccount;
    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return null;
    }

    /**
     * If this returns true, the User is only allowed to see, and use, data which belongs to the same Group as him
     * <br/><b> and data from the Group with the ID 1.</b><br/><br/>
     * Use this method to implement multi-client capability.
     * @return
     */
    public boolean isGroupRestricted() {
        return __getIsrgrouped();
    }

    /**
     * @return the intdefaultstatus
     */
    public int __getIntdefaultstatus() {
        return intdefaultstatus;
    }

    /**
     * @param intdefaultstatus the intdefaultstatus to set
     */
    public void setIntdefaultstatus(int intdefaultstatus) {
        this.intdefaultstatus = intdefaultstatus;
    }

    /**
     * @return the compsids
     */
    public int __getCompsids() {
        return compsids;
    }

    /**
     * @param compsids the compsids to set
     */
    public void setCompsids(int compsids) {
        this.compsids = compsids;
    }

    /**
     * Define the mail config
     */
    public void defineMailConfig() {
        if (getProperties().hasProperty("smtp.host")) {
            defineMailConfiguration(new MailConfiguration());
            mailConfiguration.setSmtpHost(getProperties().getProperty("smtp.host"));
            mailConfiguration.setSmtpPort(getProperties().getProperty("smtp.port"));
            mailConfiguration.setSenderAddress(mail);
            mailConfiguration.setUsername(getProperties().getProperty("smtp.host.user"));
            mailConfiguration.setPassword(getProperties().getProperty("smtp.host.password"));
            mailConfiguration.setUseTls(Boolean.valueOf(getProperties().getProperty("smtp.host.usetls")));
            mailConfiguration.setUseSSL(Boolean.valueOf(getProperties().getProperty("smtp.host.usessl")));
            mailConfiguration.setUseSmtps(Boolean.valueOf(getProperties().getProperty("smtp.host.usesmpts")));
        } else {
            Log.Debug(this, "Mail configuration not set.");
        }
    }

    /**
     * @return the mailConfiguration
     */
    public MailConfiguration getMailConfiguration() {
        if (mailConfiguration.getSmtpHost() == null || mailConfiguration.getSmtpHost().length() == 0) {
            mpv5.YabsViewProxy.instance().addMessage(Messages.NO_MAIL_CONFIG);
            Log.Debug(this, "SMTP host configuration not set: " + mailConfiguration.getSmtpHost());
        }
        return mailConfiguration;
    }

    /**
     * @param mailConfiguration the mailConfiguration to set
     */
    public void defineMailConfiguration(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    /**
     * The configured banking account
     * @return
     */
    public Konto getDTAAccount() {
        return dtaconf.getBankAccount();
    }

    /**
     * The configured banking account
     * @return
     */
    public List<String> getDTAUsages() {
        return dtaconf.getUsages();
    }

    public void defineDTAConfig() {
        try {
            Konto acc = new Konto(getProperties().getProperty("dtabankid"),
                    getProperties().getProperty("dtabankaccount"),
                    getProperties().getProperty("dtabankname"));
            acc.name = __getFullname();
            dtaconf = new DTAConfig();
            dtaconf.setBankAccount(acc);
            dtaconf.getUsages().add(getProperties().getProperty("dtausage0"));
        } catch (Exception e) {
            Log.Debug(this, "Unable to create DTA info");
            dtaconf = null;
        }
    }
    private HashMap<String, String> layoutinfo = new HashMap<String, String>();

    /**
     *
     * @return
     */
    public HashMap<String, String> getLayoutProperties() {
        return layoutinfo;
    }

    /**
     * Delegates to getproperties().getproperty
     * @param typ
     * @param name
     * @return
     */
    public boolean getProperty(String typ, String name) {
        return getProperties().getProperty(typ, name);
    }

    /** Delegates to getproperties().getproperty
     * @param name
     * @return
     */
    public String getProperty(String name) {
        return getProperties().getProperty(name);
    }

    /** Delegates to getproperties().setproperty
     * @param name
     * @return
     */
    public void setProperty(String key, String value) {
        getProperties().changeProperty(key, value);
    }
}

class DTAConfig {

    private Konto bankAccount;
    private List<String> usages = new Vector<String>();

    /**
     * @return the bankAccount
     */
    public Konto getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(Konto bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * @return the usages
     */
    public List<String> getUsages() {
        return usages;
    }
}
