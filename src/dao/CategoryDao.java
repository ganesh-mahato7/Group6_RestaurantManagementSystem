package dao;

import database.MySqlConnection;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private final MySqlConnection mysql = new MySqlConnection();

    // ===== Get all categories (full objects) =====
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching categories: " + e.getMessage());
            e.printStackTrace();
        }

        return categories;
    }

    // ===== Get all category names (for combo boxes) =====
    public List<String> getAllCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        String sql = "SELECT name FROM categories";

        try (Connection conn = mysql.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                categoryNames.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("Error fetching category names: " + e.getMessage());
            e.printStackTrace();
        }

        return categoryNames;
    }

    // ===== Add a new category =====
    public boolean addCategory(String name) {
        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding category: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ===== Delete a category by ID =====
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id=?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting category: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
