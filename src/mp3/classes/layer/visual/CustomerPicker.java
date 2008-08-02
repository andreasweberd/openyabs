/*
 * CustomerPicker.java
 *
 * Created on 15. Januar 2008, 07:02
 */

package mp3.classes.layer.visual;

import mp3.classes.layer.*;
import mp3.classes.utils.Formater;
import mp4.utils.windows.Position;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SingleSelectionModel;
import javax.swing.table.DefaultTableModel;
import mp4.klassen.objekte.Customer;
import mp4.panels.rechnungen.billsView;
import mp4.panels.rechnungen.offersView;


/**
 *
 * @author  anti43
 */
public class CustomerPicker extends javax.swing.JFrame {
    private billsView frame;
    private offersView frame1;
    private boolean order=false;
    private Customer cust;

 

    public CustomerPicker(offersView aThis) {
        initComponents ();
        this.frame1=aThis;
         cust=frame1.getCustomer();
        new Position(this);
        
        String[][] list = cust.select("id, kundennummer, firma ", "kundennummer", "", "kundennummer", true);
        String k = "id, " + "Nummer,Firma";
       

        this.jTable1.setModel(new DefaultTableModel(list, k.split(",")));
        Formater.stripFirst(jTable1);
        
        this.setVisible(rootPaneCheckingEnabled);
        order=true;
        this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    /** Creates new form CustomerPicker
     * @param frame 
     */
    public CustomerPicker (billsView frame) {
        initComponents ();
        this.frame=frame;
        cust=frame.getCustomer();
        new Position(this);
        
        String[][] list = cust.select("id, kundennummer, firma ", null, "", "kundennummer", true);
        String k = "id, " + "Nummer,Firma";

        this.jTable1.setModel(new DefaultTableModel(list, k.split(",")));
        Formater.stripFirst(jTable1);
        this.jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setVisible(rootPaneCheckingEnabled);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pick");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("W�hlen Sie einen Kunden"));

        jLabel1.setText("Nummer");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Firma");

        jButton2.setText("Abbruch");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 335, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        
        String[][] list = cust.select("id, kundennummer, firma ", "kundennummer", jTextField1.getText(), "kundennummer", true);
        String k = "id, " + "Nummer,Firma";

        this.jTable1.setModel(new DefaultTableModel(list, k.split(",")));
        Formater.stripFirst(jTable1);

        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        String[][] list = cust.select("id, kundennummer, firma ", "firma", jTextField2.getText(), "firma", true);
        String k = "id, " + "Nummer,Firma";

        this.jTable1.setModel(new DefaultTableModel(list, k.split(",")));
        Formater.stripFirst(jTable1);
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTable1MouseClicked (java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
        
        boolean idOk = true;
        Integer id = 0;
     
        try {
            id = Integer.valueOf((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        } catch (Exception numberFormatException) {
            idOk = false;
        }



        if (idOk) {

            try {
                if(!order) {
                    frame.setCustomer(new Customer(id));
                }else {
                    frame1.setCustomer(new Customer(id));
                }
                this.dispose();
            } catch (Exception exception) {
//                exception.printStackTrace();
            }
        } 

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        jTable1MouseClicked(new MouseEvent(frame, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed (java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1KeyPressed (java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER    ) {
            jTable1MouseClicked(new MouseEvent(frame, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, rootPaneCheckingEnabled));
        }
    }//GEN-LAST:event_jTable1KeyPressed
    
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
}
