package mpv5.ui.panels;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.db.objects.Contact;
import mpv5.globals.Headers;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.misc.MPTable;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.Selection;
import mpv5.ui.misc.TableViewPersistenceHandler;
import mpv5.utils.export.Export;

/**
 *
 *
 */
public class ContactsList extends javax.swing.JPanel implements ListPanel, DataPanel {

   private static final long serialVersionUID = 1L;
   private Context context;
   private java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle();

   /**
    * Creates new form ListPanel
    */
   public ContactsList() {
      initComponents();
      setName("contactlist");
      prinitingComboBox1.init(listTable);
      this.context = Context.getContact();
      ((MPTable) listTable).setDefaultColumns(new Integer[]{0, 100, 100, 100, 100, 100, 100}, new Boolean[]{});
      ((MPTable) listTable).setPersistanceHandler(new TableViewPersistenceHandler((MPTable) listTable, this));
   }

   public ContactsList(Context context) {
      this();

      this.context = context;
      context.setSearchFields(Context.DETAILS_CONTACTS);
//        fill(true, true, true, true, false);
   }

   private void fill(boolean customer, boolean supplier, boolean manufacturer, boolean company, boolean filtered) {

      context.setContactConditions(customer, supplier, manufacturer, company);
      context.setExclusiveContactConditions(customer, supplier, manufacturer, company);
      context.useExclusiveConditions(filtered);
      if (jTextField1.getText() != null && jTextField1.getText().length() > 0) {
         listTable.setModel(new MPTableModel(new DatabaseSearch(context).getValuesFor(Context.DETAILS_CONTACTS, Context.DETAIL_CONTACT_SEARCH.split(","), jTextField1.getText(), true), Headers.CONTACT_DETAILS.getValue(),
               new Class[]{Integer.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}));
      } else {
         listTable.setModel(new MPTableModel(new DatabaseSearch(context).getValuesFor(Context.DETAILS_CONTACTS), Headers.CONTACT_DETAILS.getValue()));
      }
      count.setText(String.valueOf(listTable.getModel().getRowCount()));
   }

