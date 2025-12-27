package dao;

import database.MySqlConnection;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private final MySqlConnection mysql = new MySqlConnection();

    // Get all categories
    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        }

        return categories;
    }

    // Add a new category
    public boolean addCategory(String name) throws SQLException {
        String sql = "INSERT INTO categories(name) VALUES(?)";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            return ps.executeUpdate() > 0;
        }
    }

    // Delete a category by ID
    public boolean deleteCategory(int id) throws SQLException {
        String sql = "DELETE FROM categories WHERE id=?";

        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
