package dao;

import database.MySqlConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    private final MySqlConnection db = new MySqlConnection();

    // Check if a user exists by email
    public boolean existsByEmail(String email) throws Exception {
        Connection conn = db.openConnection();
        String sql = "SELECT 1 FROM users WHERE email=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        boolean exists = rs.next();

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return exists;
    }

    // Fetch user by email (used for reset password / profile)
    public User getUserByEmail(String email) throws Exception {
        Connection conn = db.openConnection();
        String sql = "SELECT fullname, email, password, role FROM users WHERE email=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User(
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return user;
    }

    // ✅ PROPER LOGIN METHOD (IMPORTANT)
    public User login(String email, String hashedPassword) throws Exception {
        Connection conn = db.openConnection();
        String sql = """
            SELECT fullname, email, password, role
            FROM users
            WHERE email=? AND password=?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, hashedPassword);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User(
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
            );
        }

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return user;
    }

    // Register a new user
    public boolean registerUser(User user) throws Exception {
        Connection conn = db.openConnection();
        String sql = "INSERT INTO users(fullname, email, password, role) VALUES (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getFullName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());

        int result = ps.executeUpdate();

        ps.close();
        db.closeConnection(conn);
        return result > 0;
    }

    // Update password by email
    public boolean updatePasswordByEmail(String email, String hashedPassword) throws Exception {
        Connection conn = db.openConnection();
        String sql = "UPDATE users SET password=? WHERE email=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, hashedPassword);
        ps.setString(2, email);

        int updated = ps.executeUpdate();

        ps.close();
        db.closeConnection(conn);
        return updated > 0;
    }
}
