package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;

public class PasswordResetDao {
    private final MySqlConnection db = new MySqlConnection();

    // Create password reset token
    public boolean createToken(String email, String token, Instant expiresAt) throws Exception {
        String query = "INSERT INTO password_resets(email, token, expires_at) VALUES(?,?,?)";
        
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, token);
            
            // Convert Instant to SQL Timestamp
            ps.setTimestamp(3, Timestamp.from(expiresAt));
            
            int result = ps.executeUpdate();
            return result > 0;
        }
    }

    // Verify password reset token
    public boolean verifyToken(String email, String token) throws Exception {
        String query = "SELECT * FROM password_resets WHERE email=? AND token=?";
        
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, email);
            ps.setString(2, token);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
