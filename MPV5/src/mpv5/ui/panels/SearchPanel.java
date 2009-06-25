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

import java.awt.Dimension;
import javax.swing.SwingUtilities;
import mpv5.db.common.Context;

import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.globals.Headers;
import mpv5.logging.Log;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.Selection;
import mpv5.utils.tables.TableFormat;

/**
 *
 * 
 */
public class SearchPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private Context context;
    private DataPanel panel;
    private int lasttype = 4;
    private String lastneedle = "";
    private int oldwitdth;

    /** Creates new form SearchPanel */
    public SearchPanel() {
        initComponents();
    }

    public SearchPanel(Context context, DataPanel panel) {
        initComponents();
//        jToolBar1.setSize(jToolBar1.getWidth(), 41);
//        jToolBar1.setPreferredSize(new Dimension(jToolBar1.getWidth(), 41));
//        jToolBar1.setSize(jToolBar1.getWidth(), 41);
//        jToolBar1.setMinimumSize(new Dimension(jToolBar1.getWidth(), 41));
        this.validate();
        this.context = context;
        this.panel = panel;
        search(1, context.getParent().__getCName());
    }

    /**
     * Reload the search result
     */
    public void refresh() {
        search(lasttype, lastneedle);
    }

    public void setContextOwner(DatabaseObject object) {
        context.setOwner(object);
        refresh();
    }

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
        results = new javax.swing.JPanel();
        resultsscrollpane = new javax.swing.JScrollPane();
        resulttable = new javax.swing.JTable();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
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

        javax.swing.GroupLayout searchfieldsLayout = new javax.swing.GroupLayout(searchfields);
        searchfields.setLayout(searchfieldsLayout);
        searchfieldsLayout.setHorizontalGroup(
            searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchfieldsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1)
                    .addComponent(label2)
                    .addComponent(label3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchbutton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(searchfield3, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(searchfield2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(searchfield1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addContainerGap())
        );
        searchfieldsLayout.setVerticalGroup(
            searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchfieldsLayout.createSequentialGroup()
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(searchfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2)
                    .addComponent(searchfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchfieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3)
                    .addComponent(searchfield3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchbutton1, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addContainerGap())
        );

        results.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SearchPanel.results.border.title"))); // NOI18N
        results.setName("results"); // NOI18N

        resultsscrollpane.setName("resultsscrollpane"); // NOI18N

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
        resultsscrollpane.setViewportView(resulttable);

        javax.swing.GroupLayout resultsLayout = new javax.swing.GroupLayout(results);
        results.setLayout(resultsLayout);
        resultsLayout.setHorizontalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );
        resultsLayout.setVerticalGroup(
            resultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultsscrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchfields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(results, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchfields, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(results, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void search(final int searchtype, final String value) {

        if (isShowing()) {
            Runnable runnable = new Runnable() {

                public void run() {
                    lasttype = searchtype;
                    lastneedle = value;

                    switch (searchtype) {

                        case 1:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 50).getValuesFor("ids,cname", "cname", value, true), Headers.SEARCH_DEFAULT.getValue()));

                            break;
                        case 2:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 50).getValuesFor("ids,cname", "cnumber", value, true), Headers.SEARCH_DEFAULT.getValue()));

                            break;
                        case 3:
                            Integer id = new DatabaseSearch(Context.getGroup()).searchForID("cname", value);
                            if (id != null) {
                                resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 50).getValuesFor("ids,cname", "groupsids",
                                        id), Headers.SEARCH_DEFAULT.getValue()));
                            } else {
                                resulttable.setModel(new MPTableModel(new String[][]{}, Headers.SEARCH_DEFAULT.getValue()));
                            }

                            break;
                        case 4:
                            resulttable.setModel(new MPTableModel(new DatabaseSearch(context, 50).getValuesFor("ids,cname", "cname", context.getParent().__getCName(), true), Headers.SEARCH_DEFAULT.getValue()));

                    }
                    TableFormat.stripFirstColumn(resulttable);
                    TableFormat.makeUneditable(resulttable);
                }
            };
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
        search(4, context.getParent().__getCName());
    }//GEN-LAST:event_searchbutton1ActionPerformed

    private void resulttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resulttableMouseClicked
        Selection sel = new Selection(resulttable);
        if (sel.checkID()) {
            try {
                panel.setDataOwner(DatabaseObject.getObject(context, sel.getId()));
            } catch (NodataFoundException ex) {
                Log.Debug(ex);
            }
        }
    }//GEN-LAST:event_resulttableMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
}
