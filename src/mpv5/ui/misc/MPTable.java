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
package mpv5.ui.misc;

import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import mpv5.utils.ui.TableViewPersistenceHandler;

/**
 * A JTable implementation which keeps its view state saved in the current yabs user profile
 * @author andreas
 */
public class MPTable extends JTable {

    private final TableViewPersistenceHandler t;

    /**
     * Constructs a default <code>JTable</code> that is initialized with a default
     * data model, a default column model, and a default selection
     * model. The identifier JComponent is used to save the state of the table columns in the actual view.
     * Not using this one constructor may lead to inconsistent behavior of the view if this JTable's parent component has no unique name.
     * It is recommended to use this constructor in Yabs.
     *
     * @see #createDefaultDataModel
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public MPTable(JComponent identifier) {
        super();
        t = new TableViewPersistenceHandler(this, identifier);
    }

    /**
     * Constructs a default <code>JTable</code> that is initialized with a default
     * data model, a default column model, and a default selection
     * model.
     *
     * @see #createDefaultDataModel
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public MPTable() {
        super();
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm        the data model for the table
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public MPTable(TableModel dm) {
        super(dm);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @see #createDefaultSelectionModel
     */
    public MPTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @param sm        the row selection model for the table
     * @see #createDefaultDataModel
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public MPTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());

    }

    /**
     * Constructs a <code>JTable</code> with <code>numRows</code>
     * and <code>numColumns</code> of empty cells using
     * <code>DefaultTableModel</code>.  The columns will have
     * names of the form "A", "B", "C", etc.
     *
     * @param numRows           the number of rows the table holds
     * @param numColumns        the number of columns the table holds
     * @see javax.swing.table.DefaultTableModel
     */
    public MPTable(int numRows, int numColumns) {
        super(numRows, numColumns);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Constructs a <code>JTable</code> to display the values in the
     * <code>Vector</code> of <code>Vectors</code>, <code>rowData</code>,
     * with column names, <code>columnNames</code>.  The
     * <code>Vectors</code> contained in <code>rowData</code>
     * should contain the values for that row. In other words,
     * the value of the cell at row 1, column 5 can be obtained
     * with the following code:
     * <p>
     * <pre>((Vector)rowData.elementAt(1)).elementAt(5);</pre>
     * <p>
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public MPTable(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Constructs a <code>JTable</code> to display the values in the two dimensional array,
     * <code>rowData</code>, with column names, <code>columnNames</code>.
     * <code>rowData</code> is an array of rows, so the value of the cell at row 1,
     * column 5 can be obtained with the following code:
     * <p>
     * <pre> rowData[1][5]; </pre>
     * <p>
     * All rows must be of the same length as <code>columnNames</code>.
     * <p>
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public MPTable(final Object[][] rowData, final Object[] columnNames) {
        super(rowData, columnNames);
        t = new TableViewPersistenceHandler(this, (JComponent) this.getParent());
    }

    /**
     * Sets the data model for this table to newModel and registers with it for listener notifications from the new data model.
     * Additionally takes care of the registered {@link TableViewPersistenceHandler}.
     * @param model
     */
    @Override
    public void setModel(TableModel model) {
        if (t != null) {
            t.remove();
        }
        super.setModel(model);
        if (t != null) {
            t.set();
        }
    }

    /**
     * Sets the value for the cell in the table model at row and column.
     * Note: Unlike the original JTable implementation, the column is specified in the table <b>TableModel</b> order,
     * and not in the view's column order.
     * This is an important distinction because as the user rearranges the columns in the table,
     * the column at a given index in the view will change.
     * Meanwhile the user's actions never affect the model's column ordering.
     * @param value is the new value.
     * @param col
     * @param row
     */
//    @Override
//    public void setValueAt(Object value, int col, int row) {
//        this.setValueAt(value, row, col);
//    }
//
//    public void setDefaultColumns(Integer[] integer, boolean resizable) {
//
//    }
}