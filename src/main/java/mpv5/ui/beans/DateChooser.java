package mpv5.ui.beans;


import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import mpv5.ui.dialogs.MiniCalendarDialog;
import mpv5.utils.date.DateConverter;

/**
 *
 *  
 */
public class DateChooser extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private MiniCalendarDialog d;
    private boolean asAPopup = false;

    /** Creates new form LabeledTextField */
    public DateChooser() {
        initComponents();
        jSpinner1.setEditor(new DateEditor(jSpinner1, DateConverter.getDefaultFormatString()));
    }

    /**
     *
     * @return
     */
    public JSpinner getSpinner() {
        return jSpinner1;
    }

    /**
     *
     */
    public DateChooser(boolean asAPopup) {
        initComponents();
        jSpinner1.setEditor(new DateEditor(jSpinner1, DateConverter.getDefaultFormatString()));
        this.asAPopup = asAPopup;
    }

    /**
     * Determines if the field has some text
     * @return
     */
    public boolean hasDate() {
        return (DateConverter.getDate(jSpinner1.getValue()) != null);
    }

    public void setDate(Date date) {
        if (d == null) {
            d = new MiniCalendarDialog(jSpinner1, asAPopup);
        }

        try {
            jSpinner1.setValue(date);
        } catch (Exception e) {
        }
        d.setDate(date);
    }

    public Date getDate() {
        if (hasDate()) {
            try {
                jSpinner1.commitEdit();
                return DateConverter.getDate(jSpinner1.getValue());
            } catch (ParseException ex) {
                Logger.getLogger(DateChooser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Date();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(41, 23));
        setName("Form"); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(118, 30));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jSpinner1.setFont(jSpinner1.getFont());
        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        jSpinner1.setEditor(new javax.swing.JSpinner.DateEditor(jSpinner1, ""));
        jSpinner1.setMinimumSize(new java.awt.Dimension(23, 18));
        jSpinner1.setName("jSpinner1"); // NOI18N
        jSpinner1.setPreferredSize(new java.awt.Dimension(100, 20));
        add(jSpinner1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16/cal.png"))); // NOI18N
        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        jButton1.setToolTipText(bundle.getString("DateChooser.jButton1.toolTipText")); // NOI18N
        jButton1.setBorder(null);
        jButton1.setContentAreaFilled(false);
        jButton1.setMaximumSize(new java.awt.Dimension(30, 333));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (d == null) {
            d = new MiniCalendarDialog(jSpinner1, asAPopup);
        }

        d.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (d == null) {
            d = new MiniCalendarDialog(jSpinner1, asAPopup);
        }
        d.setLocation(evt.getLocationOnScreen());
        d.setVisible(true);
    }//GEN-LAST:event_jButton1MouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JSpinner jSpinner1;
    // End of variables declaration//GEN-END:variables
}