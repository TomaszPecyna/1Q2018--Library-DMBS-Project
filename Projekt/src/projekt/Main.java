/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import GUI.GuiMain;
import java.sql.*; 
import java.util.Properties; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.swing.SwingUtilities;

/**
 *
 * @author Tomek
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        DatabaseManager db = new DatabaseManager();
        GuiMain gui = new GuiMain(db);
        db.connectToDatabase("inf127284", "INF127284");
        db.executeSQLSatement();
        db.createSampleDatabase();
        db.initializeDBWithSampleTuples();
        db.deleteSampleDatabase();
        db.disconnectFromDatabase();
    }  
}
