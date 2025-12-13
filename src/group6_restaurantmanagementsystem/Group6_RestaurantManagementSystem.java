/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group6_restaurantmanagementsystem;
import database.Database;
import database.MySqlConnection;

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
        Database db = new MySqlConnection();
        if (db.openConnection() != null) {
            System.out.println("Connection successful");
        }
        else {
            System.out.println("Not successful");
        }
    }
}