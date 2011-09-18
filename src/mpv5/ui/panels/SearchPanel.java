/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchPanel.java
 *
 * Created on Nov 30, 2008, 6:16:09 PM
 */
package mpv5.ui.panels;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellRenderer;
import mpv5.db.common.Context;

import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.db.objects.User;
import mpv5.db.objects.ValueProperty;
import mpv5.globals.Headers;
import mpv5.logging.Log;
import mpv5.ui.frames.MPView;
import mpv5.ui.misc.MPTable;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.Selection;
import mpv5.utils.tables.TableFormat;
import mpv5.ui.misc.TableViewPersistenceHandler;

/**
 *
 * 
 */
public class SearchPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private Context context;
    private DataPanel panel;
    private int lasttype = 5;
    private String lastneedle = "0";
    private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();

    /** Creates new form SearchPanel */
    public SearchPanel() {
        initComponents();
        label1.setToolTipText(label1.getText());
        label2.setToolTipText(label2.getText());
        label3.setToolTipText(label3.getText());
        //  t = new mpv5.utils.ui.TableViewPersistenceHandler(resulttable, this);
        TableFormat.hideHeader(resulttable);
        ((MPTable) resulttable).setPersistanceHandler(new TableViewPersistenceHandler((MPTable)resulttable, this));
    }

    public SearchPanel(Context context, DataPanel panel) {
        this();
        setName("searchpanel");
        this.validate();
        this.context = context;
        this.panel = panel;
        lastneedle = String.valueOf(mpv5.db.objects.User.getCurrentUser().__getGroupsids());
        search(5, String.valueOf(mpv5.db.objects.User.getCurrentUser().__getGroupsids()));
    }

    /**
     * Reload the search result
     */
    public void refresh() {
        search(lasttype, lastneedle);
    }
