/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.SwingUtilities;
import projekt.DatabaseManager;
import javax.swing.JFrame;

/**
 *
 * @author Tomek
 */
public class GuiMain
{ 
    DatabaseManager db;
    public GuiMain(DatabaseManager db)
    {
        this.db = db;
        SwingUtilities.invokeLater(() -> {
        LoginWindow loginWindow = new LoginWindow(db);
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
