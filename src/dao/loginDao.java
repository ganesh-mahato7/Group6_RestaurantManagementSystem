package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import model.UserSession;
import utils.PasswordService;

public class LoginDao {

    private final MySqlConnection mysql = new MySqlConnection();

    /**
     * Authenticate user by email and password.
     * @param email user's email
     * @param passwordInput user's input password
     * @return true if login successful, false otherwise
     */
    public boolean login(String email, String passwordInput) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT id, fullname, email, password, role FROM users WHERE email=?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                String storedPassword = result.getString("password");

                // Verify password
                boolean passwordMatch = PasswordService.verifyPassword(passwordInput, storedPassword);

                if (passwordMatch) {
                    // Set user session using the User model
                    User loggedInUser = new User(
                        result.getString("fullname"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("role")
                    );

                    // ✅ Fixed: use the correct method
                    UserSession.getInstance().setUser(loggedInUser);

                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }

        return false;
    }
}
