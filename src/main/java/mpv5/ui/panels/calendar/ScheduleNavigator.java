/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * ScheduleNavigator.java
 *
 * Created on 01.01.2012, 13:04:10
 */
package mpv5.ui.panels.calendar;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Develop
 */
public class ScheduleNavigator extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private final ScheduleMonthChooser month;
    private final ScheduleYearChooser year;

    /**
     * Creates new form ScheduleNavigator
     *
     * @param monthChooser
     * @param yearChooser
     */
    public ScheduleNavigator(ScheduleMonthChooser monthChooser,
            ScheduleYearChooser yearChooser) {
        initComponents();
        heute.setText(DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
        month = monthChooser;
        year = yearChooser;
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

        forward = new javax.swing.JButton();
        back = new javax.swing.JButton();
        heute = new javax.swing.JFormattedTextField();

        setName("Form"); // NOI18N

        forward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/22/next.png"))); // NOI18N
        java.util.ResourceBundle bundle =  mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        forward.setToolTipText(bundle.getString("ScheduleNavigator.forward.toolTipText")); // NOI18N
        forward.setName("forward"); // NOI18N
        forward.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/22/history.png"))); // NOI18N
        forward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardActionPerformed(evt);
            }
        });

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/22/adept_keep.png"))); // NOI18N
        back.setToolTipText(bundle.getString("ScheduleNavigator.back.toolTipText")); // NOI18N
        back.setName("back"); // NOI18N
        back.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/22/kontact_date.png"))); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        heute.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.FULL))));
        heute.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        heute.setText("null");
        heute.setDisabledTextColor(new java.awt.Color(0, 204, 0));
        heute.setEnabled(false);
        heute.setFont(heute.getFont().deriveFont(heute.getFont().getStyle() | java.awt.Font.BOLD));
        heute.setName("heute"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heute, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(forward)
            .addComponent(back)
            .addComponent(heute, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void forwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardActionPerformed
        if (month.getMonth() == 11) {
            month.setMonth(0);
            year.setYear(year.getYear() + 1);
        } else {
            month.setMonth(month.getMonth() + 1);
        }
    }//GEN-LAST:event_forwardActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        if (month.getMonth() == 0) {
            month.setMonth(11);
            year.setYear(year.getYear() - 1);
        } else {
            month.setMonth(month.getMonth() - 1);
        }
    }//GEN-LAST:event_backActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton forward;
    private javax.swing.JFormattedTextField heute;
    // End of variables declaration//GEN-END:variables

    /**
     * Write the given Date formated to the label
     *
     * @param tday
     */
    protected void setDate(Date tday) {
        heute.setText(DateFormat.getDateInstance(DateFormat.FULL).format(tday));
    }
}
