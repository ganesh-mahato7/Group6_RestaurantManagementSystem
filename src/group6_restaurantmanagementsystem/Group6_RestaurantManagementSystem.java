package group6_restaurantmanagementsystem;

import controller.LoginController;
import database.Database;
import database.MySqlConnection;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.Login;

/**
 *
 * @author ACER
 */
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

        // Test database connection
        Database db = new MySqlConnection();
        if (db.openConnection() != null) {
            System.out.println("Database connection success");
        } else {
            System.out.println("Database connection failed");
        }

        // Open Login UI
        Login loginView = new Login();
        LoginController controller = new LoginController(loginView);
        controller.open();
    }
}
