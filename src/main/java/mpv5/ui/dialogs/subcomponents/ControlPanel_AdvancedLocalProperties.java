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
 * GeneralListPanel.java
 *
 * Created on 03.04.2009, 15:26:37
 */
package mpv5.ui.dialogs.subcomponents;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import mpv5.data.PropertyStore;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.ui.dialogs.ControlApplet;
import mpv5.ui.dialogs.Popup;
import mpv5.usermanagement.MPSecurityManager;
import mpv5.utils.models.MPTableModel;
import mpv5.utils.tables.TableFormat;

/**
 *
 *  
 */
public class ControlPanel_AdvancedLocalProperties extends javax.swing.JPanel implements ControlApplet {

    private static final long serialVersionUID = 1L;
    private static ControlPanel_AdvancedLocalProperties ident;
    private PropertyStore oldvals;

    /** Creates new form GeneralListPanel */
    public ControlPanel_AdvancedLocalProperties() {
        if (MPSecurityManager.checkAdminAccess()) {
            initComponents();
            setData(LocalSettings.getPropertyStore());
        }
    }

    public void setData(PropertyStore vals) {
        oldvals = vals;
        List<String[]> data = vals.getList();
        Object[][] list = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            String[] strings = data.get(i);
            list[i][0] = strings[0].toUpperCase();
            list[i][1] = strings[1];
        }
        MPTableModel mod = new MPTableModel(list, new String[]{Messages.PROPERTY.getValue(), Messages.VALUE.getValue()});
        mod.setCanEdits(new boolean[]{false, true, false});
        jTable1.setModel(mod);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        java.util.ResourceBundle bundle = mpv5.i18n.LanguageManager.getBundle(); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ControlPanel_AdvancedLocalProperties.border.title"))); // NOI18N
        setName("Form"); // NOI18N
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);

//$2java.awt.Color(255, 255, 255));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton1.setText(bundle.getString("ControlPanel_AdvancedLocalProperties.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText(bundle.getString("ControlPanel_AdvancedLocalProperties.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setText(bundle.getString("ControlPanel_AdvancedLocalProperties.jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Popup.Y_N_dialog(Messages.ADVANCED_SETTINGS_SAVE, Messages.WARNING)) {
            setSettings();
            LocalSettings.apply();
            LocalSettings.save();
        }
}//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setSettings();
        LocalSettings.apply();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
}//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getAndRemoveActionPanel() {
        remove(jPanel1);
        validate();
        return jPanel1;
    }

    @Override
    public void setValues(PropertyStore values) {
        setData(values);
    }

    @Override
    public String getUname() {
        return "advancedlocalsettings";
    }

    @Override
    public void reset() {
        if (oldvals != null) {
            setValues(oldvals);
        }
    }

    private void setSettings() {
        if (jTable1.getCellEditor() != null) {
             try {
                jTable1.getCellEditor().stopCellEditing();
            } catch (Exception e) {
            }
        }
        MPTableModel data = (MPTableModel) jTable1.getModel();
        int i = data.getRowCount();
        for (int j = 0; j < i; j++) {
            String value = String.valueOf(data.getValueAt(j, 1));
            String key = String.valueOf(data.getValueAt(j, 0)).toLowerCase();
            if (!key.equals("null") && key.length() > 0)LocalSettings.setProperty(key, value);
        }
    }
}
