/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tomek
 */
public class Model extends AbstractTableModel
{
    public Model()
    {
    }
    @Override
    public int getRowCount()
    {
        return 0;
    }

    @Override
    public int getColumnCount()
    {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return rowIndex + columnIndex;
    }
    
}
