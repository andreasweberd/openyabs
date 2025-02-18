/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LabeledTextField.java
 *
 * Created on 20.11.2008, 19:26:39
 */
package mpv5.ui.beans;

import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import javax.swing.JLabel;
import javax.swing.JTextField;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.panels.DataPanel;
import mpv5.utils.numberformat.FormatNumber;
import mpv5.utils.ui.TextFieldUtils;

/**
 *
 *  
 */
public final class LabeledTextField extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private String _text;
    private String _label;
    private Class clazz; // = Double.class;//default now
    private DataPanel parent;
    private Context context;
    private String searchField;
    private boolean searchOnEnterEnabled;
    private Object displayingObject;
    private int labelWidth=100;
    private boolean _invisible;

   
    /** Creates new form LabeledTextField */
    public LabeledTextField() {
        initComponents();
        setVisible(!_invisible);
    }
    
     /** Creates new form LabeledTextField
     * @param initComps */
    public LabeledTextField(boolean initComps) {
        if (initComps) {
            initComponents();
        }
    }

    /**
     *
     * @param d
     * @param clazz
     */
    public LabeledTextField(Object d, Class clazz) {
        initComponents();
        set_ValueClass(clazz);
        setText(d);
    }

    /**
     *
     * @return
     */
    public JTextField getTextField() {
        return jTextField1;
    }

    /**
     *
     * @return
     */
    public JLabel getLabelField() {
        return jLabel1;
    }
    
    /**
     *
     * @param w
     */
    public void setLabelWidth(int w){
        labelWidth=w;
    }

    /**
     * Determines if the field has some text
     * @return
     */
    public boolean hasText() {
        return jTextField1.getText() != null && jTextField1.getText().length() > 0;
    }

    public void setText(String text) {
        setText((Object) text);
    }

