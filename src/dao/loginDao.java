/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.userdata;

/**
 *
 * @author ACER
 */
public class loginDao {
    MySqlConnection mysql = new MySqlConnection();
    
    public boolean login(userdata user){
        Connection conn = mysql.openConnection();
        String sql = "Select * From customers where email=? and password=?";
        try(PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getEmail());
            ResultSet result = pstm.executeQuery();
            return result.next();
        }catch(SQLException e){
            System.out.print(e);
        }finally{
            mysql.closeConnection(conn);
        }
        return false;
    }
    
    
    
}

