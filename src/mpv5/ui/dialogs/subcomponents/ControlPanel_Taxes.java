/*
This file is part of YaBS.

YaBS is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

YaBS is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * ContactPanel.java
 *
 * Created on Nov 20, 2008, 8:17:28 AM
 */
package mpv5.ui.dialogs.subcomponents;

import java.awt.Component;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpv5.data.PropertyStore;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.objects.Tax;
import mpv5.i18n.LanguageManager;
import mpv5.ui.dialogs.ControlApplet;
import mpv5.ui.panels.DataPanel;
import mpv5.usermanagement.MPSecurityManager;
import mpv5.utils.ui.TextFieldUtils;

/**
 *
 * 
 */
public class ControlPanel_Taxes extends javax.swing.JPanel implements ControlApplet, DataPanel {

    private static final long serialVersionUID = 1L;
    private ControlPanel_Taxes insta;
    private Tax dataOwner;

    /** Creates new form ContactPanel
     */
    public ControlPanel_Taxes() {
        if (MPSecurityManager.checkAdminAccess()) {
            initComponents();
            value.set_ValueClass(Double.class);
            value.setText(0);
            selecttax.setContext(Context.getTaxes());
            selecttax.setReceiver(this);
            selecttax.getComboBox().setEditable(false);
            selecttax.setSearchEnabled(false);
            refresh();
        }
    }

    public void showRequiredFields() {
        TextFieldUtils.blinkerRed(cname);

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
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        selecttax = new mpv5.ui.beans.LabeledCombobox();
        cname = new mpv5.ui.beans.LabeledTextField();
        value = new mpv5.ui.beans.LabeledTextField();
        countries = new mpv5.ui.beans.LabeledCombobox();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("ControlPanel_Taxes.border.title"))); // NOI18N
        setName("Form"); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton2.setText(bundle.getString("ControlPanel_Taxes.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2);

        jButton1.setText(bundle.getString("ControlPanel_Taxes.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton1);

        add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jPanel1.setName("jPanel1"); // NOI18N

        selecttax.set_Label(bundle.getString("ControlPanel_Taxes.selecttax._Label")); // NOI18N
        selecttax.setName("selecttax"); // NOI18N

        cname.set_Label(bundle.getString("ControlPanel_Taxes.cname._Label")); // NOI18N
        cname.setName("cname"); // NOI18N

        value.set_Label(bundle.getString("ControlPanel_Taxes.value._Label")); // NOI18N
        value.setName("value"); // NOI18N

        countries.set_Label(bundle.getString("ControlPanel_Taxes.countries._Label")); // NOI18N
        countries.setName("countries"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selecttax, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(countries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selecttax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(countries, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        collectData();
        Tax t = new Tax();
        if (dataOwner != null) {
            t = dataOwner;
        } else {
            t.setIdentifier(cname_);
        }
        t.setCName(cname_);
        t.setCountry(country_);
        t.setTaxvalue(taxvalue_);
        t.save();
        actionAfterSave();
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        collectData();
        Tax t = new Tax();
        t.setCountry(country_);
        t.setIdentifier(cname_);
        t.setCName(cname_);
        t.setTaxvalue(taxvalue_);
        t.save();
        actionAfterCreate();

}//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private mpv5.ui.beans.LabeledTextField cname;
    private mpv5.ui.beans.LabeledCombobox countries;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private mpv5.ui.beans.LabeledCombobox selecttax;
    private mpv5.ui.beans.LabeledTextField value;
    // End of variables declaration//GEN-END:variables
    public String cname_;
    public int intaddedby_;
    public int ids_;
    public Date dateadded_;
    public int groupsids_ = 1;
    public double taxvalue_;
    public String identifier_ = "";
    public String country_ = "";

    public void setValues(PropertyStore values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUname() {
        return "Taxes";
    }

    public void reset() {
    }

    public ControlApplet instanceOf() {
        if (insta == null) {
            insta = new ControlPanel_Taxes();
        }
        return insta;
    }

    public void refresh() {
        try {
            selecttax.setModel(DatabaseObject.getObjects(Context.getTaxes()));
        } catch (NodataFoundException ex) {}
        countries.setModel(LanguageManager.getCountriesAsComboBoxModel());
    }

    public Component getAndRemoveActionPanel() {
        this.remove(jPanel6);
        validate();
        return jPanel6;
    }

    public boolean collectData() {
        try {
            cname_ = cname.getText();
            taxvalue_ = value.getValue(0d);
            country_ = countries.getComboBox().getSelectedItem().toString();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DatabaseObject getDataOwner() {
        return dataOwner;
    }

    public void setDataOwner(DatabaseObject object, boolean populateData) {
        this.dataOwner = (Tax) object;
        if (populateData) {
            dataOwner.setPanelData(this);
            this.exposeData();
        }
    }

    public void exposeData() {
        cname.setText(cname_);
        value.setText(taxvalue_);
        countries.getComboBox().setSelectedItem(country_);
    }

    public void paste(DatabaseObject dbo) {
        setDataOwner(dbo, true);
    }

    public void showSearchBar(boolean show) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionAfterSave() {
        refresh();
    }

    public void actionAfterCreate() {
        refresh();
    }

    public void actionBeforeCreate() {
    }

    public void actionBeforeSave() {
    }

    public void mail() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
