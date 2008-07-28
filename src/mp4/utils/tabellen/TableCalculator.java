/*
 * 
This file is part of MP by anti43 /GPL.

MP is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

MP is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with MP.  If not, see <http://www.gnu.org/licenses/>.

 * 
 */
package mp4.utils.tabellen;

import javax.swing.CellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import mp4.einstellungen.Einstellungen;

import mp4.utils.zahlen.FormatNumber;

/**
 *
 * @author Administrator
 */
public class TableCalculator implements Runnable {

    private boolean stopped = true;
    private Thread t;
    private Double defaultTaxRate;
    private JTable table;
    private boolean nettoprices = true;
    private JTextField taxTextField = new JTextField();
    private JTextField bruttoTextField = new JTextField();
    private JComponent panel;

    public TableCalculator(JTable table, JComponent panel) {
        defaultTaxRate = Double.valueOf(Einstellungen.instanceOf().getGlobaltax());
        
        this.table = table;
        this.panel = panel;
        
        t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();

    }


    public void run() {

        TableColumn column;
        CellEditor cell;

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            while (!isStopped() && panel.isShowing()) {

                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                }

                TableModel m = table.getModel();

                Double tax = 0d;
                Double itax = 0d;
                Double netto = 0d;
                Double brutto = 0d;
                Double curnetto = 0d;
                Double curbrutto = 0d;
                Double curnettoe = 0d;
                Double curbruttoe = 0d;


                try {
                    if (!table.isEditing()) {
                        for (int row = 0; row < m.getRowCount(); row++) {
                            //anzahl,bezeichnung,mehrwertsteuer,nettopreis

                            if ((m.getValueAt(row, 2)) != null) {
                                if ((m.getValueAt(row, 2)).toString().equals("null")) {
                                    m.setValueAt("", row, 2);
                                }
                            }

                            if ((m.getValueAt(row, 1)) == null) {
                                m.setValueAt(new Double(1), row, 1);
                            }

                            if ((m.getValueAt(row, 3)) == null) {
                                m.setValueAt(new Double(defaultTaxRate), row, 3);
                            }

                            if (m.getValueAt(row, 4) != null && isNettoprices()) {
                                tax = ((Double) (m.getValueAt(row, 3)));
                                itax = (tax / 100) + 1;
                                curnetto = ((Double) (m.getValueAt(row, 1))) * ((Double) (m.getValueAt(row, 4)));
                                curnettoe = ((Double) (m.getValueAt(row, 4)));
                                netto = netto + curnetto;
                                curbruttoe = curnettoe * itax;
                                curbrutto = curnetto * itax;
                                brutto = brutto + curbrutto;

                                m.setValueAt((curbruttoe), row, 5);
                            } else if (m.getValueAt(row, 5) != null && !isNettoprices()) {
                                tax = ((Double) (m.getValueAt(row, 3)));
                                itax = (tax / 100) + 1;
                                curbrutto = ((Double) (m.getValueAt(row, 5)));
                                curnetto = curbrutto / itax;
                                netto = netto + ((Double) (m.getValueAt(row, 1))) * curnetto;
                                brutto = brutto + ((Double) (m.getValueAt(row, 1))) * curbrutto;

                                m.setValueAt((curnetto), row, 4);
                            }
                        }
                        getTaxTextField().setText(FormatNumber.formatDezimal(brutto - netto));//!tax
                        getBruttoTextField().setText(FormatNumber.formatDezimal(brutto));//!tax
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isNettoprices() {
        return nettoprices;
    }

    public void setNettoprices(boolean nettoprices) {
        this.nettoprices = nettoprices;
    }

    public JTextField getTaxTextField() {
        return taxTextField;
    }

    public void setTaxTextField(JTextField taxTextField) {
        this.taxTextField = taxTextField;
    }

    public JTextField getBruttoTextField() {
        return bruttoTextField;
    }

    public void setBruttoTextField(JTextField bruttoTextField) {
        this.bruttoTextField = bruttoTextField;
    }
}