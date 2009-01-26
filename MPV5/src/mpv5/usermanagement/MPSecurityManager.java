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
package mpv5.usermanagement;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.UIManager;
import mpv5.db.common.Context;
import mpv5.db.common.NodataFoundException;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPV5View;
import mpv5.utils.text.MD5HashGenerator;

/**
 *
 * @author Andreas
 */
public class MPSecurityManager {

    public static final int RIGHT_TO_VIEW = 3;
    public static final int RIGHT_TO_EXPORT = 2;
    public static final int RIGHT_TO_EDIT = 1;
    public static final int RIGHT_TO_CREATE = 0;
    public static final int VIEW = 3;
    public static final int EXPORT = 2;
    public static final int EDIT = 1;
    public static final int CREATE = 0;
    public static ArrayList<Context> securedContexts = Context.getSecuredContexts();
    private static String usern;

    /**
     * Checks whether the currently logged in user has to right to do this
     * action in the given context
     * @param context
     * @param action
     * @return True if the highest right of the user is equal or higher as
     * the right to do requested action
     */
    public static Boolean check(Context context, int action) {
        for (Context item : securedContexts) {
            if (item.getDbIdentity().equals(context.getDbIdentity())) {
                if (MPV5View.getUser().__getINThighestright() <= action) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

    public static boolean checkAuth(String username, String password) {
        User usern1 = new User();
        try {
            if (usern1.fetchDataOf(username)) {
                try {
                    
                    System.out.println(usern1.__getPassword().toUpperCase());
                    if (MD5HashGenerator.getInstance().hashData(password.getBytes()).equalsIgnoreCase(usern1.__getPassword())) {
                        MPV5View.setUser(usern1);
                        return true;
                    } else {
                        return false;
                    }
                } catch (NoSuchAlgorithmException ex) {
                    Log.Debug(MPSecurityManager.class, ex);
                    return false;
                }
            } else {
                return false;
            }
        } catch (NodataFoundException ex) {
            Popup.notice(Messages.USER_NOT_FOUND + username);
            return false;
        }
    }

    public static String getActionName(int action) {

        switch (action) {

            case CREATE:
                return Messages.ACTION_CREATE;

            case EDIT:
                return Messages.ACTION_EDIT;

            case EXPORT:
                return Messages.ACTION_EXPORT;

            case VIEW:
                return Messages.ACTION_VIEW;
        }

        return null;
    }
}
