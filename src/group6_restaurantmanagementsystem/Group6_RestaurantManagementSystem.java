/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group6_restaurantmanagementsystem;
import database.Database;
import database.MySqlConnection;
<<<<<<< HEAD
import javax.swing.JFrame;
import view.Login;
=======
>>>>>>> fd9551270dcb11fc5455b89d5f8611dbd36cb1c5

/**
 *
 * @author ACER
 */
public class Group6_RestaurantManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
<<<<<<< HEAD
               try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        SignIN frame = new SignIN();
        frame.setTitle("Resturant Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
     
=======
        Database db = new MySqlConnection();
        if (db.openConnection() != null) {
            System.out.println("Connection successful");
        }
        else {
            System.out.println("Not successful");
        }
>>>>>>> fd9551270dcb11fc5455b89d5f8611dbd36cb1c5
    }
}