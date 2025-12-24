package dao;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.UserSession;
import model.userdata;
import utils.PasswordService;

/**
 *
 * @author ACER
 */
public class loginDao {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean login(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "SELECT id, username, email, password, role FROM users WHERE email=?";
        
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getEmail());
            ResultSet result = pstm.executeQuery();
            
            if(result.next()){
                String storedPassword = result.getString("password");
                String inputPassword = user.getPassword();
                
                // Check if password is hashed or plain text
                boolean passwordMatch;
                if(PasswordService.isHashed(storedPassword)){
                    // Use BCrypt verification for hashed passwords
                    passwordMatch = PasswordService.verifyPassword(inputPassword, storedPassword);
                } else {
                    // For backward compatibility with plain text passwords
                    passwordMatch = inputPassword.equals(storedPassword);
                }
                
                if(passwordMatch){
                    // Set user session
                    userdata loggedInUser = new userdata();
                    loggedInUser.setId(result.getInt("id"));
                    loggedInUser.setUsername(result.getString("username"));
                    loggedInUser.setEmail(result.getString("email"));
                    loggedInUser.setRole(result.getString("role"));
                    
                    UserSession.getInstance().setUserData(loggedInUser);
                    return true;
                }
            }
        }catch(SQLException e){
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
}