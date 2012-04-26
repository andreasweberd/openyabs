/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LabeledTextField.java
 *
 * Created on 20.11.2008, 19:26:39
 */
package mpv5.ui.beans;

import java.awt.Font;
import java.io.File;
import javax.swing.JComponent;
import javax.swing.filechooser.FileFilter;
import mpv5.globals.Messages;
import mpv5.ui.dialogs.DialogForFile;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.dialogs.subcomponents.wizard_XMLImport_1;

/**
 *
 *  
 */
public class LabeledTextChooser extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private String _text;
    private String _label;
    private int _mode = DialogForFile.FILES_AND_DIRECTORIES;
    private FileFilter filter;
    private JComponent mparent;

    /** Creates new form LabeledTextField */
    public LabeledTextChooser() {
        initComponents();
    }

    /**
     * Determines if the field has some text
     * @return
     */
    public boolean hasText() {
        if (jTextField1.getText() != null && jTextField1.getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sets a File filter for this text field
     * @param f
     */
    public void setFilter(FileFilter f) {
        filter = f;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new mpv5.ui.beans.LabeledTextField();
        jButton1 = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
        add(jTextField1);

        jButton1.setText("...");
        jButton1.setToolTipText("Choose");
        jButton1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton1.setMaximumSize(new java.awt.Dimension(50, 100));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DialogForFile dialog = new DialogForFile(getMode());
        dialog.setModalityParent(mparent);
        if (filter != null) {
            dialog.setFileFilter(filter);
        }
        if (dialog.chooseFile()) {
            jTextField1.setText(dialog.getFile().getPath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private mpv5.ui.beans.LabeledTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    /**
     * @param check if the file exists (pops up a warning if not)
     * @return the _text
     */
    public String get_Text(boolean check) {
        if (new File(jTextField1.getText()).exists()) {
            return jTextField1.getText();
        } else {
            if (check) {
                Popup.notice(Messages.FILE_OPEN_FAILED + jTextField1.getText());
            }
        }
        return jTextField1.getText();
    }

    /**
     * @param text the _text to set
     */
    public void set_Text(String text) {
        if (text == null) {
            text = "";
        }
        this._text = text;
        jTextField1.setText(text);
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        if (text == null) {
            text = "";
        }
        this._text = text;
        jTextField1.setText(text);
    }

    /**
     * @return the _label
     */
    public String get_Label() {
        return jTextField1.get_Label();
    }

    /**
     * @param label the _label to set
     */
    public void set_Label(String label) {
        this._label = label;
        jTextField1.set_Label(label);
    }

    @Override
    public void setEnabled(boolean enabled) {
        jTextField1.setEnabled(enabled);
    }

    public void set_LabelFont(Font ignore) {
    }

    /**
     * @return the selection mode
     */
    public int getMode() {
        return _mode;
    }

    /**
     * Default: javax.​swing.​JFileChooser.FILES_AND_DIRECTORIES
     * @param mode the selection mode to set
     */
    public void setMode(int mode) {
        this._mode = mode;
    }

    /**
     * Sets the modality parent for the popup dialogs
     * @param parent
     */
    public void setModalityParent(JComponent parent) {
        this.mparent=parent;
    }
}
