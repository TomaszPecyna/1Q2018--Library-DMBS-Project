/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Tomek
 */
public class Reader
{
    private Long ID;
    private String name;
    private String surname;
    private Long PESEL;
    private String address;
    private Long phone;
    private String email;
    private Set<BorrowedBook> books;
    
    public Reader()
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
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * @return the PESEL
     */
    public Long getPESEL()
    {
        return PESEL;
    }

    /**
     * @param PESEL the PESEL to set
     */
    public void setPESEL(Long PESEL)
    {
        this.PESEL = PESEL;
    }

    /**
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public Long getPhone()
    {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone)
    {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the books
     */
    public Set<BorrowedBook> getBooks()
    {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<BorrowedBook> books)
    {
        this.books = books;
    }
    
    public Set<String> getBookNames()
    {
        Set<String> names = new HashSet<>();
        for(BorrowedBook book : books) names.add(book.getTitle());
        return names;
    }
}
