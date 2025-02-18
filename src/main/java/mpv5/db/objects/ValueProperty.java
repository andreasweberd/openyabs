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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JComponent;

import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.QueryCriteria2;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.QueryParameter;
import mpv5.logging.Log; 
import mpv5.utils.text.RandomText;

/**
 *
 *  
 */
public final class ValueProperty extends DatabaseObject {

    private static final long serialVersionUID = 1L;

    public ValueProperty() {
        setContext(Context.getValueProperties());
    }

    /**
     * Create a new property
     * @param key
     * @param value
     * @param owner
     */
    public ValueProperty(final String key, final Serializable value, final DatabaseObject owner) {
        this();
        if (key == null) {
            throw new NullPointerException();
        }
        setValueObj(owner);
        setContextids(owner.getContext().getId());
        setObjectids(owner.__getIDS());
        setGroupsids(owner.__getGroupsids());
        setCname(key);
    }

    /**
     * Create a new property
     * @param key
     * @param value
     * @param owner
     * @param group  
     */
    public ValueProperty(final String key, final Serializable value, final Context owner, final Group group) {
        this();
        if (owner == null) {
            throw new NullPointerException();
        }
        setValueObj(value);
        setContextids(owner.getId());
        setObjectids(0);
        setGroupsids(group.__getIDS());
        setCname(key == null ? RandomText.getString() : key);
    }

