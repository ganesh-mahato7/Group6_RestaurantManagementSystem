package dao;

import database.MySqlConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDao {

    private final MySqlConnection mysql = new MySqlConnection();

    // Save a Product object to database
    public boolean save(Product product) {
        String sql = "INSERT INTO products(name, category, price) VALUES(?, ?, ?)";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error saving product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Add new product directly using parameters (for AddNewProduct)
    public boolean addProduct(String name, String category, double price) {
        Product product = new Product(name, category, price);
        return save(product);
    }

    // Optional: Update existing product
    public boolean update(Product product) {
        if (product.getId() <= 0) {
            throw new IllegalArgumentException("Product ID must be set for update.");
        }

        String sql = "UPDATE products SET name = ?, category = ?, price = ? WHERE id = ?";
        try (Connection conn = mysql.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
