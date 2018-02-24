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
import projekt.Book;

/**
 *
 * @author Tomek
 */
public class BookModel extends AbstractTableModel
{
    Map<Integer, String> dictionary;
    List<Book> books;
    public BookModel(Set<Book> books)
    {
        this.books = new ArrayList<Book>(books);
        init();
    }
    
    public BookModel(Book book)
    {
        this.books = new ArrayList<>();
        books.add(book);
        init();
    }
    
    private void init()
    {
        this.dictionary = new HashMap<>();
        dictionary.put(0, "TYTUL");
        dictionary.put(1, "AUTORZY");
        dictionary.put(2, "DZIAL");
        dictionary.put(3, "REGAL");
        dictionary.put(4, "LICZBA DOSTEPNYCH");
    }
    
    @Override
    public String getColumnName(int col) 
    {
        return this.dictionary.get(col);
    }

    @Override
    public int getRowCount()
    {
        return books.size();
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
        Book book = books.get(rowIndex);
        switch(columnIndex)
        {
            case 0: returnValue = book.getTitle(); break;
            case 1: returnValue = book.getAuthors(); break;
            case 2: returnValue = book.getSection(); break;
            case 3: returnValue = book.getBookstand(); break;
            case 4: returnValue = book.getAvailable(); break;
        }
        return returnValue;
    }
    
}
