/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SplashScreen.java
 *
 * Created on 30.03.2009, 21:55:52
 */
package mpv5.ui.dialogs;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import mpv5.Main;
import mpv5.globals.Constants;
import mpv5.logging.Log;
import mpv5.logging.LogConsole;
import mpv5.ui.misc.Position;

/**
 *
 *
 */
public class SplashScreen extends JDialog {

    private static final long serialVersionUID = 1L;
    private Image image;
    private Image grayImage;
    private int progressSteps;
    private final LogConsole l;
    private final int level;

    /**
     * Creates new form SplashScreen
     *
     * @param imageIcon
     */
    public SplashScreen(ImageIcon imageIcon) {
        initComponents();
        setTitle("Yabs start...");
        jPanel1.setOpaque(false);
        setInfo(Constants.VERSION);
        title.setText(Constants.TITLE);
        jProgressBar1.setStringPainted(true);
        image = imageIcon.getImage();
        grayImage = GrayFilter.createDisabledImage(image);

        new Position(this);
        setVisible(true);
        setModalityType(ModalityType.MODELESS);
        jPanel1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jScrollPane1.getViewport().setOpaque(false);
        l = new LogConsole() {
            public void log() {
                jTextArea1.setText(jTextArea1.getText().concat("\n"));
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
            }

            public void log(Object object) {
                jTextArea1.setText(jTextArea1.getText().concat("\n" + String.valueOf(object)));
                jTextArea1.setCaretPosition(jTextArea1.getText().length());
            }

            public void flush() {
                jTextArea1.setText(null);
            }

            public JComponent open() {
                return jTextArea1;
            }
        };
        Log.addLogger(l);
        level = Log.getLoglevel();
        Log.setLogLevel(Log.LOGLEVEL_DEBUG);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 =  new javax.swing.JPanel(){
            public void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, this);
                super.paintComponent(g);
            }
        };
        progress = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MP Start..");
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("OptionPane.errorDialog.border.background"));
        jPanel1.setPreferredSize(new java.awt.Dimension(450, 300));

        progress.setForeground(new java.awt.Color(23, 23, 23));
        progress.setText("Progress");

        info.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        info.setText("Version info");

        jProgressBar1.setBorderPainted(false);
        jProgressBar1.setStringPainted(true);

        title.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        title.setForeground(new java.awt.Color(131, 1, 1));
        title.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        title.setText("Title");

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        jTextArea1.setBackground(new java.awt.Color(1, 1, 1));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(jTextArea1.getFont().deriveFont(jTextArea1.getFont().getSize()-1f));
        jTextArea1.setForeground(new java.awt.Color(254, 254, 254));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(info)
                    .addComponent(title))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel info;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel progress;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables

    /**
     * Initiates the progressbar
     *
     * @param steps
     */
    public void init(int steps) {
        jProgressBar1.setMaximum(100);
        jProgressBar1.setMinimum(0);
        progressSteps = 100 / steps + 1;
        nextStep("Don't panik!");
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the grayImage
     */
    public Image getGrayImage() {
        return grayImage;
    }

    /**
     * @param grayImage the grayImage to set
     */
    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info.getText();
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info.setText(info);
    }

    /**
     * @return the progress
     */
    public String getProgress() {
        return progress.getText();
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(String progress) {
        this.progress.setText(progress);
    }

    /**
     * Moves the progressbar forward one step
     *
     * @param message
     */
    public synchronized void nextStep(final String message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                setProgress(message);
                jProgressBar1.setValue(progressSteps);
                progressSteps += progressSteps;
            }
        };
        SwingUtilities.invokeLater(runnable);
    }

    @Override
    public void dispose() {
        Log.removeLogger(l);
        Log.setLogLevel(level);
        super.dispose();
    }
}
