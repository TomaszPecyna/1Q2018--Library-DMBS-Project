/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.Set;

/**
 *
 * @author Tomek
 */
public class Book
{
    private String title;
    private String section;
    private Integer bookstand;
    private Set<String> authors;
    private int available;
    
    public Book()
    {
    }

    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the section
     */
    public String getSection()
    {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section)
    {
        this.section = section;
    }

    /**
     * @return the bookstand
     */
    public Integer getBookstand()
    {
        return bookstand;
    }

    /**
     * @param bookstand the bookstand to set
     */
    public void setBookstand(Integer bookstand)
    {
        this.bookstand = bookstand;
    }

    /**
     * @return the authors
     */
    public Set<String> getAuthors()
    {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(Set<String> authors)
    {
        this.authors = authors;
    }

    /**
     * @return the available
     */
    public int getAvailable()
    {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(int available)
    {
        this.available = available;
    }
}
