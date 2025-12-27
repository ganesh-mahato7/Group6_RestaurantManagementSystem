package group6_restaurantmanagementsystem;

import controller.LoginController;
import database.MySqlConnection;
import database.Database;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.Login;

public class Group6_RestaurantManagementSystem {
    public static void main(String[] args) {

        // Set Nimbus Look and Feel
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
        }

        // Test DB
        Database db = new MySqlConnection();
        if (db.openConnection() != null) System.out.println("DB connected");

        // Launch Login
        Login loginView = new Login();
        new LoginController(loginView).open();
    }
}
