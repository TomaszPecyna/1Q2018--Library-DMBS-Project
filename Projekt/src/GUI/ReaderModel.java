/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.AbstractTableModel;
import projekt.Reader;

/**
 *
 * @author Tomek
 */
public class ReaderModel extends AbstractTableModel
{
    Map<Integer, String> dictionary;
    List<Reader> readers;
    public ReaderModel(Set<Reader> readers)
    {
        this.readers = new ArrayList<Reader>(readers);
        init();
    }
    
    public ReaderModel(Reader reader)
    {
        this.readers = new ArrayList<>();
        readers.add(reader);
        init();
    }
    
    private void init()
    {
        this.dictionary = new HashMap<>();
        dictionary.put(0, "ID");
        dictionary.put(1, "IMIE");
        dictionary.put(2, "NAZWISKO");
        dictionary.put(3, "PESEL");
        dictionary.put(4, "ADRES");
        dictionary.put(5, "TELEFON");
        dictionary.put(6, "EMAIL");
        dictionary.put(7, "KSIAZKI");
    }
    
    @Override
    public String getColumnName(int col) 
    {
        return this.dictionary.get(col);
    }

    @Override
    public int getRowCount()
    {
        return readers.size();
    }

    @Override
    public int getColumnCount()
    {
        return dictionary.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Object returnValue = null;
        Reader reader = readers.get(rowIndex);
        switch(columnIndex)
        {
            case 0: returnValue = reader.getID(); break;
            case 1: returnValue = reader.getName(); break;
            case 2: returnValue = reader.getSurname(); break;
            case 3: returnValue = reader.getPESEL(); break;
            case 4: returnValue = reader.getAddress(); break;
            case 5: returnValue = reader.getPhone(); break;
            case 6: returnValue = reader.getEmail(); break;
            case 7: 
            {
                String names = "";
                for (String name : reader.getBookNames())
                {
                    names = names + name + " \n";
                }
                returnValue = names;
                break;
            }
        }
        return returnValue;
    }
}
