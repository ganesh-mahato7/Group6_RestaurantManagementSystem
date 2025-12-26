/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.userdata;
import java.sql.*;

public class userDao {

    MySqlConnection mysql = new MySqlConnection();

    // SIGN UP
    public void signUp(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println(e);
        } 
        finally {
            mysql.closeConnection(conn);
        }
    }

    // CHECK IF USER ALREADY EXISTS
    public boolean check(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            ResultSet result = pstm.executeQuery();
            return result.next();
        } 
        catch (SQLException e) {
            System.out.println(e);
        } 
        finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    // RESET PASSWORD
    public boolean updatePassword(String email, String newPassword) {

        Connection conn = mysql.openConnection();
        String sql = "UPDATE users SET password=? WHERE email=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, newPassword);
            pst.setString(2, email);

            return pst.executeUpdate() > 0;

        } 
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        finally {
            mysql.closeConnection(conn);
        }
    }

    // CHECK IF EMAIL EXISTS
    public boolean existsByEmail(String email) {
        Connection conn = mysql.openConnection();
        if (conn == null) {
            System.out.println("Error: Cannot connect to database. Check MySQL connection.");
            return false;
        }
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();
            if (result.next()) {
                return result.getInt(1) > 0;
            }
        } 
        catch (SQLException e) {
            System.out.println(e);
        } 
        finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    // UPDATE PASSWORD BY EMAIL (for password recovery)
    public boolean updatePasswordByEmail(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE users SET password=? WHERE email=?";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, newPassword);
            pst.setString(2, email);
            return pst.executeUpdate() > 0;
        } 
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        finally {
            mysql.closeConnection(conn);
        }
    }
}
