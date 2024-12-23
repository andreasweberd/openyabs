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
package mpv5.ui.dialogs;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.globals.Messages;

/**
 *
 *  
 */
public class Popup {

    public static String WARN = Messages.WARNING.getValue();
    public static String ERROR = Messages.ERROR_OCCURED.getValue();
    public static String NOTICE = Messages.NOTICE.getValue();
    public static String GENERAL_ERROR = Messages.ERROR_OCCURED.getValue();
    /**
     * This identifier is the parent container opf popups, to keep them all in one screen
     */
    public static Component identifier = new Frame(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());

    /**
     * Prompts the user with a text box
     * @param message
     * @return
     */
    public static String Enter_Value(Object... message) {
        String text = "";
        for (int i = 0; i < message.length; i++) {
            if (message[i] != null) {
                text += message[i].toString();
            }
        }
        return JOptionPane.showInputDialog(identifier, String.valueOf(text));
    }

    /**
     * A Y_N_dialog
     * @param text
     * @return
     */
    public static boolean Y_N_dialog(Object text) {
        return Y_N_dialog(text, Messages.ARE_YOU_SURE.toString());
    }

    /**
     * A Y_N_dialog
     * @param text
     * @param label 
     * @return
     */
    public static boolean Y_N_dialog(Object text, Object label) {
        if (JOptionPane.showConfirmDialog(identifier, prepareText(String.valueOf(text)), label.toString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(new Popup().getClass().getResource("/images/32/warning.png"))) == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A Y_N_dialog
     * @param parent
     * @param text
     * @param label
     * @return
     */
    public static boolean Y_N_dialog(Component parent, Object text, Object label) {
        if (JOptionPane.showConfirmDialog(parent, prepareText(String.valueOf(text)), label.toString(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(new Popup().getClass().getResource("/images/32/warning.png"))) == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A Ok or Cancel -dialog
     * @param text
     * @param label
     * @return
     */
    public static boolean OK_dialog(Object text, String label) {
        if (JOptionPane.showConfirmDialog(identifier, prepareText(String.valueOf(text)), label, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new javax.swing.ImageIcon(new Popup().getClass().getResource("/images/32/warning.png"))) == JOptionPane.OK_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Shows a notice
     * @param parent
     * @param text
     */
    public static void notice(Component parent, Object text) {
        JOptionPane.showMessageDialog(parent, prepareText(String.valueOf(text)), Popup.NOTICE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a warning
     * @param parent
     * @param text
     */
    public static void warn(Component parent, Object text) {
        JOptionPane.showMessageDialog(parent, prepareText(String.valueOf(text)), Popup.WARN, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows an error
     * @param parent
     * @param text
     */
    public static void error(Component parent, Object text) {
        JOptionPane.showMessageDialog(parent, prepareText(String.valueOf(text)), Popup.ERROR, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * A convenience method to show a notice popup with
     * the mainframe as parent
     * @param text
     */
    public static void warn(Object text) {
        JOptionPane.showMessageDialog(identifier, prepareText(String.valueOf(text)), Popup.WARN, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * A convenience method to show a notice popup with
     * the mainframe as parent
     * @param text
     */
    public static void notice(Object text) {
        JOptionPane.showMessageDialog(identifier, prepareText(String.valueOf(text)), Popup.NOTICE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * A convenience method to show a notice popup with
     * the mainframe as parent
     * @param text
     * @param boxWidth
     * @param boxLength
     */
    public static void notice(Object text, int boxWidth, int boxLength) {
        JOptionPane.showMessageDialog(identifier, prepareText(String.valueOf(text), boxWidth, boxLength), Popup.NOTICE, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * A convenience method to show an error popup with
     * the mainframe as parent
     * @param x
     */
    public static void error(Exception x) {
//        x.printStackTrace();
        error(identifier, x.getLocalizedMessage());
    }

    private static Object prepareText(String s) {
        return prepareText(s, 300, 80);
    }

    private static Object prepareText(String s, int boxWidth, int boxLength) {
        JTextArea text = new JTextArea(s);
        JScrollPane scroll = new JScrollPane(text);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setBorder(new EmptyBorder(1, 1, 1, 1));
        text.setBackground(JOptionPane.getRootFrame().getBackground());
        scroll.setBackground(JOptionPane.getRootFrame().getBackground());
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        scroll.setPreferredSize(new Dimension(boxWidth, boxLength));
        return scroll;
    }

    /**
     * Show a selection box
     * @param objects
     * @param message
     * @return the selected object or null
     */
    public static DatabaseObject SelectValue(ArrayList<DatabaseObject> objects, Object message) {
        return (DatabaseObject) JOptionPane.showInputDialog(identifier, message, "",
                JOptionPane.PLAIN_MESSAGE, (Icon) null, objects.toArray(), objects.get(0));
    }

    /**
     * Show a search box
     * @param t
     * @return the selected object or null
     */
    public static DatabaseObject SelectValue(Context t) {
        return Search2.showSearchFor(t);
//        return null;
    }

    /**
     * 
     * @param list
     * @param message
     */
    public static void notice(List list, Object message) {
        String t = message.toString() + "\n";

        for (int i = 0; i < list.size(); i++) {
            Object l = list.get(i);
            if (l instanceof DatabaseObject) {
                DatabaseObject databaseObject = (DatabaseObject) l;
                t += databaseObject.__getCname() + " [" + databaseObject.getDbIdentity() + "]" + "\n";
            } else {
                t += list.get(i) + "\n";
            }
        }
        Popup.notice(t, 300, list.size() * 5 + 100);
    }

    private Popup() {
    }
}
