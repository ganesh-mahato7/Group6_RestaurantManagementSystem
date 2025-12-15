/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;

/**
 *
 * @author ACER
 */

public class MySqlConnection implements Database{

    @Override
    public Connection openConnection() {
        try{
            String username = "root";
            String password = "king@123";
            String database = "hello";
            Connection connection;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password);
            if(connection == null){
                System.out.println("Database connection fail");
            }
            else{
                System.out.println("Database connection success");
            }
            return connection;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
   
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("Connection close");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try{
            Statement stmp = conn.createStatement();
            ResultSet result = stmp.executeQuery(query);
            return result;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try{
            Statement stmp = conn.createStatement();
            int result = stmp.executeUpdate(query);
            return result; 
        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }
<<<<<<< HEAD

    @Override
    public void closeCoonection(Connection conn) {
try{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
           
        }catch(SQLException e){
            System.out.println(e);
        }    }

    public void closeConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

=======
>>>>>>> fd9551270dcb11fc5455b89d5f8611dbd36cb1c5
    
}