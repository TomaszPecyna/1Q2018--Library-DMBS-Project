/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.Date;

/**
 *
 * @author Tomek
 */
public class BorrowedBook
{
    private Long ID;
    private String title;
    private Date dateFrom;
    private Date dateTo;
    private int numberOfprolongations;
    private boolean available;
    
    public BorrowedBook()
    {
    }

    /**
     * @return the ID
     */
    public Long getID()
    {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Long ID)
    {
        this.ID = ID;
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
     * @return the dateFrom
     */
    public Date getDateFrom()
    {
        return dateFrom;
    }

    /**
     * @param dateFrom the dateFrom to set
     */
    public void setDateFrom(Date dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    /**
     * @return the dateTo
     */
    public Date getDateTo()
    {
        return dateTo;
    }

    /**
     * @param dateTo the dateTo to set
     */
    public void setDateTo(Date dateTo)
    {
        this.dateTo = dateTo;
    }

    /**
     * @return the numberOfprolongations
     */
    public int getNumberOfprolongations()
    {
        return numberOfprolongations;
    }

    /**
     * @param numberOfprolongations the numberOfprolongations to set
     */
    public void setNumberOfprolongations(int numberOfprolongations)
    {
        this.numberOfprolongations = numberOfprolongations;
    }

    /**
     * @return the available
     */
    public boolean isAvailable()
    {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available)
    {
        this.available = available;
    }
}
