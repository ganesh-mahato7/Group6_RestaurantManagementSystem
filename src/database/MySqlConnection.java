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

public class MySqlConnection implements Database {

    private static final String DB_NAME = System.getProperty("DB_NAME", "restaurant_management");
    private static final String DB_USER = System.getProperty("DB_USER", "root");
    private static final String DB_PASSWORD = System.getProperty("DB_PASSWORD", "");
    private static final String DB_HOST = System.getProperty("DB_HOST", "localhost");
    private static final String DB_PORT = System.getProperty("DB_PORT", "3306");

    @Override
    public Connection openConnection() {
        try {
            try {
                // Ensure MySQL JDBC driver is loaded when present
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ignore) {
                // Driver not found on classpath; DriverManager may still resolve if using Java SPI
            }

            String url = String.format(
                "jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                DB_HOST, DB_PORT, DB_NAME);

            Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);

            if (connection == null) {
                System.out.println("Database connection failed");
            } else {
                System.out.println("Database connection successful");
            }
            return connection;
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query execution error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Update execution error: " + e.getMessage());
            return -1;
        }
    }

    // Alias for backward compatibility
    public Connection openconnection() {
        return openConnection();
    }
}