    /**
     * Create a new property or update an existing one
     * @param key
     * @param value
     * @param owner
     */
    public static synchronized void addOrUpdateProperty(final String key, final Serializable value, final DatabaseObject owner) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (owner != null && key.length() > 0) {
            ValueProperty val;
            try {
                val = getProperty(owner, key);
            } catch (NodataFoundException ex) {
                val = new ValueProperty(key, value, owner);
            }
            val.setValueObj(value);
            val.save(true);
        }
    }

    /**
     * Create a new property or update an existing one
     * @param key
     * @param value
     * @param owner
     * @param group  
     */
    public static synchronized void addOrUpdateProperty(final String key, final Serializable value, final Context owner, final Group group) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (owner != null && key.length() > 0) {
            ValueProperty val;
            try {
                val = getProperty(owner, key);
            } catch (NodataFoundException ex) {
                val = new ValueProperty(key, value, owner, group);
            }
            val.setValueObj(value);
            val.save(true);
        }
    }

    /**
     * Create a new property or update an existing one
     * @param key
     * @param sourceClass 
     * @param value
     * @param owner
     */
    public static synchronized void addOrUpdateProperty(final String key, final Class<?> sourceClass, final Serializable value, final DatabaseObject owner) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (owner != null && key.length() > 0) {
            ValueProperty val;
            try {
                val = getProperty(owner, key);
            } catch (NodataFoundException ex) {
                val = new ValueProperty(key, value, owner);
            }
            val.setValueObj(value);
            val.setClassname(sourceClass.getCanonicalName());
            val.save(true);
        }
    }

    /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return
     * @throws NodataFoundException
     */
    public static synchronized ValueProperty getProperty(final DatabaseObject owner, final String key) throws NodataFoundException {
        if (key == null) {
            throw new NullPointerException();
        }
        QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
        c.addAndCondition("cname", key);
        c.addAndCondition("objectids", owner.__getIDS());
        ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
        return (ValueProperty) objects.get(0);
    }
    
    /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return 
     */
    public static synchronized boolean hasProperty(final DatabaseObject owner, final String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        QueryCriteria2 c = new QueryCriteria2();
        c.and(new QueryParameter(Context.getValueProperties(), "cname", key, QueryParameter.EQUALS));
        c.and(new QueryParameter(Context.getValueProperties(), "objectids", owner.__getIDS(), QueryParameter.EQUALS));
        return QueryHandler.instanceOf(Context.getValueProperties()).checkExistance(c);
    }
    
     /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return 
     */
    public static synchronized boolean hasProperty(final Context owner, final String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        QueryCriteria2 c = new QueryCriteria2();
        c.and(new QueryParameter(Context.getValueProperties(), "cname", key, QueryParameter.EQUALS));
        c.and(new QueryParameter(Context.getValueProperties(), "objectids", 0, QueryParameter.EQUALS));
        c.and(new QueryParameter(Context.getValueProperties(), "contextids", owner.getId(), QueryParameter.EQUALS));
        return QueryHandler.instanceOf(Context.getValueProperties()).checkExistance(c);
    }

    /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return
     * @throws NodataFoundException
     */
    public static synchronized ValueProperty getProperty(final Context owner, final String key) throws NodataFoundException {
        if (key == null) {
            throw new NullPointerException();
        }
        QueryCriteria c = new QueryCriteria("contextids", owner.getId());
        c.addAndCondition("cname", key);
        c.addAndCondition("objectids", 0);
        ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
        return (ValueProperty) objects.get(0);
    }

    /**
     * Search for a specific property
     * @param owner
     * @param key
     * @return
     * @throws NodataFoundException
     */
    public static synchronized ValueProperty getProperty(final DatabaseObject owner, final Class<?> sourceClass, final String key) throws NodataFoundException {
        if (key == null) {
            throw new NullPointerException();
        }
        QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
        c.addAndCondition("cname", key);
        c.addAndCondition("classname", sourceClass.getCanonicalName());
        c.addAndCondition("objectids", owner.__getIDS());
        ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
        return (ValueProperty) objects.get(0);
    }

    /**
     * @param owner
     * @param key  
     */
    public static synchronized void deleteProperty(final DatabaseObject owner, final String key) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
            if (key != null) {
                c.addAndCondition("cname", key);
            }
            c.addAndCondition("objectids", owner.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            for (int i = 0; i < objects.size(); i++) {
                DatabaseObject databaseObject = objects.get(i);
                databaseObject.delete();
            }
        } catch (NodataFoundException ex) {
        }
    }

    /**
     * @param owner
     * @param key  
     */
    public static synchronized void deleteProperty(final Context owner, final String key) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getId());
            if (key != null) {
                c.addAndCondition("cname", key);
            }
            c.addAndCondition("objectids", 0);
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            for (int i = 0; i < objects.size(); i++) {
                DatabaseObject databaseObject = objects.get(i);
                databaseObject.delete();
            }
        } catch (NodataFoundException ex) {
        }
    }

    /**
     * @param owner
     * @param key  
     */
    public static synchronized void deleteProperty(final Context owner, final Group group, final String key) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getId());
            if (key != null) {
                c.addAndCondition("cname", key);
            }
            c.addAndCondition("objectids", 0);
            c.addAndCondition("groupsids", group.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            for (int i = 0; i < objects.size(); i++) {
                DatabaseObject databaseObject = objects.get(i);
                databaseObject.delete();
            }
        } catch (NodataFoundException ex) {
        }
    }

    /**
     * Search for a specific property
     * @param owner
     * @param sourceClass 
     * @param key
     */
    public static synchronized void deleteProperty(final DatabaseObject owner, final Class<?> sourceClass, final String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
            c.addAndCondition("cname", key);
            c.addAndCondition("classname", sourceClass.getCanonicalName());
            c.addAndCondition("objectids", owner.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            for (int i = 0; i < objects.size(); i++) {
                DatabaseObject databaseObject = objects.get(i);
                databaseObject.delete();
            }
        } catch (NodataFoundException ex) {
        }
    }

    /**
     * Find all properties for the given owner
     * @param owner
     * @return A list of properties (possibly empty)
     */
    @SuppressWarnings("unchecked")
    public static synchronized List<ValueProperty> getProperties(final DatabaseObject owner) {
        try {
            QueryCriteria c = new QueryCriteria("contextids", owner.getContext().getId());
            c.addAndCondition("objectids", owner.__getIDS());
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c);
            return DatabaseObject.toObjectList(objects, new ValueProperty());
        } catch (NodataFoundException ex) {
            Log.Debug(ValueProperty.class, ex.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Fetches all group-bound properties
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static List<ValueProperty> getGroupProperties() {
        try {
            QueryCriteria2 c = new QueryCriteria2();
            c.and(new QueryParameter(Context.getValueProperties(), "objectids", 0, QueryParameter.EQUALS));
            List<Group> groups = Group.getObjects(Context.getGroup());
            for (Group g : groups) {
                c.or(new QueryParameter(Context.getValueProperties(), "groupsids", g.__getIDS(), QueryParameter.EQUALS));
            }
            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c, false);
            return DatabaseObject.toObjectList(objects, new ValueProperty());
        } catch (NodataFoundException ex) {
            Log.Debug(ValueProperty.class, ex.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Find all properties for the given owner
     * @param owner
     * @return A list of properties (possibly empty)
     */
    @SuppressWarnings("unchecked")
    public static synchronized List<ValueProperty> getProperties(final Context owner, final Group p) {
        try {
            QueryCriteria2 c = new QueryCriteria2();
            c.and(new QueryParameter(Context.getValueProperties(), "contextids", owner.getId(), QueryParameter.EQUALS));
            c.and(new QueryParameter(Context.getValueProperties(), "objectids", 0, QueryParameter.EQUALS));
            if (p != null && !p.isRoot()) {
                List<Group> groups = p.getChildGroups();
                for (Group g : groups) {
                    c.or(new QueryParameter(Context.getValueProperties(), "groupsids", g.__getIDS(), QueryParameter.EQUALS));
                }
            }

            ArrayList<DatabaseObject> objects = DatabaseObject.getObjects(Context.getValueProperties(), c, false);
            return DatabaseObject.toObjectList(objects, new ValueProperty());
        } catch (NodataFoundException ex) {
            Log.Debug(ValueProperty.class, ex.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Add or update multiple properties
     * @param values
     * @param owner
     */
    public synchronized static void addOrUpdateProperties(final Map<String, Serializable> values, final DatabaseObject owner) {
        for (Map.Entry<String, Serializable> element : values.entrySet()) {
            addOrUpdateProperty(element.getKey(), element.getValue(), owner);
        }
    }
    private String sourceclassname = "";
    private Serializable valueObj;
    private int contextids;
    private int objectids;

    @Override
    public JComponent getView() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Generates a xml string representation of the value object
     * @return the value
     */
    @Persistable(false)
    public synchronized String __getValue() {
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            XMLEncoder xmlEncoder = new XMLEncoder(io);
            xmlEncoder.writeObject(getValueObj());
            xmlEncoder.close();
            String x = io.toString("UTF-8");
//            Log.Debug(io, x);
            return x;
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            //shall not happen on utf-8
            return null;
        }
    }

    /**
     * 
     * @param <T>
     * @param target
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public synchronized <T extends Object> T getValue(T target) throws Exception {

        Log.Debug(this, "trying to get a " + target.getClass() + " from " + getValue().getClass());

        if (target.getClass().isAssignableFrom(getValue().getClass())) {
            return (T) valueObj;
        } else {
            throw new UnsupportedOperationException(target + " is not  assignable to " + getValue().getClass());
        }
    }

    /**
     * Return the Class of the source, see {@link #setClassname(java.lang.String) }
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> getClazz() throws ClassNotFoundException {
        return Class.forName(__getClassname());
    }

    /**
     * You should not need to programmatically call this method, use {@link #defineValueObj(Serializable valueObj)}
     * @param value the value to set
     */
    public void setValue(byte[] value) {

        try {
            value = Compressor.decompress(value);
        } catch (Exception e) {
            Log.Debug(this, e.getMessage());
        }

        ByteArrayInputStream io = new ByteArrayInputStream(value);
        try {
            XMLDecoder d = new XMLDecoder(io);
            setValueObj((Serializable) d.readObject());
        } catch (Exception exc) {
            synchronized (this) {
                Log.Debug(exc);
                Log.Debug(this, new String(value));
                if(delete()) {
                    Log.Debug(this, "Removed faulty VP from database!");
                }
            }
        }
    }
//
//    /**
//     * You should not need to programmatically call this method, use {@link #defineValueObj(Serializable valueObj)}
//     * @param value the value to set
//     */
//    public void setValue(final String value) {
//
//
//        try {
//            byte[] bytes = value.getBytes("UTF-8");
//            try {
//                bytes = Compressor.decompress(bytes);
//            } catch (Exception e) {
//                Log.Debug(this, e.getMessage());
//            }
//            ByteArrayInputStream io = new ByteArrayInputStream(bytes);
//            XMLDecoder d = new XMLDecoder(io);
//            setValueObj((Serializable) d.readObject());
//        } catch (Exception unsupportedEncodingException) {
//            synchronized (this) {
//                Log.Debug(unsupportedEncodingException);
//                Log.Debug(this, value);
//            }
//        }
//    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return null;
    }

    /**
     * @return the classname
     */
    public String __getClassname() {
        return sourceclassname;
    }

    /**
     * @param sourceclassname the classname from where the value object originates (optional)
     */
    public void setClassname(String sourceclassname) {
        this.sourceclassname = sourceclassname;
    }

    /**
     * @return the contextids
     */
    public int __getContextids() {
        return contextids;
    }

    /**
     * @param contextids the contextids to set
     */
    public void setContextids(int contextids) {
        this.contextids = contextids;
    }

    /**
     * @return the objectids
     */
    public int __getObjectids() {
        return objectids;
    }

    /**
     * @param objectids the objectids to set
     */
    public void setObjectids(int objectids) {
        this.objectids = objectids;
    }

    /**
     * @return the valueObj
     */
    public Object getValueObj() {
        return valueObj;
    }

    /**
     * @param valueObj the valueObj to set
     */
    @Persistable(false)
    public void setValueObj(Serializable valueObj) {
        this.valueObj = valueObj;
        __getValue();//generate xml
//        Log.Debug(this, __getValue());
    }

    /**
     * Get the key for this property, maps to {@link #__getCname()} here
     * @return A String, never null
     */
    public String getKey() {
        return __getCname();
    }

    /**
     * Get the value for this property, maps to {@link #getValueObj()} here 
     * @return An object, maybe null
     */
    public Object getValue() {
        return getValueObj();
    }

    @Override
    public String toString() {
        return getKey() + ": " + String.valueOf(getValueObj()) + " [" + __getClassname() + "]";
    }

    /**
     *
     * @param key
     */
    @Override
    public void setCname(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        super.setCname(key);
    }

    /**
     * Set the key for this property
     * @param key
     */
    @Persistable(false)
    public void setKey(String key) {
        setCname(key);
    }

    @Override
    public boolean save(boolean silent) {
        return save();
    }

    @Override
    public boolean save() {

        try {
            //            ByteArrayInputStream input = new ByteArrayInputStream(__getValue().getBytes("Utf-8"));
            byte[] bytes = __getValue().getBytes("Utf-8");
            Log.Debug(this, "Compressing a value of size: " + bytes.length);
            bytes = Compressor.compress(bytes);
            Log.Debug(this, "Compressed size: " + bytes.length);
            if (ids <= 0) {
                Log.Debug(this, "Inserting new dataset into: " + this.getContext());
                setDateadded(new Date());
                setIntaddedby(mpv5.db.objects.User.getCurrentUser().__getIDS());
                ids = QueryHandler.instanceOf().clone(getContext()).insertValueProperty(new ByteArrayInputStream(bytes), super.collect(), null);
                Log.Debug(this, "The inserted row has id: " + ids);
            } else {
                Log.Debug(this, "Updating dataset: " + ids + " within context '" + getContext() + "'");
                QueryHandler.instanceOf().clone(getContext()).updateValueProperty(ids, new ByteArrayInputStream(bytes), super.collect(), null);
            }
            return true;
        } catch (Exception e) {
            Log.Debug(e);
            return false;
        }
    }
}

class Compressor {

    public static byte[] compress(byte[] content) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.err.printf("Compression ratio %f\n", (1.0f
//                * content.length / byteArrayOutputStream.size()));
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] decompress(byte[] contentBytes) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            org.apache.commons.io.IOUtils.copy(new GZIPInputStream(new ByteArrayInputStream(contentBytes)), out);
        } catch (IOException e) {
//            System.err.println(new String(contentBytes));
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }
}