//
//    public void setContextOwner(DatabaseObject object) {
//        context.setOwner(object);
//        refresh();
//    }

    /** This me4thod is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchfields = new javax.swing.JPanel();
        label1 = new javax.swing.JLabel();
        searchfield1 = new javax.swing.JTextField();
        searchfield2 = new javax.swing.JTextField();
        label2 = new javax.swing.JLabel();
        searchfield3 = new javax.swing.JTextField();
        label3 = new javax.swing.JLabel();
        searchbutton1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        results = new javax.swing.JPanel();
        resultsscrollpane = new javax.swing.JScrollPane();
        resulttable = new  mpv5.ui.misc.MPTable(this) {
            public Component prepareRenderer(TableCellRenderer renderer,
                int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (c instanceof JComponent) {
                    JComponent jc = (JComponent)c;
                    jc.setToolTipText(String.valueOf(getValueAt(rowIndex, vColIndex)));
                }
                return c;
            }
        };

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        searchfields.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SearchPanel.searchfields.border.title"))); // NOI18N
        searchfields.setName("searchfields"); // NOI18N

        label1.setText(bundle.getString("SearchPanel.label1.text")); // NOI18N
        label1.setName("label1"); // NOI18N

        searchfield1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchfield1.setName("searchfield1"); // NOI18N
        searchfield1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfield1ActionPerformed(evt);
            }
        });

        searchfield2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchfield2.setName("searchfield2"); // NOI18N
        searchfield2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfield2ActionPerformed(evt);
            }
        });

        label2.setText(bundle.getString("SearchPanel.label2.text")); // NOI18N
        label2.setName("label2"); // NOI18N

        searchfield3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchfield3.setName("searchfield3"); // NOI18N
        searchfield3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchfield3ActionPerformed(evt);
            }
        });

        label3.setText(bundle.getString("SearchPanel.label3.text")); // NOI18N
        label3.setName("label3"); // NOI18N

        searchbutton1.setText(bundle.getString("SearchPanel.searchbutton1.text")); // NOI18N
        searchbutton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        searchbutton1.setName("searchbutton1"); // NOI18N
        searchbutton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbutton1ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/16/search.png"))); // NOI18N
        jButton1.setText(bundle.getString("SearchPanel.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/16/search.png"))); // NOI18N
        jButton2.setText(bundle.getString("SearchPanel.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/16/search.png"))); // NOI18N
        jButton3.setText(bundle.getString("SearchPanel.jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchfieldsLayout = new javax.swing.GroupLayout(searchfields);
        searchfields.setLayout(searchfieldsLayout);
        searchfieldsLayout.setHorizontalGroup(
            searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchfieldsLayout.createSequentialGroup()
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchfieldsLayout.createSequentialGroup()
                        .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(searchfield1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchfield2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchfield3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        searchfieldsLayout.setVerticalGroup(
            searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchfieldsLayout.createSequentialGroup()
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label1)
                    .addComponent(searchfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label2)
                    .addComponent(searchfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(label3)
                    .addComponent(searchfield3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchbutton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        results.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SearchPanel.results.border.title"))); // NOI18N
        results.setName("results"); // NOI18N

        resultsscrollpane.setName("resultsscrollpane"); // NOI18N

        resulttable.setAutoCreateRowSorter(true);
        resulttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        resulttable.setName("resulttable"); // NOI18N
        resulttable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resulttable.setShowVerticalLines(false);
        resulttable.setSurrendersFocusOnKeystroke(true);
        resulttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resulttableMouseClicked(evt);
            }
        });
        resulttable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                resulttableKeyTyped(evt);
            }
        });
        resultsscrollpane.setViewportView(resulttable);

        javax.swing.GroupLayout resultsLayout = new javax.swing.GroupLayout(results);
        results.setLayout(resultsLayout);
        resultsLayout.setHorizontalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );
        resultsLayout.setVerticalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchfields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchfields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private synchronized void search(final int searchtype, final String value) {

        if (this.isShowing()) {

            Runnable runnable = new Runnable() {

                @Override
                public void run() {

                    String sf = context.getSearchFields();
                    if (sf == null) {
                        sf = context.getDbIdentity() + ".ids," + context.getDbIdentity() + ".cname," + context.getDbIdentity() + ".cnumber";
                    }

                    lasttype = searchtype;
                    lastneedle = value;
                    Log.Debug(this, "Search parameters: " + searchtype + " " + value);
                    switch (searchtype) {
                        case 1:
                            if (context.equals(Context.getContact())) {
                                resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 500).getValuesFor(sf, new String[]{"cname", "company"}, value, true)));
                            } else if (context.equals(Context.getItem())) {
                                Integer id = new DatabaseSearch(Context.getContact()).searchForID("cname", value, false);
                                if (id != null) {
                                    resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 500).getValuesFor(sf, "contactsids",
                                            id)));
                                } else {
                                    resulttable.setModel(new MPTableModel(new String[][]{}));
                                }
                            } else {
                                resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 500).getValuesFor(sf, "cname", value, true)));
                            }
                            break;
                        case 2:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 500).getValuesFor(sf, "cnumber", value, true)));

                            break;
                        case 3:
                            Integer id = new DatabaseSearch(Context.getGroup()).searchForID("cname", value, false);
                            if (id != null) {
                                resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 500).getValuesFor(sf, "groupsids",
                                        id)));
                            } else {
                                resulttable.setModel(new MPTableModel(new String[][]{}));
                            }

                            break;
                        case 4:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 0).getValuesFor(sf, "cname", "", true)));
                            break;
                        case 5:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 0).getValuesFor(sf, "groupsids",
                                    Integer.valueOf(value))));
                            break;
                        default:
                            Log.Debug(this, "Invalid parameters!");
                    }
                    TableFormat.hideHeader(resulttable);
                    TableFormat.makeUneditable(resulttable);
                    TableFormat.stripColumns(resulttable, new int[]{0, 4, 5, 6, 7, 8, 9});

                }
            };
            Log.Debug(this, "Starting search..");
            SwingUtilities.invokeLater(runnable);
        }

    }

    private void searchfield3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfield3ActionPerformed
        search(3, searchfield3.getText());
}//GEN-LAST:event_searchfield3ActionPerformed

    private void searchfield1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfield1ActionPerformed
        search(1, searchfield1.getText());
    }//GEN-LAST:event_searchfield1ActionPerformed

    private void searchfield2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchfield2ActionPerformed
        search(2, searchfield2.getText());
    }//GEN-LAST:event_searchfield2ActionPerformed

    private void searchbutton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbutton1ActionPerformed
        search(4, "");
    }//GEN-LAST:event_searchbutton1ActionPerformed

    private void resulttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resulttableMouseClicked
        Selection sel = new Selection(resulttable);
        if (sel.checkID()) {
            try {
                panel.setDataOwner(DatabaseObject.getObject(context, sel.getId()), true);
            } catch (NodataFoundException ex) {
                Log.Debug(ex);
                User.getCurrentUser().getLayoutProperties().clear();
                try {
                    ValueProperty.deleteProperty(User.getCurrentUser(), "layoutinfo");
                } catch (Exception e) {
                    Log.Debug(ex);
                }

//                mpv5.YabsViewProxy.instance().resetTables();
            }
        }
    }//GEN-LAST:event_resulttableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        searchfield1ActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        searchfield2ActionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        searchfield3ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void resulttableKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resulttableKeyTyped

        resulttableMouseClicked(null);
    }//GEN-LAST:event_resulttableKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JPanel results;
    private javax.swing.JScrollPane resultsscrollpane;
    private javax.swing.JTable resulttable;
    private javax.swing.JButton searchbutton1;
    private javax.swing.JTextField searchfield1;
    private javax.swing.JTextField searchfield2;
    private javax.swing.JTextField searchfield3;
    private javax.swing.JPanel searchfields;
    // End of variables declaration//GEN-END:variables

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
     * repeat the last search
     */
    public void search() {
        search(lasttype, lastneedle);
    }
}
