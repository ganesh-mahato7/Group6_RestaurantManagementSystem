/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import database.MySqlConnection;
import model.userdata;
import java.sql.*;

/**
 *
 * @author ACER
 */
public class userDao {
    MySqlConnection mysql = new MySqlConnection();
    public void signUp(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "insert into users(username, email, password) values(?, ?, ?)";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.executeUpdate();
        }
        catch(SQLException e){
            System.out.print(e);
        }
        finally{
            mysql.closeConnection(conn);
        }
    }
    public boolean check(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "select * from users where username = ? or email = ?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }
        catch(SQLException e){
            System.out.print(e);
        }
        finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
}