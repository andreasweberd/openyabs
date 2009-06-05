/*
 *  This file is part of MP.
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
 * ContactsTB.java
 *
 * Created on 25.01.2009, 16:54:29
 */
package mpv5.ui.toolbars;

import java.awt.event.ActionListener;
import mpv5.db.common.DatabaseObject;
import mpv5.globals.LocalSettings;
import mpv5.globals.Messages;
import mpv5.db.objects.Favourite;
import mpv5.logging.Log;
import mpv5.ui.dialogs.Popup;
import mpv5.ui.frames.MPV5View;
import mpv5.ui.panels.DataPanel;
import mpv5.utils.print.PrintJob;

/**
 *
 * 
 */
public class DataPanelTB extends javax.swing.JPanel {

    private static final long serialVersionUID = -8215471082724735228L;
    private DataPanel parents;
    private final ActionListener action1 = new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            favAdd(evt);
        }
    };
    private final ActionListener action2 = new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            favRemover(evt);
        }
    };

    /**
     * 
     * @param panel
     */
    public DataPanelTB(DataPanel panel) {
        initComponents();
        parents = panel;
        parents.showSearchBar(!MPV5View.getUser().getProperties().getProperty(this, "jToggleButton1", true));
        jToggleButton1.setSelected(MPV5View.getUser().getProperties().getProperty(this, "jToggleButton1", true));
        jButton21.setEnabled(!DatabaseObject.isAutoLockEnabled());
    }

    /**
     *
     * @param on
     */
    public void setMinimalFunctionality(boolean on) {
        jButton21.setEnabled(!on);
        jButton23.setEnabled(!on);
        jButton24.setEnabled(!on);
    }

    /**
     *
     * @param on
     */
    public void setEditable(boolean on) {
        jButton25.setEnabled(on);
        jButton26.setEnabled(on);
        jButton21.setEnabled(on);
    }

    /**
     *
     * @param favourite
     */
    public void setFavourite(boolean favourite) {
        if (!favourite) {
            jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/bookmark_add.png"))); // NOI18N
            jButton24.setFocusable(false);
            jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            jButton24.removeActionListener(action2);
            jButton24.removeActionListener(action1);
            jButton24.addActionListener(action1);
        } else {
            jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/bookmark.png"))); // NOI18N
            jButton24.setFocusable(false);
            jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
            jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
            jButton24.removeActionListener(action1);
            jButton24.removeActionListener(action2);
            jButton24.addActionListener(action2);
        }
        this.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        commonActionsToolbar = new javax.swing.JToolBar();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton22 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton26 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton21 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton23 = new javax.swing.JButton();

        commonActionsToolbar.setFloatable(false);
        commonActionsToolbar.setRollover(true);
        commonActionsToolbar.setPreferredSize(new java.awt.Dimension(342, 41));

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/viewmag.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        jToggleButton1.setText(bundle.getString("DataPanelTB.jToggleButton1.text")); // NOI18N
        jToggleButton1.setToolTipText(bundle.getString("DataPanelTB.jToggleButton1.toolTipText")); // NOI18N
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jToggleButton1ItemStateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jToggleButton1);
        commonActionsToolbar.add(jSeparator4);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/revert.png"))); // NOI18N
        jButton22.setText(bundle.getString("MPV5View.jButton22.text")); // NOI18N
        jButton22.setToolTipText(bundle.getString("MPV5View.jButton22.toolTipText")); // NOI18N
        jButton22.setContentAreaFilled(false);
        jButton22.setFocusable(false);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton22);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/3floppy_unmount.png"))); // NOI18N
        jButton25.setText(bundle.getString("MPV5View.jButton25.text")); // NOI18N
        jButton25.setToolTipText(bundle.getString("MPV5View.jButton25.toolTipText")); // NOI18N
        jButton25.setContentAreaFilled(false);
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton25);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/filenew.png"))); // NOI18N
        jButton4.setText(bundle.getString("MPV5View.jButton4.text")); // NOI18N
        jButton4.setToolTipText(bundle.getString("MPV5View.jButton4.toolTipText")); // NOI18N
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton4);
        commonActionsToolbar.add(jSeparator1);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/edittrash.png"))); // NOI18N
        jButton26.setText(bundle.getString("MPV5View.jButton26.text")); // NOI18N
        jButton26.setToolTipText(bundle.getString("MPV5View.jButton26.toolTipText")); // NOI18N
        jButton26.setContentAreaFilled(false);
        jButton26.setFocusable(false);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton26);
        commonActionsToolbar.add(jSeparator3);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/password.png"))); // NOI18N
        jButton21.setText(bundle.getString("MPV5View.jButton21.text")); // NOI18N
        jButton21.setToolTipText(bundle.getString("MPV5View.jButton21.toolTipText")); // NOI18N
        jButton21.setContentAreaFilled(false);
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton21);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/bookmark_add.png"))); // NOI18N
        jButton24.setText(bundle.getString("MPV5View.jButton24.text")); // NOI18N
        jButton24.setToolTipText(bundle.getString("MPV5View.jButton24.toolTipText")); // NOI18N
        jButton24.setContentAreaFilled(false);
        jButton24.setFocusable(false);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton24);
        commonActionsToolbar.add(jSeparator2);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mpv5/resources/images/32/printer.png"))); // NOI18N
        jButton23.setText(bundle.getString("MPV5View.jButton23.text")); // NOI18N
        jButton23.setToolTipText(bundle.getString("MPV5View.jButton23.toolTipText")); // NOI18N
        jButton23.setContentAreaFilled(false);
        jButton23.setFocusable(false);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        commonActionsToolbar.add(jButton23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commonActionsToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(commonActionsToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        DatabaseObject dato = parents.getDataOwner();

        if (dato.isExisting()) {
            dato.getPanelData((parents));
            dato.reset();
            parents.refresh();
            parents.setDataOwner(dato);
        }

        Log.Debug(this, commonActionsToolbar.getSize());

}//GEN-LAST:event_jButton22ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        DatabaseObject dato = parents.getDataOwner();

        dato.getPanelData(parents);
        if (dato.save()) {
            parents.refresh();
            parents.setDataOwner(dato);
        } else {
            parents.showRequiredFields();
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        DatabaseObject dato = parents.getDataOwner();

        dato.getPanelData(parents);
        dato.setIDS(-1);

        if (dato.save()) {
            parents.refresh();
            parents.setDataOwner(dato);
        } else {
            parents.showRequiredFields();
        }
}//GEN-LAST:event_jButton4ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        DatabaseObject dato = parents.getDataOwner();
        if (MPV5View.getUser().isDefault()) {
            Popup.notice(Messages.DEFAULT_USER);
        } else if (dato.isExisting()) {
            if (dato.lock()) {
                Popup.notice(dato.toString() + Messages.LOCKED);
            } else {
                Popup.notice(Messages.LOCK_FAILED);
            }
        } else {
            Popup.notice(Messages.NOT_SAVED_YET);
        }
}//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed

        DatabaseObject dato = parents.getDataOwner();
        if (dato.isExisting()) {
            new PrintJob().print(dato);
        }
}//GEN-LAST:event_jButton23ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed

        DatabaseObject dato = parents.getDataOwner();
        if (dato.isExisting()) {
            dato.getPanelData(parents);
            dato.delete();
            parents.refresh();
            parents.setDataOwner(dato);
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        parents.showSearchBar(!jToggleButton1.isSelected());
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jToggleButton1ItemStateChanged
        MPV5View.getUser().getProperties().changeProperty(this, "jToggleButton1", jToggleButton1.isSelected());
    }//GEN-LAST:event_jToggleButton1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar commonActionsToolbar;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    private void favRemover(java.awt.event.ActionEvent evt) {

        DatabaseObject dato = parents.getDataOwner();
        if (!MPV5View.getUser().isDefault() && dato.isExisting()) {
            dato.getPanelData(parents);
            if (dato.save()) {
                Favourite.removeFavourite(dato);
                MPV5View.identifierView.refreshFavouritesMenu();
                parents.setDataOwner(dato);
            } else {
                parents.showRequiredFields();
            }
        }
    }

    private void favAdd(java.awt.event.ActionEvent evt) {
        DatabaseObject dato = parents.getDataOwner();
        Favourite fav = null;
        if (!MPV5View.getUser().isDefault()) {
            if (dato.isExisting()) {
                dato.getPanelData(parents);
                if (dato.save()) {
                    fav = new Favourite(dato);
                    fav.save();
                    MPV5View.identifierView.refreshFavouritesMenu();
                    MPV5View.addMessage(Messages.ADDED_TO_FAVOURITES + dato.__getCName());
                    parents.setDataOwner(dato);
                } else {
                    parents.showRequiredFields();
                }
            }
        } else {
            Popup.notice(Messages.DEFAULT_USER);
        }
    }
}
