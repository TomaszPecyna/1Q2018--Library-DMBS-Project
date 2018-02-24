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
public class Article
{
    private String title;
    private Long numberOfCitations;
    private String titleOfJournal;
    private Set<String> authors;
    
    public Article()
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
     * @return the numberOfCitations
     */
    public Long getNumberOfCitations()
    {
        return numberOfCitations;
    }

    /**
     * @param numberOfCitations the numberOfCitations to set
     */
    public void setNumberOfCitations(Long numberOfCitations)
    {
        this.numberOfCitations = numberOfCitations;
    }

    /**
     * @return the titleOfJournal
     */
    public String getTitleOfJournal()
    {
        return titleOfJournal;
    }

    /**
     * @param titleOfJournal the titleOfJournal to set
     */
    public void setTitleOfJournal(String titleOfJournal)
    {
        this.titleOfJournal = titleOfJournal;
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
}