   /**
    * This me4thod is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listTable = new  mpv5.ui.misc.MPTable(this) {
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        excButton = new javax.swing.JCheckBox();
        prinitingComboBox1 = new mpv5.ui.beans.PrinitingComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        count = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ContactsList.jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        listTable.setAutoCreateRowSorter(true);
        listTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        listTable.setDragEnabled(true);
        listTable.setFillsViewportHeight(true);
        listTable.setName("listTable"); // NOI18N
        listTable.setShowVerticalLines(false);
        listTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listTable);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ContactsList.jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setFont(jLabel3.getFont());
        jLabel3.setText(bundle.getString("ContactsList.jLabel3.text")); // NOI18N
        jLabel3.setMaximumSize(new java.awt.Dimension(100, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(100, 14));
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 30));

        jTextField1.setFont(jTextField1.getFont());
        jTextField1.setText(bundle.getString("ContactsList.jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 30));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setFont(jButton2.getFont());
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/22/mail_find.png"))); // NOI18N
        jButton2.setText(bundle.getString("ContactsList.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(jButton3.getFont());
        jButton3.setText(bundle.getString("ContactsList.jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.setPreferredSize(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jToolBar2.setFloatable(false);
        jToolBar2.setMaximumSize(new java.awt.Dimension(457, 33));
        jToolBar2.setName("jToolBar2"); // NOI18N
        jToolBar2.setPreferredSize(new java.awt.Dimension(670, 23));

        jLabel1.setFont(jLabel1.getFont());
        jLabel1.setText(bundle.getString("ContactsList.jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 14));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 14));
        jToolBar2.add(jLabel1);

        jCheckBox1.setFont(jCheckBox1.getFont());
        jCheckBox1.setText(bundle.getString("ContactsList.jCheckBox1.text")); // NOI18N
        jCheckBox1.setMargin(new java.awt.Insets(2, 6, 2, 6));
        jCheckBox1.setMaximumSize(new java.awt.Dimension(333, 33));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(100, 20));
        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.setPreferredSize(new java.awt.Dimension(100, 20));
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ItemStateChanged(evt);
            }
        });
        jToolBar2.add(jCheckBox1);

        jCheckBox2.setFont(jCheckBox2.getFont());
        jCheckBox2.setText(bundle.getString("ContactsList.jCheckBox2.text")); // NOI18N
        jCheckBox2.setMargin(new java.awt.Insets(2, 6, 2, 6));
        jCheckBox2.setMaximumSize(new java.awt.Dimension(333, 33));
        jCheckBox2.setMinimumSize(new java.awt.Dimension(100, 20));
        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.setPreferredSize(new java.awt.Dimension(100, 20));
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ItemStateChanged(evt);
            }
        });
        jToolBar2.add(jCheckBox2);

        jCheckBox3.setFont(jCheckBox3.getFont());
        jCheckBox3.setText(bundle.getString("ContactsList.jCheckBox3.text")); // NOI18N
        jCheckBox3.setMargin(new java.awt.Insets(2, 6, 2, 6));
        jCheckBox3.setMaximumSize(new java.awt.Dimension(333, 33));
        jCheckBox3.setMinimumSize(new java.awt.Dimension(100, 20));
        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.setPreferredSize(new java.awt.Dimension(100, 20));
        jCheckBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ItemStateChanged(evt);
            }
        });
        jToolBar2.add(jCheckBox3);

        jCheckBox4.setFont(jCheckBox4.getFont());
        jCheckBox4.setText(bundle.getString("ContactsList.jCheckBox4.text")); // NOI18N
        jCheckBox4.setMargin(new java.awt.Insets(2, 6, 2, 6));
        jCheckBox4.setMaximumSize(new java.awt.Dimension(333, 33));
        jCheckBox4.setMinimumSize(new java.awt.Dimension(100, 20));
        jCheckBox4.setName("jCheckBox4"); // NOI18N
        jCheckBox4.setPreferredSize(new java.awt.Dimension(100, 20));
        jCheckBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ItemStateChanged(evt);
            }
        });
        jToolBar2.add(jCheckBox4);

        excButton.setFont(excButton.getFont().deriveFont(excButton.getFont().getStyle() | java.awt.Font.BOLD));
        excButton.setText(bundle.getString("ContactsList.excButton.text")); // NOI18N
        excButton.setToolTipText(bundle.getString("ContactsList.excButton.toolTipText")); // NOI18N
        excButton.setMargin(new java.awt.Insets(2, 6, 2, 6));
        excButton.setMaximumSize(new java.awt.Dimension(333, 33));
        excButton.setMinimumSize(new java.awt.Dimension(100, 20));
        excButton.setName("excButton"); // NOI18N
        excButton.setPreferredSize(new java.awt.Dimension(100, 20));
        excButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                excButtonItemStateChanged(evt);
            }
        });
        jToolBar2.add(excButton);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        prinitingComboBox1.setName("prinitingComboBox1"); // NOI18N
        prinitingComboBox1.setPreferredSize(new java.awt.Dimension(200, 20));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(232, 227, 227), 1, true));
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel4.setFont(jLabel4.getFont().deriveFont(jLabel4.getFont().getStyle() | java.awt.Font.BOLD));
        jLabel4.setText(bundle.getString("ContactsList.jLabel4.text")); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(200, 20));
        jLabel4.setMinimumSize(new java.awt.Dimension(50, 20));
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel3.add(jLabel4);

        jButton5.setFont(jButton5.getFont());
        jButton5.setText(bundle.getString("ContactsList.jButton5.text")); // NOI18N
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jButton5.setContentAreaFilled(false);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

        jButton1.setFont(jButton1.getFont());
        jButton1.setText(bundle.getString("ContactsList.jButton1.text")); // NOI18N
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jButton1.setContentAreaFilled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton4.setFont(jButton4.getFont());
        jButton4.setText(bundle.getString("ContactsList.jButton4.text")); // NOI18N
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        jButton4.setContentAreaFilled(false);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setPreferredSize(new java.awt.Dimension(120, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);

        count.setFont(count.getFont().deriveFont(count.getFont().getStyle() | java.awt.Font.BOLD));
        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setText(bundle.getString("ContactsList.count.text")); // NOI18N
        count.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        count.setName("count"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(prinitingComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(count, javax.swing.GroupLayout.Alignment.LEADING)))
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

    private void ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ItemStateChanged
//        fill(jCheckBox1.isSelected(), jCheckBox2.isSelected(), jCheckBox3.isSelected(), jCheckBox4.isSelected(), excButton.isSelected());
}//GEN-LAST:event_ItemStateChanged

    private void excButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_excButtonItemStateChanged

       fill(jCheckBox1.isSelected(), jCheckBox2.isSelected(), jCheckBox3.isSelected(), jCheckBox4.isSelected(), excButton.isSelected());
    }//GEN-LAST:event_excButtonItemStateChanged

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       fill(jCheckBox1.isSelected(), jCheckBox2.isSelected(), jCheckBox3.isSelected(), jCheckBox4.isSelected(), excButton.isSelected());
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void listTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTableMouseClicked

       if (evt.getClickCount() > 1) {
          Selection sel = new Selection(listTable);
          if (sel.checkID()) {
             try {
                mpv5.YabsViewProxy.instance().getIdentifierView().addTab(DatabaseObject.getObject(context, sel.getId()));
             } catch (NodataFoundException ex) {
                Log.Debug(ex);
             }
          }
       }
    }//GEN-LAST:event_listTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       DatabaseObject d = DatabaseObject.getObject(Context.getCustomer());
       ((mpv5.db.objects.Contact) d).setisCustomer(true);
       mpv5.YabsViewProxy.instance().getIdentifierView().addTab(d, Messages.NEW_CUSTOMER);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       jTextField1ActionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       jTextField1.setText("");
       jCheckBox1.setSelected(false);
       jCheckBox2.setSelected(false);
       jCheckBox3.setSelected(false);
       jCheckBox4.setSelected(false);
       excButton.setSelected(false);
       jTextField1ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

       DatabaseObject d = DatabaseObject.getObject(Context.getManufacturer());
       ((mpv5.db.objects.Contact) d).setisManufacturer(true);
       mpv5.YabsViewProxy.instance().getIdentifierView().addTab(d, Messages.NEW_MANUFACTURER);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

       DatabaseObject d = DatabaseObject.getObject(Context.getSupplier());
       ((mpv5.db.objects.Contact) d).setisSupplier(true);
       mpv5.YabsViewProxy.instance().getIdentifierView().addTab(d, Messages.NEW_SUPPLIER);

    }//GEN-LAST:event_jButton5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel count;
    private javax.swing.JCheckBox excButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JTable listTable;
    private mpv5.ui.beans.PrinitingComboBox prinitingComboBox1;
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

   public void refresh() {
      String t = jTextField1.getText();
      jTextField1.setText("");
      jTextField1ActionPerformed(null);
      jTextField1.setText(t);
   }

   public void flush() {
      listTable.setModel(null);
   }

   public void showType(Contact d) {
      jCheckBox1.setSelected(d.__getIscustomer());
      jCheckBox2.setSelected(d.__getIssupplier());
      jCheckBox3.setSelected(d.__getIsmanufacturer());
      jCheckBox4.setSelected(d.__getisCompany());
      jTextField1ActionPerformed(null);
   }

   public boolean collectData() {
      return false;
   }

   public DatabaseObject getDataOwner() {
      return null;
   }

   public void setDataOwner(DatabaseObject object, boolean populateData) {
   }

   public void exposeData() {
   }

   public void paste(DatabaseObject... dbo) {
   }

   public void showRequiredFields() {
   }

   public void showSearchBar(boolean show) {
   }

   public void actionAfterSave() {
   }

   public void actionAfterCreate() {
   }

   public void actionBeforeCreate() {
   }

   public void actionBeforeSave() throws ChangeNotApprovedException {
   }

   public void mail() {
   }

   public void print() {
      Export.print(listTable);
   }
}
