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


import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import mpv5.db.common.Context;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryCriteria2;
import mpv5.db.common.QueryHandler;
import mpv5.db.common.QueryParameter;
import mpv5.db.common.ReturnValue;
import mpv5.db.objects.Product;
import mpv5.db.objects.SubItem;
import mpv5.logging.Log;
import mpv5.ui.panels.DataPanel;
import mpv5.utils.date.vTimeframe;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.models.MPComboboxModel;
import mpv5.utils.models.MPTableModel;

public class ProductSelectDialog2 extends javax.swing.JDialog  {

    protected JTable table;

    /** Creates new form ItemTextAreaDialog */
    public ProductSelectDialog2(java.awt.Frame parent, boolean modal, JTable table) {
        super(parent, modal);
        this.table = table;
        setResizable(false);
        setUndecorated(true);
        initComponents();   
        InputMap inputMap = getIDTextField().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
        getIDTextField().getActionMap().put(inputMap.get(enter),new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ActionListener[] listeners = okButton.getActionListeners();
                ActionEvent actionEvent = new ActionEvent(okButton, ActionEvent.ACTION_PERFORMED, okButton.getActionCommand());
                for (int i = 0; i < listeners.length; i++) {
                    listeners[i].actionPerformed(actionEvent);
                }
            }
        });
        KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
        inputMap.put(esc, "escape");
        ((JComponent)getRootPane()).getActionMap().put(inputMap.get(esc),new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ActionListener[] listeners = cancelButton.getActionListeners();
                ActionEvent actionEvent = new ActionEvent(cancelButton, ActionEvent.ACTION_PERFORMED, cancelButton.getActionCommand());
                for (int i = 0; i < listeners.length; i++) {
                    listeners[i].actionPerformed(actionEvent);
                }
            }
        });

        getIDTextField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cnumberActionPerformed(e);
            }
        });
        getIDTextField().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                getIDTextField().selectAll();
                getIDTextField().requestFocusInWindow();
            }
        });

        productCombobox.getComboBox().addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                productComboboxStateChanged(e);
            }
        });

        familyselect.setContext(Context.getProductGroup());
        familyselect.setSearchEnabled(true);
        suppliername.setSearchEnabled(true);
        suppliername.setContext(Context.getSupplier());
        manufacturername.setSearchEnabled(true);
        manufacturername.setContext(Context.getManufacturer());
        stype.setModel(Product.getTypes(), MPComboBoxModelItem.COMPARE_BY_ID, new java.util.Vector<Integer>());

    }


    public JTextField getIDTextField(){
        return cnumber.getTextField();
    }

    private void cnumberActionPerformed(ActionEvent e){
        try {
            productCombobox.setSelectedItem(Integer.valueOf(cnumber.getText()));
            Product p = (Product) Product.getObject(Context.getProduct(), Integer.valueOf(productCombobox.getSelectedItem().getId()));
        } catch (Exception ex) {
        }
    }

    private void productComboboxStateChanged(ItemEvent e){
        try {
            Product p = (Product) Product.getObject(Context.getProduct(), Integer.valueOf(productCombobox.getSelectedItem().getId()));
            cnumber.setText(p.__getCnumber());
            description.setText(p.__getDescription());
        } catch (NodataFoundException ex) {
        }
    }


    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        cnumber.grabFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        cnumber = new mpv5.ui.beans.LabeledTextField();
        productCombobox = new mpv5.ui.beans.LabeledCombobox();
        cname = new mpv5.ui.beans.LabeledTextField();
        stype = new mpv5.ui.beans.LabeledCombobox();
        familyselect = new mpv5.ui.beans.LabeledCombobox();
        suppliername = new mpv5.ui.beans.LabeledCombobox();
        manufacturername = new mpv5.ui.beans.LabeledCombobox();
        searchButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        class NoTabTextArea extends JTextArea {
            protected void processComponentKeyEvent( KeyEvent e ) {
                if ( e.getID() == KeyEvent.KEY_PRESSED &&
                    e.getKeyCode() == KeyEvent.VK_TAB ) {
                    e.consume();
                    if (e.isShiftDown()) {
                        transferFocusBackward();
                    } else {
                        transferFocus();
                    }
                } else {
                    super.processComponentKeyEvent( e );
                }
            }
        }
        description = new NoTabTextArea()
        ;

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
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

        cnumber.set_Label(bundle.getString("ProductPanel.contactid.text")); // NOI18N
        cnumber.setName("cnumber"); // NOI18N

        productCombobox.set_Label(bundle.getString("ProductSelectDialog.labeledCombobox1._Label")); // NOI18N
        productCombobox.setName("productCombobox"); // NOI18N
        productCombobox.setSearchOnEnterEnabled(true);

        cname.set_Label(bundle.getString("ProductPanel.cname._Label_1")); // NOI18N
        cname.setName("cname"); // NOI18N

        stype.set_Label(bundle.getString("ProductPanel.stype._Label")); // NOI18N
        stype.setName("stype"); // NOI18N

        familyselect.set_Label(bundle.getString("ProductPanel.familyselect._Label")); // NOI18N
        familyselect.setName("familyselect"); // NOI18N
        familyselect.setSearchOnEnterEnabled(false);

        suppliername.set_Label(bundle.getString("ProductSelectDialog.suppliername._Label")); // NOI18N
        suppliername.setName("suppliername"); // NOI18N

        manufacturername.set_Label(bundle.getString("ProductSelectDialog.manufacturername._Label")); // NOI18N
        manufacturername.setName("manufacturername"); // NOI18N

        searchButton.setText(bundle.getString("searchButton")); // NOI18N
        searchButton.setName("searchButton"); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        resetButton.setText(bundle.getString("resetButton")); // NOI18N
        resetButton.setName("resetButton"); // NOI18N
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        description.setColumns(20);
        description.setEditable(false);
        description.setLineWrap(true);
        description.setRows(5);
        description.setWrapStyleWord(true);
        description.setFocusTraversalPolicyProvider(true);
        description.setHighlighter(null);
        description.setName("description"); // NOI18N
        jScrollPane3.setViewportView(description);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(productCombobox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(cnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stype, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(familyselect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suppliername, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(manufacturername, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resetButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                    .addComponent(cname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(familyselect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(manufacturername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(productCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        int row = table.getSelectedRow();
        if(row<0){
            Log.Debug(this,"row is "+row);
            return;
        }
        try {
            MPComboBoxModelItem item = productCombobox.getSelectedItem();
            if(item!=null){
                SubItem s = new SubItem((Product) Product.getObject(Context.getProduct(), Integer.valueOf(item.getId())));
                ((MPTableModel)table.getModel()).setRowAt(s.getRowData(row), row, 1);
            }
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        cnumber.setText("");
        suppliername.setSelectedItem(null);
        manufacturername.setSelectedItem(null);
        cname.setText("");
    }//GEN-LAST:event_resetButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        QueryCriteria2 qc = new QueryCriteria2();
        String cnametext = cname.getText();
        if(cnametext!=null && !cnametext.equals("")){
            qc.and(new QueryParameter(Context.getProduct(), "cname",cnametext, QueryParameter.LIKE));
        }
        qc.and(new QueryParameter(Context.getProduct(), "inttype",Integer.parseInt(stype.getSelectedItem().getId()), QueryParameter.EQUALS));
        MPComboBoxModelItem item = familyselect.getSelectedItem();
        if(item!=null){
            qc.and(new QueryParameter(Context.getProduct(), "productgroupsids",Integer.parseInt(item.getId()), QueryParameter.EQUALS));
        }
        item = suppliername.getSelectedItem();
        if(item!=null){
            qc.and(new QueryParameter(Context.getProduct(), "suppliersids",Integer.parseInt(item.getId()), QueryParameter.EQUALS));
        }
        item = manufacturername.getSelectedItem();
        if(item!=null){
            qc.and(new QueryParameter(Context.getProduct(), "manufacturersids",Integer.parseInt(item.getId()), QueryParameter.EQUALS));
        }

        try{
            ReturnValue data = QueryHandler.instanceOf().clone(Context.getProduct()).select("ids, cname", qc, new vTimeframe(new Date(0), new Date()));
            productCombobox.setModel(new MPComboboxModel(MPComboBoxModelItem.toItems(data.getData())));
        }catch(Exception e){
            Log.Debug(this,e);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton cancelButton;
    private mpv5.ui.beans.LabeledTextField cname;
    private mpv5.ui.beans.LabeledTextField cnumber;
    private javax.swing.JTextArea description;
    private mpv5.ui.beans.LabeledCombobox familyselect;
    private javax.swing.JScrollPane jScrollPane3;
    private mpv5.ui.beans.LabeledCombobox manufacturername;
    public javax.swing.JButton okButton;
    private mpv5.ui.beans.LabeledCombobox productCombobox;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton searchButton;
    private mpv5.ui.beans.LabeledCombobox stype;
    private mpv5.ui.beans.LabeledCombobox suppliername;
    // End of variables declaration//GEN-END:variables

}
