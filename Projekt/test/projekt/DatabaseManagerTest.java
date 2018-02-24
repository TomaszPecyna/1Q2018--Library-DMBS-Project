/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomek
 */
public class DatabaseManagerTest
{
    static DatabaseManager databaseManager;
    
    public DatabaseManagerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        databaseManager = new DatabaseManager();
        databaseManager.connectToDatabase("inf127284", "INF127284");
        databaseManager.createSampleDatabase();
        databaseManager.initializeDBWithSampleTuples();
    }
    
    @AfterClass
    public static void tearDownClass()
    {
        databaseManager.deleteSampleDatabase();
        databaseManager.disconnectFromDatabase();
    }
    
    @Before
    public void setUp()
    {
        
    }
    
    @After
    public void tearDown()
    {
        
    }
    
    /**
     * Test of findBookWithTitle method, of class DatabaseManager.
     */
    @Test
    public void testfindBookWithTitle()
    {
        System.out.println("execute findBookWithTitle");
        assertEquals("[STEIN, CORMEN, LEISERSON, RIVEST]", 
                databaseManager.findBookWithTitle("WPROWADZENIE DO ALGORYTMOW").getAuthors().toString());
    }
    
    /**
     * Test of findBooksWithAuthor method, of class DatabaseManager.
     */
    @Test
    public void testfindBooksWithAuthor()
    {
        System.out.println("execute findBooksWithAuthor");
        Set<Book> books = databaseManager.findBooksWithAuthor("CORMEN");
        Set<String> titles = new HashSet<>();
        books.forEach((book) ->
        {
            assertTrue(book.getAuthors().contains("CORMEN"));
            titles.add(book.getTitle());
        });
        assertTrue(titles.contains("WPROWADZENIE DO ALGORYTMOW"));
        assertTrue(titles.contains("ALGORITHMIC COMPLEXITY"));
    }
    
    /**
     * Test of findReaderWithId method, of class DatabaseManager.
     */
    @Test
    public void testfindReaderWithId()
    {
        System.out.println("execute findReaderWithId");
        Reader reader = databaseManager.findReaderWithID(new Long(3));
        assertEquals(2, reader.getBooks().size());
    }
    
    /**
     * Test of findArticleWithTitle method, of class DatabaseManager.
     */
    @Test
    public void testfindArticleWithTitle()
    {
        System.out.println("execute findBookWithArticle");
        assertEquals("[MIKA, WEGLARZ, WALIGORA]", 
                databaseManager.findArticleWithTitle("MODELLING AND SOLVING GRID "
                        + "RESOURCE ALLOCATION PROBLEM WITH NETWORK RESOURCES "
                        + "FOR WORKFLOW APPLICATIONS").getAuthors().toString());
    }
    
    /**
     * Test of findArticlesWithAuthor method, of class DatabaseManager.
     */
    @Test
    public void testfindArticlesWithAuthor()
    {
        System.out.println("execute findArticlesWithAuthor");
        Set<Article> articles = databaseManager.findArticlesWithAuthor("WALIGORA");
        Set<String> titles = new HashSet<>();
        articles.forEach((article) ->
        {
            assertTrue(article.getAuthors().contains("WALIGORA"));
            titles.add(article.getTitle());
        });
        assertTrue(titles.contains("MODELLING AND SOLVING GRID RESOURCE ALLOCATION PROBLEM "
                    + "WITH NETWORK RESOURCES FOR WORKFLOW APPLICATIONS"));
        assertTrue(titles.contains("HEURISTIC APPROACHES TO DISCRETE-CONTINUOUS "
                    + "PROJECT SCHEDULING PROBLEMS TO MINIMIZE THE MAKESPAN"));
    }
}
