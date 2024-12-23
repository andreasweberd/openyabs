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
package mpv5.pluginhandling;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import mpv5.Main;
import mpv5.db.common.Context;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseObjectModifier;
import mpv5.db.common.QueryHandler;
import mpv5.globals.Constants;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPView;
import mpv5.utils.images.MPIcon;

/**
 *
 *  This class loads plugins from the database, and utilises caching of the plugin files.
 */
public class MPPLuginLoader {

    public static String pluginSignature = LocalSettings.getProperty(LocalSettings.CACHE_DIR) + File.separator + "%%filename%%-mp5p.jar";
    public static List<DatabaseObjectModifier> registeredModifiers = new Vector<DatabaseObjectModifier>();

    public static Image getDefaultPluginImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(MPPLuginLoader.class.getResource("/images/48/blockdevice.png"));
        } catch (Exception e) {
            Log.Debug(e);
        }
        return img;
    }

    public static Image getErrorImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(MPPLuginLoader.class.getResource("/images/48/messagebox_question.png"));
        } catch (Exception e) {
            Log.Debug(e);
        }
        return img;
    }

    public static void importPlugin(String name, File file) throws FileNotFoundException {
        mpv5.YabsViewProxy.instance().setWaiting(true);
        try {
            MP5Plugin pl = new MPPLuginLoader().checkPlugin(file);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MPPLuginLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (pl != null) {
                String s = name;
                if (s != null) {
                    String filename = QueryHandler.instanceOf().clone(Context.getFiles()).insertFile(file);
                    Plugin p = new Plugin();
                    p.setDescription(s);
                    p.setCname("Plugin: " + pl.getName());
                    p.setFilename(filename);
                    p.save();
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            Popup.error(mpv5.Main.getApplication().getMainFrame(), "Your database seems slow, try again :-/");
        } finally {
            mpv5.YabsViewProxy.instance().setWaiting(false);
        }
    }

    /**
     * Loads all plugins which are assigned to the current logged in user from database or cache dir<br/>
     * does NOT call plugin.load()
     * @return An array of instantiated plugins
     */
    public static MP5Plugin[] getPlugins() {

        ArrayList<MP5Plugin> list = null;

        try {
            list = new ArrayList<MP5Plugin>();
            ArrayList<File> jars = new ArrayList<File>();
            QueryCriteria criterias = new QueryCriteria("usersids", mpv5.db.objects.User.getCurrentUser().__getIDS());
            try {
                ArrayList<UserPlugin> data = DatabaseObject.getObjects(Context.getPluginsToUsers(), criterias);
                for (int i = 0; i < data.size(); i++) {
                    UserPlugin up = data.get(i);
                    Plugin o = ((Plugin) DatabaseObject.getObject(Context.getPlugins(), up.__getPluginsids()));
                    Log.Debug(MPPLuginLoader.class, "Found Plugin: " + o);
                    if (!isCachedPlugin(o.__getFilename())) {
                        Log.Debug(MPPLuginLoader.class, "Caching plugin: " + pluginSignature.replace("%%filename%%", o.__getFilename()));
                        jars.add(QueryHandler.instanceOf().clone(Context.getFiles()).retrieveFile(o.__getFilename(), new File(pluginSignature.replace("%%filename%%", o.__getFilename()))));
                    } else {
                        Log.Debug(MPPLuginLoader.class, "Using cached plugin: " + pluginSignature.replace("%%filename%%", o.__getFilename()));
                        jars.add(new File(pluginSignature.replace("%%filename%%", o.__getFilename())));
                    }
                }
            } catch (NodataFoundException ex) {
                Log.Debug(MPPLuginLoader.class, "No plugins found: " + ex.getMessage());
            }

            for (int i = 0; i < jars.size(); i++) {
                File x = jars.get(i);
                MP5Plugin c = checkPlugin(x);
                if (c != null) {
                    list.add(c);
                }
            }
        } catch (Exception e) {
            Popup.error(e);
            Log.Debug(e);
        }

        return list.toArray(new MP5Plugin[0]);
    }

    /**
     * Checks if the plugin is already cached
     * @param filename
     * @return false if the file DOES NOT EXIST in the plugin cache directory, true otherwise
     */
    public static boolean isCachedPlugin(String filename) {
        File f = new File(pluginSignature.replace("%%filename%%", filename));
        Log.Debug(MPPLuginLoader.class, "Checking cache for " + filename);
        return f.exists() && f.canRead() && (checkPlugin(f) != null);
    }

    /**
     * Checks if the given file is a valid plugin
     * @param pluginCandidate
     * @return the plugin if <code>Constants.PLUGIN_LOAD_CLASS<code/> could be instantiated from this file
     */
    public static MP5Plugin checkPlugin(final File pluginCandidate) {
        try {
            URL[] urls = {new URL("jar:file:" + pluginCandidate + "!/")};
            URLClassLoader loader = new AddURLClassLoader(urls, Main.getApplication().getClass().getClassLoader());
            Class c = loader.loadClass(Constants.PLUGIN_LOAD_CLASS);
            Object o = c.newInstance();
            return (MP5Plugin) o;
        } catch (Exception malformedURLException) {
            Log.Debug(malformedURLException);
            Popup.error(malformedURLException);
            return null;
        }
    }

    /**
     * A nifty classloader
     */
    public static class AddURLClassLoader extends URLClassLoader {

        public AddURLClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }

        public AddURLClassLoader(URL[] urls) {
            super(urls);
        }

        public AddURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
            super(urls, parent, factory);
        }

        @Override
        public void addURL(URL url) {
            super.addURL(url);
        }
    }

    /**
     *
     * @param file
     * @return
     */
    public MP5Plugin getPlugin(File file) {
        return checkPlugin(file);
    }

    /**
     * Load all queued plugins
     */
    public static void loadPlugins() throws Exception, Throwable {
        for (int i = 0; i < pluginstoBeLoaded.size(); i++) {
            MP5Plugin mP5Plugin = pluginstoBeLoaded.get(i);
            loadPlugin(mP5Plugin);
        }
    }

    /**
     * Queues plugins to be loaded after the main Frame is showing.</br>
     * Adding plugins AFTER the main Frame is constructed will result in nothing.</br>
     * Use {@link loadPlugin(MP5Plugin)} instead.
     * @param plugins
     */
    public static void queuePlugins() {
        pluginstoBeLoaded.addAll(Arrays.asList(getPlugins()));
    }
    private static ArrayList<MP5Plugin> pluginstoBeLoaded = new ArrayList<MP5Plugin>();

    /**
     * Unloads the plugin and notifies the main view about the unload
     * @param mP5Plugin
     */
    public static void unLoadPlugin(MP5Plugin mP5Plugin) {
        mP5Plugin.unload();
        loadedPlugs.remove(mP5Plugin.getUID());
//        Component[] c = mpv5.YabsViewProxy.instance().getIdentifierView().getPluginIcons().getComponents();
//        for (int i = 0; i < c.length; i++) {
//            Component component = c[i];
//            if (((JLabel) component).getToolTipText().contains(String.valueOf(mP5Plugin.getUID()))) {
////                mpv5.YabsViewProxy.instance().getIdentifierView().getPluginIcons().remove(component);
//            }
//        }
        mpv5.YabsViewProxy.instance().getIdentifierFrame().validate();
        mpv5.YabsViewProxy.instance().getIdentifierFrame().repaint();
        mP5Plugin = null;
        System.gc();
    }

    /**
     * Loads the given plugin (by calling <code>plugin.load(this)<code/>). If the plugin is a visible plugin, adds it to the main tab pane.</br>
     * If it is a <code>Runnable<code/>, it will be started on an new thread.
     * @param gin
     */
    public void loadPlugin(Plugin gin) throws Exception, Throwable {
        MP5Plugin plo = new mpv5.pluginhandling.MPPLuginLoader().getPlugin(QueryHandler.instanceOf().clone(Context.getFiles()).retrieveFile(gin.__getFilename()));
        if (plo != null) {
            loadPlugin(plo);
        } else {
            Log.Debug(this, "Plugin not loaded: " + plo);
        }
    }
    private static final List<Long> loadedPlugs = new Vector<Long>();

    /**
     * Loads the given plugin (by calling <code>plugin.load(this)<code/>). If the plugin is a visible plugin, adds it to the main tab pane.</br>
     * If it is a <code>Runnable<code/>, it will be started on an new thread.
     * @param mP5Plugin
     * @throws Exception
     * @throws Throwable
     */
    public static void loadPlugin(final MP5Plugin mP5Plugin) throws Exception, Throwable {
        if (!loadedPlugs.contains(mP5Plugin.getUID()) && mP5Plugin.isEnabled()) {
            loadedPlugs.add(mP5Plugin.getUID());
            final JLabel plab = new JLabel();
            plab.setDisabledIcon(new MPIcon(MPPLuginLoader.getErrorImage()).getIcon(18));
            try {
                try {
                    Log.Debug(MPPLuginLoader.class, "Plugin: \n"
                            + "Name:        " + mP5Plugin.getName() + "\n"
                            + "Vendor:      " + mP5Plugin.getVendor() + "\n"
                            + "IsComponent: " + mP5Plugin.isComponent() + "\n"
                            + "IsRunnable:  " + mP5Plugin.isRunnable());
                    mP5Plugin.load(new MPView(Main.getApplication()));
                } catch (ClassCastException e) {
                    Log.Debug(MPPLuginLoader.class, e.getMessage());
                    Log.Debug(MPPLuginLoader.class, "Incompatible plugin found, asking to load anyway..");
                    if (!Popup.Y_N_dialog(Messages.PLUGIN_INCOMPATIBLE, e)) {
                        loadedPlugs.remove(mP5Plugin.getUID());
                        return;
                    } 
                }

                if (mP5Plugin instanceof DatabaseObjectModifier) {
                    Log.Debug(MPPLuginLoader.class, "Register modifying plugins: " + mP5Plugin);
                    registeredModifiers.add((DatabaseObjectModifier) mP5Plugin);
                }

                if (mP5Plugin.isComponent()) {
                    mpv5.YabsViewProxy.instance().getIdentifierView().addTab((JComponent) mP5Plugin, mP5Plugin.getName());
                }
                if (mP5Plugin.isRunnable() && mP5Plugin.isLoaded()) {
                    Thread t = new Thread((Runnable) mP5Plugin);
                    t.start();
                }
                if (mP5Plugin.getIcon() != null) {
                    plab.setIcon(new MPIcon(mP5Plugin.getIcon()).getIcon(18));
                } else {
                    plab.setIcon(new MPIcon(MPPLuginLoader.getDefaultPluginImage()).getIcon(18));
                }
                plab.setToolTipText("<html><b>" + mP5Plugin.getName() + " " + Messages.LOADED + "</b><br/><font size=-3>[" + mP5Plugin.getUID() + "]</html>");
                plab.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON2 || e.getButton() == MouseEvent.BUTTON3) {
                            JLabel source = (JLabel) e.getSource();
                            JPopupMenu m = new JPopupMenu();
                            JMenuItem n = new JMenuItem(Messages.UNLOAD.getValue());
                            n.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    unLoadPlugin(mP5Plugin);
                                    mpv5.Main.getApplication().getMainView().getMenuBar().remove(plab);
                                    mpv5.Main.getApplication().getMainView().getMenuBar().validate();
                                }
                            });
                            m.add(n);
                            m.show(plab, e.getX(), e.getY());
                        }
                    }
                });
                try {
                    Main.getApplication().getMainView().getMenuBar().add(plab);
                    mpv5.Main.getApplication().getMainView().getMenuBar().validate();
                } catch (Exception e) {
                    Log.Debug(e);
                }
            } catch (Throwable e) {
                Log.Debug(e);
//                plab.setEnabled(false);
                throw e;
            }
        } else {
            Popup.notice(Messages.NOT_POSSIBLE + "\n" + mP5Plugin + " is already loaded and doesn't allow multiple instances.");
            Log.Debug(MPPLuginLoader.class, "Plugin does not allow multiple instances: " + mP5Plugin);
        }
    }

    /**
     * Removes the modifier from the list
     * @param m
     */
    public static void removeModifier(DatabaseObjectModifier m) {
        registeredModifiers.remove(m);
    }
}
