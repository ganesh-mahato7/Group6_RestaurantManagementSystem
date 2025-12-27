package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import view.AddNewProduct;

import javax.swing.*;
import java.util.List;

public final class AddProductController {

    private final AddNewProduct view;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public AddProductController(AddNewProduct view) {
        this.view = view;
        this.categoryDao = new CategoryDao();
        this.productDao = new ProductDao();

        loadCategories();
        attachListeners();
    }

    // Load categories into combo box
    public void loadCategories() {
        view.getCategoryBox().removeAllItems();
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            view.getCategoryBox().addItem(c.getName());
        }
        if (!categories.isEmpty()) {
            view.getCategoryBox().setSelectedIndex(0);
        }
    }

    // Save product
    public void saveProduct() {
        String name = view.getNameField().getText().trim();
        String categoryName = (String) view.getCategoryBox().getSelectedItem();
        String priceText = view.getPriceField().getText().trim();

        if (name.isEmpty() || categoryName == null || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill all fields!");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Price must be a number!");
            return;
        }

        // Get category ID from name
        Category category = categoryDao.getCategoryByName(categoryName);
        if (category == null) {
            JOptionPane.showMessageDialog(view, "Selected category not found!");
            return;
        }

        Product product = new Product(name, category.getId(), price);

        if (productDao.save(product)) {
            JOptionPane.showMessageDialog(view, "Product saved successfully!");
            clearForm();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to save product!");
        }
    }

    // Clear form (public so the view can call it)
    public void clearForm() {
        view.getNameField().setText("");
        view.getPriceField().setText("");
        if (view.getCategoryBox().getItemCount() > 0) {
            view.getCategoryBox().setSelectedIndex(0);
        }
    }

    // Attach button listeners
    private void attachListeners() {
        view.getSaveButton().addActionListener(e -> saveProduct());
        view.getClearButton().addActionListener(e -> clearForm());
    }
}
