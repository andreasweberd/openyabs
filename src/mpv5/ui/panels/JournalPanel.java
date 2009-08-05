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
/*
 * HistoryPanel.java
 *
 * Created on 30.03.2009, 12:05:51
 */
package mpv5.ui.panels;

import java.awt.Component;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mpv5.db.common.Context;
import mpv5.db.common.QueryCriteria;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryHandler;
import mpv5.db.objects.Contact;
import mpv5.globals.Headers;
import mpv5.db.objects.Group;
import mpv5.db.objects.Item;
import mpv5.logging.Log;
import mpv5.utils.arrays.ArrayUtilities;
import mpv5.utils.date.DateConverter;
import mpv5.utils.date.vTimeframe;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.models.MPTreeModel;
import mpv5.utils.tables.TableFormat;
import mpv5.utils.trees.TreeFormat;

/**
 *
 *  
 */
public class JournalPanel extends javax.swing.JPanel implements ListPanel {

    private static JournalPanel t;
    private static final long serialVersionUID = 1L;

    public static Component instanceOf() {
        if (t == null) {
            t = new JournalPanel();
        }
        return t;
    }
    private Contact dataowner;

    /** Creates new form HistoryPanel */
    public JournalPanel() {
        initComponents();
        jLabel4.setText("");
        timeframeChooser1.setTime(new vTimeframe(DateConverter.getDate(DateConverter.getYear()), new Date()));
        prinitingComboBox1.init(jTable1);
        Object[] dat;
        Object[] dat2;

        try {
            dat2 = DatabaseObject.getObjects(Context.getGroup()).toArray();
            dat2 = ArrayUtilities.merge(new Object[]{new Group("")}, dat2);
            groups.setModel(new DefaultComboBoxModel(dat2));
        } catch (NodataFoundException ex) {
            Log.Debug(ex);
            groups.setModel(new DefaultComboBoxModel());
        }
        refresh(null);
    }

