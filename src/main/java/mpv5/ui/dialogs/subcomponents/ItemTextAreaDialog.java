/*
 * This file is part of YaBS.
 * 
 *    YaBS is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 * 
 *    YaBS is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 * 
 *    You should have received a copy of the GNU General Public License
 *    along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.ui.dialogs.subcomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.JTable;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.objects.Product;
import mpv5.db.objects.SubItem;
import mpv5.db.objects.User;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.utils.models.MPTableModel;

public class ItemTextAreaDialog extends javax.swing.JDialog implements KeyListener, DatabaseObejctReceiver {

    private JTable parentTable;
    private mpv5.db.objects.Product product;

    /**
     * Creates new form ItemTextAreaDialog
     */
    public ItemTextAreaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setResizable(false);
        setUndecorated(true);
        initComponents();
        textArea.addKeyListener(this);
//        okButton.setToolTipText("ctrl+Enter");
        cancelButton.setToolTipText("Esc");
        labeledCombobox1.setContext(Context.getProduct());
        labeledCombobox1.setSearchOnEnterEnabled(true);
        labeledCombobox1.setReceiver(this);
        labeledCombobox1.setToolTipText(Messages.SEARCHABLE.toString());

        labeledSpinner1.setValue(1);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            textArea.grabFocus();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        labeledCombobox1 = new mpv5.ui.beans.LabeledCombobox();
        labeledSpinner1 = new mpv5.ui.beans.LabeledSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setName("textArea"); // NOI18N
        jScrollPane1.setViewportView(textArea);

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();// NOI18N
        okButton.setText(bundle.getString("okButton")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(bundle.getString("cancelButton")); // NOI18N
        cancelButton.setActionCommand("CANCEL"); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N

        labeledCombobox1.set_Label(bundle.getString("ItemTextAreaDialog.labeledCombobox1._Label")); // NOI18N
        labeledCombobox1.setName("labeledCombobox1"); // NOI18N

        labeledSpinner1.set_Label(bundle.getString("ItemTextAreaDialog.labeledSpinner1._Label")); // NOI18N
        labeledSpinner1.setName("labeledSpinner1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labeledSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(11, 11, 11))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                    .addComponent(labeledCombobox1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(labeledCombobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(labeledSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        setRow();
//        Log.Debug(this, evt);
    }//GEN-LAST:event_okButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private mpv5.ui.beans.LabeledCombobox labeledCombobox1;
    private mpv5.ui.beans.LabeledSpinner labeledSpinner1;
    public javax.swing.JButton okButton;
    public javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == e.VK_ENTER) {
            ActionListener[] listeners = okButton.getActionListeners();
            ActionEvent actionEvent = new ActionEvent(okButton, ActionEvent.ACTION_PERFORMED, okButton.getActionCommand());
            for (int i = 0; i < listeners.length; i++) {
                listeners[i].actionPerformed(actionEvent);
            }
            setRow();
        } else if (e.getKeyCode() == e.VK_ESCAPE) {
            ActionListener[] listeners = cancelButton.getActionListeners();
            ActionEvent actionEvent = new ActionEvent(cancelButton, ActionEvent.ACTION_PERFORMED, cancelButton.getActionCommand());
            for (int i = 0; i < listeners.length; i++) {
                listeners[i].actionPerformed(actionEvent);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void receive(DatabaseObject obj) {
        if (obj.getContext().equals(Context.getProduct())) {
            this.product = (Product) obj;
            textArea.setText(new SubItem(product).__getDescription());
        } else {
            textArea.setText(obj.__getCname());
        }
    }

    private synchronized void setRow() {
        SubItem p;
        MPTableModel m = (MPTableModel) getParentTable().getModel();
        if (product == null) {
            p = SubItem.getFromModel(m, getParentTable().getSelectedRow());
        } else {
            p = new SubItem(product);
//            p.setQuantityvalue(new BigDecimal(labeledSpinner1.get_Value().toString()));
            p.setExternalvalue(product.findPriceFor(p.__getCountvalue().doubleValue()));
        }
        p.setDescription(textArea.getText());
        p.setQuantityvalue(new BigDecimal(labeledSpinner1.get_Value().toString()));

        m.setRowAt(p.getRowData(getParentTable().getSelectedRow()), getParentTable().getSelectedRow(), 1, 14);
        labeledCombobox1.setSelectedIndex(-1);
        product = null;
        setVisible(false);
    }

    /**
     * @return the parentTable
     */
    public JTable getParentTable() {
        return parentTable;
    }

    /**
     * @param parentTable the parentTable to set
     */
    public void setParentTable(JTable parentTable) {
        this.parentTable = parentTable;
    }

    public void setQuantity(Double valueOf) {
        labeledSpinner1.setValue(valueOf);
    }
}
