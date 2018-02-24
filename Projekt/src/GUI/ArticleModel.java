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
import projekt.Article;

/**
 *
 * @author Tomek
 */
public class ArticleModel extends AbstractTableModel
{
    Map<Integer, String> dictionary;
    List<Article> articles;
    public ArticleModel(Set<Article> articles)
    {
        this.articles = new ArrayList<Article>(articles);
        init();
    }
    
    public ArticleModel(Article article)
    {
        this.articles = new ArrayList<>();
        articles.add(article);
        init();
    }
    
    private void init()
    {
        this.dictionary = new HashMap<>();
        dictionary.put(0, "TYTUL");
        dictionary.put(1, "LICZBA CYTOWAN");
        dictionary.put(2, "NAZWA CZASOPISMA");
        dictionary.put(3, "AUTORZY");
    }
    
    @Override
    public String getColumnName(int col) 
    {
        return this.dictionary.get(col);
    }

    @Override
    public int getRowCount()
    {
        return articles.size();
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
        Article article = articles.get(rowIndex);
        switch(columnIndex)
        {
            case 0: returnValue = article.getTitle(); break;
            case 1: returnValue = article.getNumberOfCitations(); break;
            case 2: returnValue = article.getTitleOfJournal(); break;
            case 3: returnValue = article.getAuthors(); break;
        }
        return returnValue;
    }
}