//    public void setLabelFont(Font font) {
//        setFont(font);
//
//    }
    public void doInitComponents(){
        initComponents();
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("text");
        jLabel1.setMaximumSize(new java.awt.Dimension(300, 50));
        jLabel1.setPreferredSize(new java.awt.Dimension(labelWidth, 20));
        add(jLabel1);

        jTextField1.setDisabledTextColor(new java.awt.Color(0, 51, 51));
        jTextField1.setPreferredSize(new java.awt.Dimension(100, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        DatabaseObject data;
        if (searchOnEnterEnabled) {
            try {
                if (searchField != null) {
                    data = DatabaseObject.getObject(context, searchField, jTextField1.getText());
                } else {
                    data = DatabaseObject.getObject(context, jTextField1.getText());
                }
                parent.setDataOwner(data, true);
               // Log.Debug(this, "Data found: " + data);
               // Log.PrintArray(data.getValues3().toArray(new Object[][]{}));
            } catch (NodataFoundException ex) {
                Log.Debug(this, "Data NOT found: " + jTextField1.getText());
                TextFieldUtils.blinkerRed(jTextField1);
            }
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _text
     */
    public String get_Text() {
        return getText();
    }

    /**
     * @param text the _text to set
     */
    public void setText(Object text) {
        if (text == null) {
            text = "";
        }
        if (clazz != null && (clazz == Double.class || clazz == BigDecimal.class)) {
            try {
                this._text = FormatNumber.formatDezimal((Number) text);
            } catch (Exception e) {
                try {
                    this._text = FormatNumber.formatDezimal(new BigDecimal(String.valueOf(text)));
                } catch (Exception ex) {
                    this._text = String.valueOf(text);
                }
            }
        } else {
            this._text = String.valueOf(text);
        }
        jTextField1.setText(_text);
    }

    /**
     * @param text the _text to set
     */
    public void set_Text(String text) {
        setText(text);
    }

    /**
     * @return the _label
     */
    public String get_Label() {
        return jLabel1.getText();
    }

    /**
     * @param label the _label to set
     */
    public void set_Label(String label) {
        this._label = label;
        jLabel1.setText(_label);
        this.setToolTipText(_text);
        jLabel1.setToolTipText(_text);
    }

    /**
     * @param label the _label to set
     */
    public void setLabel(String label) {
        set_Label(label);
    }

    public void set_LabelFont(Font font) {
//        if (font != null) {
//            jLabel1.setFont(font);
//        }
    }

    @Override
    public void setEnabled(boolean enabled) {
//        jLabel1.setEnabled(enabled);
        jTextField1.setEnabled(enabled);
    }

    /**
     * Set the class of possible entries. Currently supported:
     * <li>Integer - invalid values will be replaced with 0
     *<li>Double - invalid values will be replaced with 0.0
     * @param clazz
     */
    public void set_ValueClass(Class clazz) {
        this.clazz = clazz;
    }

    public String getText() {
        if (clazz == Integer.class) {
            try {
                Integer.valueOf(jTextField1.getText());
//$2WHITE);
            } catch (NumberFormatException numberFormatException) {
                Log.Debug(this, numberFormatException.getMessage());
                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText("0");
            }
        } else if (clazz == Double.class) {
            try {
                FormatNumber.parseDezimal(jTextField1.getText());
//$2WHITE);
            } catch (NumberFormatException numberFormatException) {
                Log.Debug(this, numberFormatException.getMessage());
                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText("0.0");
            }
        } else if (clazz == BigDecimal.class) {
            try {
                FormatNumber.parseDezimal(jTextField1.getText());
//$2WHITE);
            } catch (NumberFormatException numberFormatException) {
                Log.Debug(this, jTextField1.getText());
                Log.Debug(this, numberFormatException.getMessage());

                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText("0.0");
            }
        }
        String txt = jTextField1.getText();
        if (txt == null) {
            txt = "";
        }
        return txt;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(DataPanel parent) {
        this.parent = parent;
    }

    /**
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * @return the searchOnEnterEnabled
     */
    public boolean isSearchOnEnterEnabled() {
        return searchOnEnterEnabled;
    }

    /**
     * @param searchOnEnterEnabled the searchOnEnterEnabled to set
     */
    public void setSearchOnEnterEnabled(boolean searchOnEnterEnabled) {
        if (searchOnEnterEnabled) {
            jTextField1.setToolTipText(Messages.SEARCHABLE.toString());
        }
        this.searchOnEnterEnabled = searchOnEnterEnabled;
    }

    /**
     *
     * @param string
     */
    public void setSearchField(String string) {
        searchField = string;
    }

    /**
     * Set the object displayed by this text field for later use
     * @param selectedItem
     */
    public void setDisplayingObject(Object selectedItem) {
        this.displayingObject = selectedItem;
        setText(selectedItem);
    }

    /**
     * @return the displayingObject
     */
    public Object getDisplayingObject() {
        return displayingObject;
    }

    @SuppressWarnings("unchecked")
    public <T extends Object> T getValue(T classtemplate) {
        if (clazz != classtemplate.getClass()) {
            throw new IllegalArgumentException("Classtemplate must match value class! " + classtemplate.getClass() + ", " + clazz);
        }
        if (clazz == Integer.class) {
            try {
                Integer.valueOf(jTextField1.getText());
//$2WHITE);
                return (T) Integer.valueOf(jTextField1.getText());
            } catch (Exception numberFormatException) {
                Log.Debug(this, numberFormatException.getMessage());
                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText(classtemplate.toString());
            }
        } else if (clazz == Double.class) {
            try {
                FormatNumber.parseDezimal(jTextField1.getText());
//$2WHITE);
                return (T) new Double(FormatNumber.parseDezimal(jTextField1.getText()).doubleValue());
            } catch (Exception numberFormatException) {
                Log.Debug(this, numberFormatException.getMessage());
                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText(FormatNumber.formatDezimal((Double)classtemplate));
            }
        } else if (clazz == BigDecimal.class) {
            try {
                FormatNumber.parseDezimal(jTextField1.getText());
//$2WHITE);
                return (T) FormatNumber.parseDezimal(jTextField1.getText());
            } catch (Exception numberFormatException) {
                Log.Debug(this, jTextField1.getText());
                Log.Debug(this, numberFormatException.getMessage());

                TextFieldUtils.blinker(jTextField1, Color.gray);
                jTextField1.setText(FormatNumber.formatDezimal((BigDecimal)classtemplate));
            }
        }
        return classtemplate;
    }

    /**
     * Forces a user input
     * @param nonNull
     * @param value
     * @return
     */
    public String getText(boolean nonNull, Object value) {
        String text = getText();
        if (text == null || text.length() == 0) {
            text = Popup.Enter_Value(Messages.ENTER_VALUE, value);
        }
        if (text == null || text.length() == 0) {
            text = String.valueOf(value);
        }
        return text;
    }

    /**
     * @return the labelWidth
     */
    public int getLabelWidth() {
        return labelWidth;
    }
    
        /**
     * @return the _invisible
     */
    public boolean isInvisible() {
        return _invisible;
    }

    /**
     * @param _invisible the _invisible to set
     */
    public void setInvisible(boolean _invisible) {
        this._invisible = _invisible;
    }
}
