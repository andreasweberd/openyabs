/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashScreen.java
 *
 * Created on 30.03.2009, 21:55:52
 */
package mpv5.ui.dialogs;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.ui.frames.MPV5View;
import mpv5.ui.misc.Position;
import mpv5.utils.html.HtmlParser;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.Selection;
import mpv5.utils.tables.TableFormat;

/**
 *
 * @author anti
 */
public class Search extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private static Search f;

    public static Search instanceOf() {
        if (f == null) {
            f = new Search();
        }
        f.setVisible(true);
        f.requestFocus();
        return f;
    }
    private static String oldSelection;
    private Context lastContext;
    private boolean firtsmove = true;

    @Override
    public void dispose() {
        setVisible(false);
    }

    /** Creates new form SplashScreen
     */
    public Search() {
        initComponents();
        scope.setModel(new DefaultComboBoxModel(Context.getSearchableContexts().toArray()));
        new Position(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        scope = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        key = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCheckBox2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(null);
        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("Search.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(bundle.getString("Search.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(bundle.getString("Search.jLabel1.toolTipText")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        scope.setName("scope"); // NOI18N

        jLabel2.setText(bundle.getString("Search.jLabel2.text")); // NOI18N
        jLabel2.setToolTipText(bundle.getString("Search.jLabel2.toolTipText")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        key.setEditable(true);
        key.setName("key"); // NOI18N
        key.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyActionPerformed(evt);
            }
        });

        jCheckBox1.setText(bundle.getString("Search.jCheckBox1.text")); // NOI18N
        jCheckBox1.setToolTipText(bundle.getString("Search.jCheckBox1.toolTipText")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        jButton1.setText(bundle.getString("Search.jButton1.text")); // NOI18N
        jButton1.setToolTipText(bundle.getString("Search.jButton1.toolTipText")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("Search.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jCheckBox2.setText(bundle.getString("Search.jCheckBox2.text")); // NOI18N
        jCheckBox2.setToolTipText(bundle.getString("Search.jCheckBox2.toolTipText")); // NOI18N
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scope, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(scope, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox1)
                        .addComponent(jCheckBox2))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() > 1) {
            if (jCheckBox2.isSelected()) {
                Selection s = new Selection(jTable1);
                if (s.checkID()) {
                    lastContext = Context.getMatchingContext(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                }
            }
            if (lastContext != null) {
                try {
                    MPV5View.identifierView.addTab(DatabaseObject.getObject(lastContext, Integer.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString())));
                } catch (NodataFoundException ex) {
                    mpv5.logging.Log.Debug(ex);
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        search();
}//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged
        scope.setEnabled(!jCheckBox2.isSelected());
}//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void keyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyActionPerformed
        search();
}//GEN-LAST:event_keyActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox key;
    private javax.swing.JComboBox scope;
    // End of variables declaration//GEN-END:variables

    private void search() {
        String newSelection = (String) key.getSelectedItem();

        if (newSelection != null && !newSelection.equals(oldSelection)) {
            key.addItem(newSelection);
            oldSelection = newSelection;
        }

        if (newSelection == null || newSelection.equals("null")) {
            newSelection = "";
        }

        Object[][] data = null;
        if (!jCheckBox2.isSelected()) {
            lastContext = (Context) scope.getSelectedItem();
            lastContext.addReference(Context.getGroup());
            DatabaseSearch s = new DatabaseSearch(lastContext);
            data = s.getValuesFor(lastContext.getDbIdentity() + ".ids," + lastContext.getDbIdentity() + ".cname," + "groups0.cname," + lastContext.getDbIdentity() + ".dateadded", new String[]{"cname"}, newSelection, !jCheckBox1.isSelected());
            jTable1.setModel(new MPTableModel(data));
        } else {
            lastContext = null;
            DatabaseSearch s = new DatabaseSearch(Context.getSearchIndex());
            data = s.getValuesFor("rowid, dbidentity, text", "text", newSelection, !jCheckBox1.isSelected());
            jTable1.setModel(new MPTableModel(HtmlParser.getMarkedHtml(data, 2, newSelection)));
        }

        TableFormat.stripFirstColumn(jTable1);
        TableFormat.format(jTable1, 1, 100);
    }
}