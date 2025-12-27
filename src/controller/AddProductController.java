package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import view.AddNewProduct;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class AddProductController {

    private final AddNewProduct view;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public AddProductController(AddNewProduct view) {
        this.view = view;
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();
    }

    // Load categories into combo box
    public void loadCategories() {
        view.getCategoryBox().removeAllItems();
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            view.getCategoryBox().addItem(c.getName());
        }
    }

    // Save product to database
    public void saveProduct() {
        String name = view.getNameField().getText().trim();
        String category = (String) view.getCategoryBox().getSelectedItem();
        String priceText = view.getPriceField().getText().trim();

        if (name.isEmpty() || priceText.isEmpty() || category == null) {
            JOptionPane.showMessageDialog(view, "Please enter all fields!");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Price must be a number!");
            return;
        }

        Product product = new Product(name, category, price);
        boolean success = productDao.addProduct(product); // assumes you update ProductDao.addProduct(Product)

        if (success) {
            JOptionPane.showMessageDialog(view, "Product saved successfully!");
            view.getNameField().setText("");
            view.getPriceField().setText("");
            view.getCategoryBox().setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(view, "Failed to save product!");
        }
    }
}
