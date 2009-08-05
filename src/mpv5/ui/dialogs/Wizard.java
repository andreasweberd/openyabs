/*
 *  This file is part of YaBS.
 *
 *  YaBS is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  YaBS is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Wizard.java
 *
 * Created on Dec 18, 2008, 5:14:12 AM
 */
package mpv5.ui.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import mpv5.data.PropertyStore;
import mpv5.globals.Messages;
import mpv5.ui.misc.Position;

/**
 *
 * 
 */
public class Wizard extends javax.swing.JFrame implements WizardMaster {

    private static final long serialVersionUID = 1L;
    public PropertyStore actionVars = new PropertyStore();
//    private int index = 4300;
    private Component lastpanel;
    private boolean isEnded = false;
    private boolean standalone;
    private ArrayList<JComponent> contentlist = new ArrayList<JComponent>();
    ;
    private int level;

    /** Creates new form Wizard
     * @param standalone
     */
    public Wizard(boolean standalone) {
        initComponents();
        back.setEnabled(false);
        this.standalone = standalone;
        if (standalone) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        level = 0;
    }

    public void showWiz() {
        back.setEnabled(false);
        content.add(contentlist.get(level), BorderLayout.CENTER);
        pack();
        new Position(this);
        setAlwaysOnTop(true);
        setVisible(true);
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
        content = new javax.swing.JPanel();
        control = new javax.swing.JPanel();
        cancel = new javax.swing.JButton();
        back = new javax.swing.JButton();
        next = new javax.swing.JButton();
        message = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("mpv5/resources/languages/Panels"); // NOI18N
        setTitle(bundle.getString("Wizard.title")); // NOI18N
        setBackground(new java.awt.Color(255, 255, 255));
        setName("Form"); // NOI18N
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        content.setName("content"); // NOI18N
        content.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(content);

        getContentPane().add(jScrollPane1);

        control.setBackground(new java.awt.Color(255, 255, 255));
        control.setMaximumSize(new java.awt.Dimension(32767, 100));
        control.setName("control"); // NOI18N
        control.setPreferredSize(new java.awt.Dimension(375, 50));

        cancel.setText(bundle.getString("Wizard.cancel.text")); // NOI18N
        cancel.setName("cancel"); // NOI18N
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        back.setText(bundle.getString("Wizard.back.text")); // NOI18N
        back.setName("back"); // NOI18N
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        next.setText(bundle.getString("Wizard.next.text")); // NOI18N
        next.setName("next"); // NOI18N
        next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextActionPerformed(evt);
            }
        });

        message.setText(bundle.getString("Wizard.message.text")); // NOI18N
        message.setName("message"); // NOI18N

        javax.swing.GroupLayout controlLayout = new javax.swing.GroupLayout(control);
        control.setLayout(controlLayout);
        controlLayout.setHorizontalGroup(
            controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(next)
                .addContainerGap())
        );
        controlLayout.setVerticalGroup(
            controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(controlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next)
                    .addComponent(back)
                    .addComponent(cancel)
                    .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(control);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        if (!standalone) {
            this.dispose();
        } else {
            System.exit(1);
        }
}//GEN-LAST:event_cancelActionPerformed

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed

        if (isEnded && standalone) {
            System.exit(0);
        } else if (isEnded) {
            dispose();
        }

        try {
            lastpanel = content.getComponent(0);
            if (!isEnded && ((Wizardable) lastpanel).next()) {
                content.remove(lastpanel);
                back.setEnabled(true);
                content.add(contentlist.get(level + 1), BorderLayout.CENTER);
                ((Wizardable) contentlist.get(level + 1)).load();
            }

            level++;
        } catch (Exception e) {
            next.setEnabled(false);
        }

        pack();
}//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

        try {
            if (((Wizardable) content.getComponent(0)).back() && level > 0) {
                content.remove(lastpanel);
                content.add(contentlist.get(level - 1), BorderLayout.CENTER);
                level--;
            }
        } catch (Exception e) {
            back.setEnabled(false);
        }

        this.validate();
}//GEN-LAST:event_backActionPerformed

    /**
     * Add a apanel to this wizard. Panels will be shown in the order they are added here,
     * while first panel is shown first.
     * @param panel
     */
    public void addPanel(Wizardable panel) {
        JPanel pane = (JPanel) panel;
//        (pane).setPreferredSize(new Dimension(content.getPreferredSize()));
//        (pane).setBounds(0, 0, content.getWidth(), content.getHeight());
//        if (pane.getWidth() > content.getWidth() || pane.getHeight() > content.getHeight()) {
//            content.setMinimumSize(new Dimension(pane.getWidth(), pane.getHeight()));
//            content.setPreferredSize(new Dimension(pane.getWidth(), pane.getHeight()));
//            this.setMinimumSize(new Dimension(pane.getWidth() + 10, pane.getHeight() + 10));
//            this.setPreferredSize(new Dimension(pane.getWidth() + 10, pane.getHeight() + 10));
//        }
        (pane).setOpaque(true);
        contentlist.add(pane);
    }

    /**
     * Notifies the wizard to show the "Finnish" button which closes the wizard
     * @param end
     */
    public void isEnd(boolean end) {
        this.isEnded = end;
        if (end) {
            next.setText(Messages.FINISH.toString());
        }
        cancel.setEnabled(!end);
        back.setEnabled(!end);
    }

    /**
     * ActionVars are temporay vars which are used between different panels of this wizrd.
     * @return A PropertyStore which can be used to hold ActionVars
     */
    public PropertyStore getStore() {
        return actionVars;
    }

    /**
     * Set the message shown on the bottom of the wizard
     * @param message
     */
    public void setMessage(String message) {
        this.message.setText(message);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Wizard(true).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton cancel;
    private javax.swing.JPanel content;
    private javax.swing.JPanel control;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JButton next;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @return
     */
    public Wizardable getNext() {
        return (Wizardable) contentlist.get(level + 1);
    }
}