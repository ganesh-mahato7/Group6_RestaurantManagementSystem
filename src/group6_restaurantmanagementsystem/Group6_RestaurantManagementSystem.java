/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group6_restaurantmanagementsystem;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import controller.LoginController;
import view.SignIN;

/**
 *
 * @author ACER
 */
public class Group6_RestaurantManagementSystem {

    /**
     * @param args the command line arguments
     */
       public static void main(String[] args) {
        // Set the Nimbus look and feel
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                 IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
            // Continue with default look and feel if Nimbus is unavailable
        }

        // Create the Login view
        Login loginView = new Login();

        // Create the LoginController and pass the view
        LoginController loginController = new LoginController(loginView);

        // Open the login window
        loginController.open();
    }
    
}