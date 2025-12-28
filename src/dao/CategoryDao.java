package dao;

import database.MySqlConnection;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class CategoryDao {

    private final MySqlConnection db = new MySqlConnection();

    // ==================== GET ALL CATEGORIES ====================
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories ORDER BY name";

        try (Connection conn = db.openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // ==================== GET CATEGORY BY NAME ====================
    public Category getCategoryByName(String name) {
        String sql = "SELECT id, name FROM categories WHERE name = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Category(rs.getInt("id"), rs.getString("name"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ==================== ADD NEW CATEGORY ====================
    public boolean addCategory(String name) {
        if (name == null || name.trim().isEmpty()) return false;

        // Check if category already exists
        if (getCategoryByName(name) != null) return false;

        String sql = "INSERT INTO categories (name) VALUES (?)";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name.trim());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== DELETE CATEGORY ====================
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";
        try (Connection conn = db.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