    public JournalPanel(Contact dataOwner) {
        initComponents();
        jLabel4.setText(dataOwner.__getCName());
        timeframeChooser1.setTime(new vTimeframe(DateConverter.getDate(DateConverter.getYear()), new Date()));
        prinitingComboBox1.init(jTable1);
        Object[] dat;
        Object[] dat2;
        dataowner = dataOwner;
        try {
            dat2 = DatabaseObject.getObjects(Context.getGroup()).toArray();
            dat2 = ArrayUtilities.merge(new Object[]{new Group("")}, dat2);
            groups.setModel(new DefaultComboBoxModel(dat2));
        } catch (NodataFoundException ex) {
            Log.Debug(ex);
            groups.setModel(new DefaultComboBoxModel());
        }
//        refresh(null);
        
        jTree1.setCellRenderer(MPTreeModel.getRenderer());
        jTree1.setModel(new MPTreeModel(dataowner));
        jTree1.addMouseListener(MPTreeModel.getDefaultTreeListener(jTree1));

//        TreeFormat.expandTree(jTree1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeframeChooser1 = new mpv5.ui.beans.TimeframeChooser();
        jLabel2 = new javax.swing.JLabel();
        groups = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        prinitingComboBox1 = new mpv5.ui.beans.PrinitingComboBox();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setName("jTree1"); // NOI18N
        jScrollPane2.setViewportView(jTree1);

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab(bundle.getString("JournalPanel.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("JournalPanel.jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setText(bundle.getString("JournalPanel.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel4.setText(bundle.getString("JournalPanel.jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel5.setText(bundle.getString("JournalPanel.jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        timeframeChooser1.setBackground(new java.awt.Color(255, 255, 255));
        timeframeChooser1.setName("timeframeChooser1"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setText(bundle.getString("JournalPanel.jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        groups.setMaximumSize(new java.awt.Dimension(224, 20));
        groups.setName("groups"); // NOI18N
        groups.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupsActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel6.setText(bundle.getString("JournalPanel.jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jCheckBox1);
        jCheckBox1.setSelected(true);
        jCheckBox1.setText(bundle.getString("JournalPanel.jCheckBox1.text")); // NOI18N
        jCheckBox1.setFocusable(false);
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jCheckBox2);
        jCheckBox2.setText(bundle.getString("JournalPanel.jCheckBox2.text")); // NOI18N
        jCheckBox2.setFocusable(false);
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jCheckBox3);
        jCheckBox3.setText(bundle.getString("JournalPanel.jCheckBox3.text")); // NOI18N
        jCheckBox3.setFocusable(false);
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton1.setText(bundle.getString("JournalPanel.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(336, 336, 336))
                    .addComponent(timeframeChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                    .addComponent(groups, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(timeframeChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(groups, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setName("jPanel3"); // NOI18N

        prinitingComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        prinitingComboBox1.setName("prinitingComboBox1"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(404, Short.MAX_VALUE)
                .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(bundle.getString("JournalPanel.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (groups.getSelectedIndex() > 0) {
                refresh((Group) groups.getSelectedItem());
            } else {
                refresh(null);
            }
        } catch (Exception ignore) {
            refresh(null);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void groupsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupsActionPerformed
    }//GEN-LAST:event_groupsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox groups;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private mpv5.ui.beans.PrinitingComboBox prinitingComboBox1;
    private mpv5.ui.beans.TimeframeChooser timeframeChooser1;
    // End of variables declaration//GEN-END:variables

    private Object[][] parse(Object[][] data) {
//         //ids date group number type status value
        double val = 0d;
        double val1 = 0d;
        Object[][] d = new Object[data.length][8];
        try {
            for (int i = 0; i < d.length; i++) {
                d[i][3] = data[i][3];
                d[i][2] = data[i][2];
                d[i][1] = DateConverter.getDefDateString(DateConverter.getDate(data[i][1].toString()));
                d[i][0] = data[i][0];
                d[i][4] = Item.getTypeString(Integer.valueOf(data[i][4].toString()));
                d[i][5] = Item.getStatusString(Integer.valueOf(data[i][5].toString()));
                if (Integer.valueOf(data[i][5].toString()).intValue() == Item.STATUS_PAID) {
                    val += Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString());
                    d[i][7] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString()));
                } else if (Integer.valueOf(data[i][5].toString()).intValue() == Item.STATUS_FINISHED) {
                    d[i][7] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(-1 * (Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString())));
                    val -= Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString());
                } else {
                    d[i][7] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(0d);
                }

                d[i][6] = "<html><p align=center>" + mpv5.utils.numberformat.FormatNumber.formatDezimal(Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString()));
                val1 += Double.valueOf(data[i][6].toString()) + Double.valueOf(data[i][7].toString());

            }
        } catch (Exception numberFormatException) {
            Log.Debug(numberFormatException);
        }

        Object[][] ok = new Object[3][8];

        ok[0][6] = "_______________";
        ok[1][6] = "<html><b>Total: " + mpv5.utils.numberformat.FormatNumber.formatDezimal(val1);

        ok[0][7] = "_______________";
        ok[1][7] = "<html><b>Total: " + mpv5.utils.numberformat.FormatNumber.formatDezimal(val);

        return ArrayUtilities.merge(d, ok);
    }

    private void refresh(final Group forGroup) {
        Runnable runnable = new Runnable() {

            public void run() {
                try {
                    QueryCriteria dh = new QueryCriteria();
                    Object[][] d = new Object[0][0];

                    if (forGroup != null && !forGroup.__getCName().equals("")) {
                        dh.add(forGroup.getDbIdentity() + "ids", forGroup.__getIDS());
                    }

                    if (dataowner != null) {
                        dh.add(dataowner.getDbIdentity() + "ids", dataowner.__getIDS());
                    }

                    if (jCheckBox1.isSelected()) {
                        dh.add("inttype", Item.TYPE_BILL);
                    } else if (jCheckBox2.isSelected()) {
                        dh.add("inttype", Item.TYPE_ORDER);
                    } else if (jCheckBox3.isSelected()) {
                        dh.add("inttype", Item.TYPE_OFFER);
                    }

                    try {
                        Context c = Context.getItems();
                        dh.setOrder("dateadded", true);
                        c.addReference(Context.getGroup());
                        d = QueryHandler.instanceOf().clone(c).select(Context.DETAILS_JOURNAL, dh, timeframeChooser1.getTime());
                    } catch (NodataFoundException ex) {
                        Log.Debug(this, ex.getMessage());
                    }

                    d = parse(d);

                    jTable1.setModel(new MPTableModel(d, Headers.JOURNAL.getValue(), new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}));
                    TableFormat.stripColumn(jTable1, 0);
                } catch (Exception e) {
                    Log.Debug(this, e.getMessage());
                }
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    @Override
    public void refresh() {
        refresh(null);
    }

    @Override
    public void flush() {
        jTable1.setModel(new DefaultTableModel());
    }
}
