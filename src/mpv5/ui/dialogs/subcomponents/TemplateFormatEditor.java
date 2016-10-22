/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * TemplateFormatEditor.java
 *
 * Created on Oct 9, 2009, 10:08:54 AM
 */
package mpv5.ui.dialogs.subcomponents;

import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;

/**
 *
 * @author andreasw
 */
public class TemplateFormatEditor extends javax.swing.JPanel {

    private static TemplateFormatEditor me;
    private final JTextField rec;

    public static TemplateFormatEditor instanceOf(JTextField txt) {
        if (me == null) {
            me = new TemplateFormatEditor(txt);
        }
        try {
            String format = txt.getText();
            int[] f = new int[format.split(",").length];
            for (int i = 0; i < f.length; i++) {
                f[i] = Integer.valueOf(format.split(",")[i]);
            }
            TableColumnModel columnModel = me.jTable1.getColumnModel();

            //Reset the table!
            while (columnModel.getColumnCount() > 0) {
                columnModel.removeColumn(columnModel.getColumn(0));
            }

            for (int i = 0; i < original.length; i++) {
                columnModel.addColumn(original[i]);
            }

            for (int ix = 0; ix < me.jTable1.getColumnCount(); ix++) {
                me.jTable1.setValueAt(Boolean.FALSE, 0, ix);
            }
            // set the format values
            for (int i = 0; i < f.length; i++) {
                int j = f[i];
                me.jTable1.setValueAt(Boolean.TRUE, 0, j - 1);
            }

            //re-arrange!
            me.setColumnOrder(f, columnModel);
        } catch (Exception n) {
            Log.Debug(n);
            Popup.error(n);
        }
        return me;
    }
    
private static TableColumn original[];

    /**
     * Creates new form TemplateFormatEditor
     *
     * @param rec
     */
    private TemplateFormatEditor(JTextField rec) {
        initComponents();
        original = new TableColumn[this.jTable1.getColumnCount()];
        for (int i = 0; i < this.jTable1.getColumnCount(); i++) {
            original[i] = this.jTable1.getColumnModel().getColumn(i);
        }
        this.rec = rec;
    }

    /**
     * This method is called from within the constructor to
     * initialize the form. WARNING: Do NOT modify this
     * code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setName("Form"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true),  new Boolean(true)}
            },
            new String [] {
                "Count", "Quantity", "Measure", "Text", "Net", "Totalnet", "Taxrate", "Tax", "Total", "Link", "Optional", "Discount", "Totalnet - Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        jButton1.setText(bundle.getString("TemplateFormatEditor.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setFormat();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void setFormat() {
        TableModel t = jTable1.getModel();
        String format = "";
        for (int i = 0; i < t.getColumnCount(); i++) {
            int n = jTable1.getColumnModel().getColumn(i).getModelIndex() + 1;
            if (((Boolean) jTable1.getValueAt(0, i))) {
                format += n + ",";
            }
        }
        if (format.length() > 0) {
            rec.setText(format.substring(0, format.length() - 1));
        } else {
            rec.setText("");
        }
    }

    private void setColumnOrder(int[] indices, TableColumnModel columnModel) {
        ArrayList<TableColumn> columns = new ArrayList<TableColumn>();
        
        for (int i = 0; i < indices.length; i++) {
            columns.add(columnModel.getColumn(this.jTable1.convertColumnIndexToModel(indices[i]-1)));
        }

        while (columnModel.getColumnCount() > 0) {
            TableColumn c = columnModel.getColumn(0);
            if (!columns.contains(c)) {
                columns.add(c);
            }
            columnModel.removeColumn(c);
        }

       for (TableColumn c: columns) {
           columnModel.addColumn(c);
       }
    }
}
