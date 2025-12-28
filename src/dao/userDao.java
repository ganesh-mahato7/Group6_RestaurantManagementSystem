package dao;

import database.MySqlConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDao {

    private final MySqlConnection db = new MySqlConnection();

    // Check if user exists by email
    public boolean existsByEmail(String email) {
        boolean exists = false;
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM users WHERE email=?")) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // Get user by email
    public User getUserByEmail(String email) {
        User user = null;
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email=?")) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Login
    public User login(String email, String hashedPassword) {
        User user = null;
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?")) {
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Register user
    public boolean registerUser(User user) {
        boolean success = false;
        String sql = "INSERT INTO users(fullname,email,password,role,status) VALUES (?,?,?,?,?)";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, "Pending"); // Default status
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Update password
    public boolean updatePasswordByEmail(String email, String hashedPassword) {
        boolean success = false;
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users SET password=? WHERE email=?")) {
            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // ================= VERIFY USERS =================

    // Get all users for VerifyUsers screen
    public ArrayList<User> getAllUsers(String emailSearch) {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE email LIKE ?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + emailSearch + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToUser(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update user status
    public boolean updateUserStatus(int id, String status) {
        boolean updated = false;
        String sql = "UPDATE users SET status=? WHERE id=?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            updated = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    // Utility method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws Exception {
        return new User(
                rs.getInt("id"),
                rs.getString("fullname"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getString("status")
        );
    }
}
