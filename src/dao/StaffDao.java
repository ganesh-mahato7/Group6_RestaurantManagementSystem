/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bidhya
 */
public class StaffDao {
    MySqlConnection mysql = new MySqlConnection();
    public boolean addStaff(Staff staff) {
        String sql = "INSERT INTO staff (staff_code, full_name, email, mobile, role, salary) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, staff.getStaffCode());
            ps.setString(2, staff.getFullName());
            ps.setString(3, staff.getEmail());
            ps.setString(4, staff.getMobile());
            ps.setDouble(5, staff.getSalary());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Add staff error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    

    
}
