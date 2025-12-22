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
        String sql = "INSERT INTO staff (staff_code, first_name, last_name, email, mobile, address, position, salary, hire_date, birth_date, gender, status, user_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, staff.getStaffCode());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
            ps.setString(4, staff.getEmail());
            ps.setString(5, staff.getMobile());
            ps.setString(6, staff.getAddress());
            ps.setString(7, staff.getPosition());
            ps.setDouble(8, staff.getSalary());
            ps.setDate(9, staff.getHireDate());
            ps.setDate(10, staff.getBirthDate());
            ps.setString(11, staff.getGender());
            ps.setString(12, staff.getStatus());
            ps.setInt(13, staff.getUserId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Add staff error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT * FROM staff";
        Connection conn = mysql.openConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setStaffCode(rs.getString("staff_code"));
                staff.setFirstName(rs.getString("first_name"));
                staff.setLastName(rs.getString("last_name"));
                staff.setEmail(rs.getString("email"));
                staff.setMobile(rs.getString("mobile"));
                staff.setAddress(rs.getString("address"));
                staff.setPosition(rs.getString("position"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setHireDate(rs.getDate("hire_date"));
                staff.setBirthDate(rs.getDate("birth_date"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getString("status"));
                staff.setUserId(rs.getInt("user_id"));
                staffList.add(staff);
            }

        } catch (SQLException e) {
            System.out.println("Get all staff error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return staffList;
    }
     public Staff getStaffById(int id) {
        String sql = "SELECT * FROM staff WHERE id=?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setId(rs.getInt("id"));
                staff.setStaffCode(rs.getString("staff_code"));
                staff.setFirstName(rs.getString("first_name"));
                staff.setLastName(rs.getString("last_name"));
                staff.setEmail(rs.getString("email"));
                staff.setMobile(rs.getString("mobile"));
                staff.setAddress(rs.getString("address"));
                staff.setPosition(rs.getString("position"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setHireDate(rs.getDate("hire_date"));
                staff.setBirthDate(rs.getDate("birth_date"));
                staff.setGender(rs.getString("gender"));
                staff.setStatus(rs.getString("status"));
                staff.setUserId(rs.getInt("user_id"));
                return staff;
            }
        } catch (SQLException e) {
            System.out.println("Get staff by ID error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
     
     public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET staff_code=?, first_name=?, last_name=?, email=?, mobile=?, address=?, position=?, salary=?, hire_date=?, birth_date=?, gender=?, status=?, user_id=? WHERE id=?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, staff.getStaffCode());
            ps.setString(2, staff.getFirstName());
            ps.setString(3, staff.getLastName());
            ps.setString(4, staff.getEmail());
            ps.setString(5, staff.getMobile());
            ps.setString(6, staff.getAddress());
            ps.setString(7, staff.getPosition());
            ps.setDouble(8, staff.getSalary());
            ps.setDate(9, staff.getHireDate());
            ps.setDate(10, staff.getBirthDate());
            ps.setString(11, staff.getGender());
            ps.setString(12, staff.getStatus());
            ps.setInt(13, staff.getUserId());
            ps.setInt(14, staff.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update staff error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
}
