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

import java.awt.Font;
import javax.swing.JComboBox;
import mpv5.handler.VariablesHandler.GENERIC_VARS;
import mpv5.utils.models.MPComboBoxModelItem;

/**
 *
 *  
 */
public class LabeledCombobox extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private String _text;
    private String _label;
    private Class clazz;

    /** Creates new form LabeledTextField */
    public LabeledCombobox() {
        initComponents();

    }

    public JComboBox getComboBox() {
        return jComboBox1;
    }

    /**
     * Delegates to setModel(Object[][])
     * @param values
     */
    public void setModel(Enum[] values) {
        String[][] val = new String[values.length][2];
        for (int i = 0; i < values.length; i++) {
            Enum enum1 = values[i];
            val[i][0] = enum1.name();
            val[i][1] = enum1.toString();
        }
        setModel(val);
    }

    /**
     * Delegates to setModel(MPComboBoxModelItem.toModel(data));
     * {id (hidden), value (shown in the list)}
     * @param data
     */
    public void setModel(Object[][] data) {
        jComboBox1.setModel(MPComboBoxModelItem.toModel(data));
    }

    public MPComboBoxModelItem getSelectedItem() {
        return (MPComboBoxModelItem) jComboBox1.getSelectedItem();
    }


//    public void setLabelFont(Font font) {
//        setFont(font);
//
//    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setOpaque(false);

        jLabel1.setText("text");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, 111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the _text
     */
    public Object get_Value() {
        return jComboBox1.getSelectedItem();
    }

    /**
     * @param text the _text to set
     */
    public void set_Value(Object text) {
        this._text = String.valueOf(text);
        jComboBox1.setSelectedItem(text);

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

    public void set_LabelFont(Font font) {
//        if (font != null) {
//            jLabel1.setFont(font);
//        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        jLabel1.setEnabled(enabled);
        jComboBox1.setEnabled(enabled);
    }
}
