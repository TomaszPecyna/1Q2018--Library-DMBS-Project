/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author Tomek
 */
public class DatabaseManager
{


    private Connection conn;
    public DatabaseManager()
    {
    }
    public void connectToDatabase(String user, String password)
    {
        Properties connectionProps = new Properties(); 
        connectionProps.put("user", user); 
        connectionProps.put("password", password); 
        try
        { 
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//admlab2.cs.put.poznan.pl:1521/"
                 + "dblab02_students.cs.put.poznan.pl", connectionProps); 
            System.out.println("Connection established"); 
        } 
        catch(SQLException ex) 
        { 
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
                    "Could not establish connection to database", ex); 
            System.exit(-1); 
        }
    }
    public void disconnectFromDatabase()
    {
        try     
        {
            conn.close();            
            System.out.println("Connection closed");
        }         
        catch (SQLException ex)
        {            
           Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);        
        }   
    }
    
    public void executeSQLSatement()
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id_prac, nazwisko, placa_pod from pracownicy"); 
            while(rs.next()) 
            { 
                System.out.println(rs.getInt(1) + " "
                        + rs.getString(2)
                        + " " + rs.getFloat(3)); 
            } 
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) { /* kod obsługi */} 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
    }
    
    public void createSampleDatabase()
    {
        Statement stmt = null; 
        
        try
        { 
            stmt = conn.createStatement();
            stmt.executeUpdate("create table CZYTELNICY("
                    + "NUMER_KARTY number(10) not null primary key,"
                    + "HASLO varchar(255) not null,"
                    + "IMIE varchar(255) not null,"
                    + "NAZWISKO varchar(255) not null,"
                    + "PESEL number(11) not null,"
                    + "ADRES varchar(255) not null,"
                    + "TELEFON number(9),"
                    + "EMAIL varchar(255))");
            
            stmt.executeUpdate("create table KSIAZKI ("
                    + "TYTUL varchar(255) not null primary key,"
                    + "DZIAL varchar(1) not null,"
                    + "REGAL number(2) not null)");
            
            stmt.executeUpdate("create table KSIAZKI_EGZEMPLARZE ("
                    + "ID_KSIAZKI number(10) not null primary key,"
                    + "STATUS varchar(255) not null,"
                    + "TYTUL varchar(255) not null constraint FK_KE_01 references KSIAZKI(TYTUL))");
            
            stmt.executeUpdate("create table WYPOZYCZENIA("
                    + "DATA_WYPOZYCZENIA DATE not null,"
                    + "DATA_ZWRORU DATE not null,"
                    + "LICZBA_PRZEDLUZEN number(1),"
                    + "ID_KSIAZKI number(10) not null constraint FK_WYP_01 references KSIAZKI_EGZEMPLARZE(ID_KSIAZKI),"
                    + "NUMER_KARTY number(10) not null constraint FK_WYP_02 references CZYTELNICY(NUMER_KARTY),"
                    + "constraint PK_WYP_01 primary key(ID_KSIAZKI, NUMER_KARTY))");
            
            stmt.executeUpdate("create table AUTORZY("
                    + "IMIE varchar(255) not null,"
                    + "NAZWISKO varchar(255) not null primary key,"
                    + "ROK_URODZENIA number(4))");
            
            stmt.executeUpdate("create table KSIAZKI_AUTORZY("
                    + "TYTUL varchar(255) not null constraint FK_KA_01 references KSIAZKI(TYTUL),"
                    + "NAZWISKO varchar(255) not null constraint FK_KA_02 references AUTORZY(NAZWISKO),"
                    + "constraint PK_KA_01 primary key(TYTUL, NAZWISKO))");
            
            stmt.executeUpdate("create table CZASOPISMA("
                    + "NAZWA varchar(255) not null primary key,"
                    + "DATA_WYDANIA DATE not null)");
            
            stmt.executeUpdate("create table ARTYKULY("
                    + "NAZWA varchar(255) not null primary key,"
                    + "LICZBA_CYTOWAN number(10) default 0 not null,"
                    + "NAZWA_CZASOPISMA varchar(255) not null constraint FK_ART_01 references CZASOPISMA(NAZWA))");
            
            stmt.executeUpdate("create table AUTORZY_ARTYKULY("
                    + "NAZWA_ARTYKULU varchar(255) not null constraint FK_AA_01 references ARTYKULY(NAZWA),"
                    + "NAZWISKO varchar(255) not null constraint FK_AA_02 references AUTORZY(NAZWISKO),"
                    + "constraint PK_AA_01 primary key(NAZWA_ARTYKULU, NAZWISKO))");
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        }
        finally
        { 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
    }
    public void deleteSampleDatabase()
    {
        Statement stmt = null;
        try
        { 
            stmt = conn.createStatement();
            List<String> tablesToDrop = new ArrayList<>();
            tablesToDrop.add("AUTORZY_ARTYKULY");
            tablesToDrop.add("ARTYKULY");
            tablesToDrop.add("CZASOPISMA");
            tablesToDrop.add("KSIAZKI_AUTORZY");
            tablesToDrop.add("AUTORZY");
            tablesToDrop.add("WYPOZYCZENIA");
            tablesToDrop.add("KSIAZKI_EGZEMPLARZE");
            tablesToDrop.add("CZYTELNICY");
            tablesToDrop.add("KSIAZKI");

            for(String table : tablesToDrop)
            {
                try
                { 
                    stmt.executeUpdate("drop table " + table);
                } 
                catch(SQLException ex) 
                { 
                    System.out.println("Statement error " + ex.toString()); 
                } 
            }
        }
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        }
        finally
        { 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
    }
    
    public void initializeDBWithSampleTuples()
    {
        Statement stmt = null; 
        
        try
        { 
            /* insert some readers into database */
            stmt = conn.createStatement();
            stmt.executeUpdate("insert into CZYTELNICY values(1, 'haslo', 'IMIE1',"
                    + "'NAZWISKO1', '12345678900', 'ADRES1', '123456789', 'email1')");
            stmt.executeUpdate("insert into CZYTELNICY values(2, 'haslo', 'IMIE2',"
                    + "'NAZWISKO2', '12345678901', 'ADRES2', null, null)");
            stmt.executeUpdate("insert into CZYTELNICY values(3, 'haslo', 'IMIE3',"
                    + "'NAZWISKO3', '12345678902', 'ADRES3', '123456789', null)");
            stmt.executeUpdate("insert into CZYTELNICY values(4, 'haslo', 'IMIE4',"
                    + "'NAZWISKO4', '12345678903', 'ADRES4', null, 'email2')");
            stmt.executeUpdate("insert into CZYTELNICY values(5, 'haslo', 'IMIE5',"
                    + "'NAZWISKO5', '12345678904', 'ADRES5', null, null)");
            
            /* insert some books into database */
            stmt.executeUpdate("insert into KSIAZKI values('WPROWADZENIE DO ALGORYTMOW', 'A', 1)");
            stmt.executeUpdate("insert into KSIAZKI values('ANALIZA MATEMATYCZNA W ZADANIACH 1', 'B', 2)");
            stmt.executeUpdate("insert into KSIAZKI values('STALE NATURY', 'C', 4)");
            stmt.executeUpdate("insert into KSIAZKI values('FIZYKA 1', 'C', 6)");
            stmt.executeUpdate("insert into KSIAZKI values('ALGORITHMIC COMPLEXITY', 'A', 1)");
            
            /* insert some bBook instances */
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(1, 'NA POLCE',"
                    + "'WPROWADZENIE DO ALGORYTMOW')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(2, 'NA POLCE',"
                    + "'WPROWADZENIE DO ALGORYTMOW')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(3, 'WYPOZYCZONA',"
                    + "'WPROWADZENIE DO ALGORYTMOW')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(4, 'WYPOZYCZONA',"
                    + "'ANALIZA MATEMATYCZNA W ZADANIACH 1')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(5, 'WYPOZYCZONA',"
                    + "'ANALIZA MATEMATYCZNA W ZADANIACH 1')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(6, 'NA POLCE',"
                    + "'STALE NATURY')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(7, 'NA POLCE',"
                    + "'STALE NATURY')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(8, 'NA POLCE',"
                    + "'FIZYKA 1')");
            stmt.executeUpdate("insert into KSIAZKI_EGZEMPLARZE values(9, 'NA POLCE',"
                    + "'ALGORITHMIC COMPLEXITY')");
            
            /* insert some borrowed books */
            stmt.executeUpdate("insert into WYPOZYCZENIA values('20180101', '20180201',"
                    + "0, 3, 3)");
            stmt.executeUpdate("insert into WYPOZYCZENIA values('20171220', '20180203',"
                    + "1, 4, 3)");
            stmt.executeUpdate("insert into WYPOZYCZENIA values('20171101', '20171229',"
                    + "2, 5, 5)");
            
            /* insert some authors */
            stmt.executeUpdate("insert into AUTORZY values('THOMAS', 'CORMEN', 1956)");
            stmt.executeUpdate("insert into AUTORZY values('CHARLES', 'LEISERSON', 1953)");
            stmt.executeUpdate("insert into AUTORZY values('RONALD', 'RIVEST', 1947)");
            stmt.executeUpdate("insert into AUTORZY values('CLIFFORD', 'STEIN', 1965)");
            stmt.executeUpdate("insert into AUTORZY values('LECH', 'WLODARSKI', 1916)");
            stmt.executeUpdate("insert into AUTORZY values('WLODZIMIERZ', 'KRYSICKI', 1905)");
            stmt.executeUpdate("insert into AUTORZY values('JOHN', 'BARROW', 1952)");
            stmt.executeUpdate("insert into AUTORZY values('ROBERT', 'RESNICK', 1923)");
            stmt.executeUpdate("insert into AUTORZY values('DAVID', 'HALLIDAY', 1916)");
            
            stmt.executeUpdate("insert into AUTORZY values('GRZEGORZ', 'WALIGORA', null)");
            stmt.executeUpdate("insert into AUTORZY values('JAN', 'WEGLARZ', 1947)");
            stmt.executeUpdate("insert into AUTORZY values('MAREK', 'MIKA', null)");
            
            /* insert connections between books and authors */
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('WPROWADZENIE DO ALGORYTMOW',"
                    + "'CORMEN')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('WPROWADZENIE DO ALGORYTMOW',"
                    + "'LEISERSON')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('WPROWADZENIE DO ALGORYTMOW',"
                    + "'RIVEST')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('WPROWADZENIE DO ALGORYTMOW',"
                    + "'STEIN')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('ANALIZA MATEMATYCZNA W ZADANIACH 1',"
                    + "'WLODARSKI')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('ANALIZA MATEMATYCZNA W ZADANIACH 1',"
                    + "'KRYSICKI')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('STALE NATURY',"
                    + "'BARROW')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('FIZYKA 1',"
                    + "'RESNICK')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('FIZYKA 1',"
                    + "'HALLIDAY')");
            stmt.executeUpdate("insert into KSIAZKI_AUTORZY values('ALGORITHMIC COMPLEXITY',"
                    + "'CORMEN')");
            
            /* insert some science journals*/
            
            stmt.executeUpdate("insert into CZASOPISMA values('JOURNAL OF SCHEDULING',"
                    + "'20110601')");
            stmt.executeUpdate("insert into CZASOPISMA values('COMPUTATIONAL"
                    + " OPTIMIZATION AND APPLICATIONS',"
                    + "'20110301')");
            
            /* insert some science articles*/
            
            stmt.executeUpdate("insert into ARTYKULY values('MODELLING AND SOLVING"
                    + " GRID RESOURCE ALLOCATION PROBLEM WITH NETWORK RESOURCES"
                    + " FOR WORKFLOW APPLICATIONS',"
                    + "25, 'JOURNAL OF SCHEDULING')");
            stmt.executeUpdate("insert into ARTYKULY values('HEURISTIC APPROACHES"
                    + " TO DISCRETE-CONTINUOUS PROJECT SCHEDULING PROBLEMS"
                    + " TO MINIMIZE THE MAKESPAN',"
                    + "18, 'COMPUTATIONAL OPTIMIZATION AND APPLICATIONS')");
            
            /* insert connections between science articles and authors */
            
            stmt.executeUpdate("insert into AUTORZY_ARTYKULY values('"
                    + "MODELLING AND SOLVING GRID RESOURCE ALLOCATION PROBLEM "
                    + "WITH NETWORK RESOURCES FOR WORKFLOW APPLICATIONS',"
                    + "'MIKA')");
            stmt.executeUpdate("insert into AUTORZY_ARTYKULY values('"
                    + "MODELLING AND SOLVING GRID RESOURCE ALLOCATION PROBLEM "
                    + "WITH NETWORK RESOURCES FOR WORKFLOW APPLICATIONS',"
                    + "'WEGLARZ')");
            stmt.executeUpdate("insert into AUTORZY_ARTYKULY values('"
                    + "MODELLING AND SOLVING GRID RESOURCE ALLOCATION PROBLEM "
                    + "WITH NETWORK RESOURCES FOR WORKFLOW APPLICATIONS',"
                    + "'WALIGORA')");
            stmt.executeUpdate("insert into AUTORZY_ARTYKULY values('"
                    + "HEURISTIC APPROACHES TO DISCRETE-CONTINUOUS "
                    + "PROJECT SCHEDULING PROBLEMS TO MINIMIZE THE MAKESPAN',"
                    + "'WALIGORA')");
            
            
        }
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
    }
    
    public Reader findReaderWithID(Long ID)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        Reader reader = new Reader();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from CZYTELNICY where NUMER_KARTY = '" + ID + "'"); 
            while(rs.next()) 
            { 
                reader.setID(rs.getLong(1));
                reader.setName(rs.getString(3));
                reader.setSurname(rs.getString(4));
                reader.setPESEL(rs.getLong(5));
                reader.setAddress(rs.getString(6));
                reader.setPhone(rs.getLong(7));
                reader.setEmail(rs.getString(8));
            } 
            rs = stmt.executeQuery("select * from WYPOZYCZENIA"
                    + " where NUMER_KARTY = '" + reader.getID() + "'"); 
            Set<BorrowedBook> books = new HashSet<>();
            while(rs.next()) 
            { 
                BorrowedBook bb = new BorrowedBook();
                bb.setDateFrom(rs.getDate(1));
                bb.setDateTo(rs.getDate(2));
                bb.setID(rs.getLong(4));
                bb.setNumberOfprolongations(rs.getInt(3));
                books.add(bb);
            }
            PreparedStatement pstmt = conn.prepareStatement("select tytul from"
                    + " KSIAZKI_EGZEMPLARZE where ID_KSIAZKI=?");
            for (BorrowedBook bb : books)
            {
                pstmt.setLong(1, bb.getID());
                rs = pstmt.executeQuery();
                while(rs.next()) 
                { 
                    bb.setTitle(rs.getString(1));
                }
            }
            reader.setBooks(books);
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) { /* kod obsługi */} 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return reader;
    }
    
    public Book findBookWithTitle(String title)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        Book book = new Book();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from KSIAZKI where tytul = '" + title + "'"); 
            while(rs.next()) 
            { 
                book.setTitle(rs.getString(1));
                book.setSection(rs.getString(2));
                book.setBookstand(rs.getInt(3));
            } 
            rs = stmt.executeQuery("select NAZWISKO from KSIAZKI_AUTORZY"
                    + " where tytul = '" + title + "'"); 
            Set<String> authors = new HashSet<>();
            while(rs.next()) 
            { 
                authors.add(rs.getString(1));
            }
            book.setAuthors(authors);
            PreparedStatement pstmt = conn.prepareStatement("select COUNT(*) from"
            + " KSIAZKI_EGZEMPLARZE where tytul=? AND status ='NA POLCE'"); 
            pstmt.setString(1, book.getTitle());
            rs = pstmt.executeQuery();
            while(rs.next()) 
            { 
                book.setAvailable(rs.getInt(1));
            }
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) { /* kod obsługi */} 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return book;
    }
    public Set<Book> findBooksWithAuthor(String author)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        Set<Book> books = new HashSet<>();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT K.TYTUL, DZIAL, REGAL FROM"
                    + " KSIAZKI_AUTORZY KA JOIN KSIAZKI K ON KA.TYTUL = K.TYTUL"
                    + " WHERE NAZWISKO = '" + author + "'"); 
            while(rs.next()) 
            { 
                Book book = new Book();
                book.setTitle(rs.getString(1));
                book.setSection(rs.getString(2));
                book.setBookstand(rs.getInt(3));
                books.add(book);
            } 
            PreparedStatement pstmt = conn.prepareStatement("select NAZWISKO from KSIAZKI_AUTORZY where tytul=?"); 
            for (Book currentBook : books)
            {
                pstmt.setString(1, currentBook.getTitle());
                rs = pstmt.executeQuery();
                Set<String> authors = new HashSet<>();
                while(rs.next()) 
                { 
                    authors.add(rs.getString(1));
                }
                currentBook.setAuthors(authors);
            }
            pstmt = conn.prepareStatement("select COUNT(*) from"
                    + " KSIAZKI_EGZEMPLARZE where tytul=? AND status ='NA POLCE'"); 
            for (Book currentBook : books)
            {
                pstmt.setString(1, currentBook.getTitle());
                rs = pstmt.executeQuery();
                while(rs.next()) 
                { 
                    currentBook.setAvailable(rs.getInt(1));
                }
            }
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) 
                {
                } 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                {
                } 
            } 
        }
        return books;
    }
    
    public Article findArticleWithTitle(String title)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        Article article = new Article();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from ARTYKULY where NAZWA = '" + title + "'"); 
            while(rs.next()) 
            { 
                article.setTitle(rs.getString(1));
                article.setNumberOfCitations(rs.getLong(2));
                article.setTitleOfJournal(rs.getString(3));
            } 
            rs = stmt.executeQuery("select NAZWISKO from AUTORZY_ARTYKULY"
                    + " where NAZWA_ARTYKULU = '" + title + "'"); 
            Set<String> authors = new HashSet<>();
            while(rs.next()) 
            { 
                authors.add(rs.getString(1));
            }
            article.setAuthors(authors);
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) { /* kod obsługi */} 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return article;
    }
    
    public Set<Article> findArticlesWithAuthor(String author)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        Set<Article> articles = new HashSet<>();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT A.NAZWA, LICZBA_CYTOWAN, A.NAZWA_CZASOPISMA FROM"
                    + " AUTORZY_ARTYKULY AA JOIN ARTYKULY A ON AA.NAZWA_ARTYKULU = A.NAZWA"
                    + " WHERE NAZWISKO = '" + author + "'"); 
            while(rs.next()) 
            { 
                Article article = new Article();
                article.setTitle(rs.getString(1));
                article.setNumberOfCitations(rs.getLong(2));
                article.setTitleOfJournal(rs.getString(3));
                articles.add(article);
            } 
            PreparedStatement pstmt = conn.prepareStatement("select NAZWISKO from AUTORZY_ARTYKULY where NAZWA_ARTYKULU=?"); 
            for (Article currentArticle : articles)
            {
                pstmt.setString(1, currentArticle.getTitle());
                rs = pstmt.executeQuery();
                Set<String> authors = new HashSet<>();
                while(rs.next()) 
                { 
                    authors.add(rs.getString(1));
                }
                currentArticle.setAuthors(authors);
            }
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) 
                {
                } 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                {
                } 
            } 
        }
        return articles;
    }
    
    public String borrowABook(Long readerID, Long bookID)
    {
        BorrowedBook bb = selectBookWithID(bookID);
        if(bb.isAvailable() == false)
        {
            return "ALREADY BORROWED";
        }
        Reader reader = findReaderWithID(readerID);
        Statement stmt = null; 
        java.sql.Date sqlCurrentDate = new java.sql.Date(System.currentTimeMillis());
        java.sql.Date sqlReturnDate = new java.sql.Date(LocalDate.now().plusMonths(1).toEpochDay());
        try
        { 
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE KSIAZKI_EGZEMPLARZE SET "
                    + "STATUS = 'WYPOZYCZONA' WHERE ID_KSIAZKI = '" + bookID + "'");
            stmt.executeUpdate("insert into WYPOZYCZENIA values("
                    + "'" + sqlCurrentDate + "',"
                    + "'" + sqlReturnDate + "',"
                    + "0,"
                    + bookID + ","
                    + readerID + ")");
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return "DONE";
    }
    
    public String returnABook(Long bookID)
    {
        BorrowedBook bb = selectBookWithID(bookID);
        if(bb.isAvailable() == true)
        {
            return "ALREADY BORROWED";
        }
        Statement stmt = null; 
        try
        { 
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE KSIAZKI_EGZEMPLARZE SET "
                    + "STATUS = 'NA POLCE' WHERE ID_KSIAZKI = '" + bookID + "'");
            stmt.executeUpdate("DELETE FROM WYPOZYCZENIA "
                    + "WHERE ID_KSIAZKI = '" + bookID + "'");
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return "DONE";
    }
    
    private BorrowedBook selectBookWithID(Long ID)
    {
        Statement stmt = null; 
        ResultSet rs = null; 
        BorrowedBook bBook = new BorrowedBook();
        
        try
        { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from KSIAZKI_EGZEMPLARZE where ID_KSIAZKI = '" + ID + "'"); 
            while(rs.next()) 
            { 
                bBook.setID(rs.getLong(1));
                String status = rs.getString(2);
                boolean available = false;
                if(status.equals("NA POLCE")) available = true;
                bBook.setAvailable(available);
                bBook.setTitle(rs.getString(3));
            } 
        } 
        catch(SQLException ex) 
        { 
            System.out.println("Statement error " + ex.toString()); 
        } 
        finally
        { 
            if(rs != null) 
            { 
                try
                {        
                    rs.close();
                } 
                catch(SQLException e) { /* kod obsługi */} 
            } 
            if
            (stmt != null) 
            { 
                try
                { 
                    stmt.close(); 
                } 
                catch(SQLException e) 
                { /* kod obsługi */} 
            } 
        }
        return bBook;
    }
}
