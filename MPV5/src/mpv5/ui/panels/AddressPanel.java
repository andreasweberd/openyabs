/*
 *  This file is part of MP by anti43 /GPL.
 *
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * AddressPanel.java
 *
 * Created on 26.02.2009, 11:33:08
 */
package mpv5.ui.panels;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.DatabaseSearch;
import mpv5.db.common.NodataFoundException;
import mpv5.globals.Messages;
import mpv5.items.div.Address;
import mpv5.items.div.Contact;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPV5View;
import mpv5.utils.arrays.ArrayUtilities;
import mpv5.utils.models.MPComboBoxModelItem;
import mpv5.utils.ui.TextFieldUtils;

/**
 *
 * @author anti
 */
public class AddressPanel extends javax.swing.JPanel implements DataPanel {

    private static final long serialVersionUID = 8513278191283931211L;
    private Address dataOwner = new Address();
    private Contact dataParent = new Contact();

    /** Creates new form AddressPanel */
    public AddressPanel() {
        initComponents();
          try {
            companyselect.setModel(new DefaultComboBoxModel(ArrayUtilities.merge(new Object[]{new MPComboBoxModelItem("<no_value>", "")},
                    MPComboBoxModelItem.toItems(new DatabaseSearch(Context.getCompany()).getValuesFor(Context.getCompany().getSubID(), null, "")))));
        } catch (Exception e) {
            Log.Debug(this, e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        title = new mpv5.ui.beans.LabeledTextField();
        street = new mpv5.ui.beans.LabeledTextField();
        cname = new mpv5.ui.beans.LabeledTextField();
        prename = new mpv5.ui.beans.LabeledTextField();
        city = new mpv5.ui.beans.LabeledTextField();
        companyselect = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        zip = new mpv5.ui.beans.LabeledTextField();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        taxnumber = new mpv5.ui.beans.LabeledTextField();
        department = new mpv5.ui.beans.LabeledTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(227, 219, 202));
        setName("Address#"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(227, 219, 202));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setName("jPanel2"); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        title.set_Label(bundle.getString("AddressPanel.title._Label")); // NOI18N
        title.setName("title"); // NOI18N

        street.set_Label(bundle.getString("AddressPanel.street._Label")); // NOI18N
        street.setName("street"); // NOI18N

        cname.set_Label(bundle.getString("AddressPanel.cname._Label")); // NOI18N
        cname.setName("cname"); // NOI18N

        prename.set_Label(bundle.getString("AddressPanel.prename._Label")); // NOI18N
        prename.setName("prename"); // NOI18N

        city.set_Label(bundle.getString("AddressPanel.city._Label")); // NOI18N
        city.setName("city"); // NOI18N

        companyselect.setName("companyselect"); // NOI18N
        companyselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                companyselectActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setText(bundle.getString("AddressPanel.jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        zip.set_Label(bundle.getString("AddressPanel.zip._Label")); // NOI18N
        zip.setName("zip"); // NOI18N

        male.setSelected(true);
        male.setText(bundle.getString("AddressPanel.male.text")); // NOI18N
        male.setName("male"); // NOI18N
        male.setOpaque(false);

        female.setText(bundle.getString("AddressPanel.female.text")); // NOI18N
        female.setName("female"); // NOI18N
        female.setOpaque(false);

        taxnumber.set_Label(bundle.getString("AddressPanel.taxnumber._Label")); // NOI18N
        taxnumber.setName("taxnumber"); // NOI18N

        department.set_Label(bundle.getString("AddressPanel.department._Label")); // NOI18N
        department.setName("department"); // NOI18N

        jButton1.setText(bundle.getString("AddressPanel.jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(bundle.getString("AddressPanel.jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(street, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(male)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(female)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(prename, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(city, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cname, 0, 0, Short.MAX_VALUE)
                            .addComponent(zip, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(department, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(companyselect, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(taxnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(companyselect, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(taxnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(male)
                            .addComponent(female))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(street, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void companyselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_companyselectActionPerformed
}//GEN-LAST:event_companyselectActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeAddress();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        addAddress();

    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private mpv5.ui.beans.LabeledTextField city;
    private mpv5.ui.beans.LabeledTextField cname;
    private javax.swing.JComboBox companyselect;
    private mpv5.ui.beans.LabeledTextField department;
    private javax.swing.JRadioButton female;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton male;
    private mpv5.ui.beans.LabeledTextField prename;
    private mpv5.ui.beans.LabeledTextField street;
    private mpv5.ui.beans.LabeledTextField taxnumber;
    private mpv5.ui.beans.LabeledTextField title;
    private mpv5.ui.beans.LabeledTextField zip;
    // End of variables declaration//GEN-END:variables
    public String city_;
    public String cname_;
    public String taxnumber_;
    public String department_;
    public boolean ismale_;
    public String prename_;
    public String street_;
    public String title_;
    public String zip_;
    public String company_;
    public int ids_;
    public int contactsids_;
    public int groupsids_ = 1;

    public void collectData() {
        city_ = city.get_Text();
        cname_ = cname.get_Text();
        taxnumber_ = taxnumber.get_Text();

        if (companyselect.getSelectedItem() != null) {
            company_ = String.valueOf(((MPComboBoxModelItem) companyselect.getSelectedItem()).getName());
        } else {
            company_ = "";
        }
        ismale_ = male.isSelected();
        prename_ = prename.get_Text();
        street_ = street.get_Text();
        title_ = title.get_Text();
        zip_ = zip.get_Text();
        department_ = department.get_Text();
        contactsids_ = dataParent.__getIDS();
    }

    public void exposeData() {
        city.set_Text(city_);
        cname.set_Text(cname_);
        taxnumber.set_Text(taxnumber_);
        try {
            companyselect.setSelectedIndex(MPComboBoxModelItem.getItemIDfromValue(company_, companyselect.getModel()));
        } catch (Exception e) {
        }
        male.setSelected(ismale_);
        female.setSelected(!ismale_);
        prename.set_Text(prename_);
        street.set_Text(street_);
        title.set_Text(title_);
        zip.set_Text(zip_);
        department.set_Text(department_);
        try {
            dataParent = (Contact) DatabaseObject.getObject(Context.getContact(), contactsids_);
        } catch (NodataFoundException ex) {
            Log.Debug(this, ex);
            removeAddress();
        }
    }

    public DatabaseObject getDataOwner() {
        return dataOwner;
    }

    public void setDataOwner(DatabaseObject object) {
        dataOwner = (Address) object;
        dataOwner.setPanelData(this);
        this.exposeData();

        if (this.getParent() instanceof JTabbedPane) {
            JTabbedPane jTabbedPane = (JTabbedPane) this.getParent();
            jTabbedPane.setTitleAt(jTabbedPane.getSelectedIndex(), Messages.CONTACT + cname_);
        }
    }

    public void refresh() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void paste(DatabaseObject dbo) {
        if (dbo.getDbIdentity().equals(Context.getAddress().getDbIdentity())) {
            setDataOwner(dbo);
        } else {
            MPV5View.addMessage(Messages.NOT_POSSIBLE + Messages.PASTE);
        }
    }

    public void showRequiredFields() {
        TextFieldUtils.blinkerRed(cname);
    }

    /**
     * Removes this adress from the current user
     */
    public void removeAddress() {
        this.getParent().remove(this);
        dataOwner.delete();
    }

    /**
     * Associates this adress with the current user
     */
    public void addAddress() {
        if (dataParent.isExisting()) {
            dataOwner.setContactsids(dataParent.__getIDS());
            dataOwner.getPanelData(this);
            if (!dataOwner.save()) {
                showRequiredFields();
            }
        } 
    }

    /**
     * @return the dataParent
     */
    public Contact getDataParent() {
        return dataParent;
    }

    /**
     * @param dataParent the dataParent to set
     */
    public void setDataParent(Contact dataParent) {
        this.dataParent = dataParent;

    }
}