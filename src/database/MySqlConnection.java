package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnection implements Database {

    private static final String DB_NAME = System.getProperty("DB_NAME", "restaurant_management");
    private static final String DB_USER = System.getProperty("DB_USER", "root");
    private static final String DB_PASSWORD = System.getProperty("DB_PASSWORD", "king@123");
    private static final String DB_HOST = System.getProperty("DB_HOST", "localhost");
    private static final String DB_PORT = System.getProperty("DB_PORT", "3306");

    @Override
    public Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = String.format(
                    "jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    DB_HOST, DB_PORT, DB_NAME
            );
            Connection conn = java.sql.DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            System.out.println(conn != null ? "Database connection successful" : "Database connection failed");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
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
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            return conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Query execution error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            return conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Update execution error: " + e.getMessage());
            return -1;
        }
    }

    // ------------------- MAIN METHOD -------------------
    public static void main(String[] args) {
        MySqlConnection db = new MySqlConnection();
        Connection conn = db.openConnection();

        if (conn != null) {
            try {
                // Example query: show all tables in the database
                ResultSet rs = db.runQuery(conn, "SHOW TABLES");
                System.out.println("Tables in database '" + DB_NAME + "':");
                while (rs != null && rs.next()) {
                    System.out.println(rs.getString(1));
                }
            } catch (SQLException e) {
                System.err.println("Error reading data: " + e.getMessage());
            } finally {
                db.closeConnection(conn);
            }
        }
    }
}
