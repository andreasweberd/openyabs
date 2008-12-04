/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mpv5.handling;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public abstract class DatabaseObject {
    public Context context;
    public Integer id = 0;
    public boolean isSaved = false;
    public boolean readonly = false;
    public abstract ArrayList<Method> getVars();
}