package controller;

import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;
import view.ViewEditDeleteProduct;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewEditDeleteProductController {

    private final ViewEditDeleteProduct view;
    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    public ViewEditDeleteProductController(ViewEditDeleteProduct view) {
        this.view = view;
        this.productDao = new ProductDao();
        this.categoryDao = new CategoryDao();
        initialize();
    }

    private void initialize() {
        loadCategories();
        loadProducts();
        attachListeners();
    }

    // Load categories into combo box
    private void loadCategories() {
        view.getCmbCategory().removeAllItems();
        List<Category> categories = categoryDao.getAllCategories();
        for (Category c : categories) {
            view.getCmbCategory().addItem(c.getName());
        }
        if (!categories.isEmpty()) view.getCmbCategory().setSelectedIndex(0);
    }

    // Load products into table
    private void loadProducts() {
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        model.setRowCount(0);

        List<Product> products = productDao.getAllProducts();
        for (Product p : products) {
            // Ensure categoryName is never null
            String catName = p.getCategoryName() != null ? p.getCategoryName() : "N/A";
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    catName,
                    p.getPrice()
            });
        }
    }

    // Attach listeners for table and buttons
    private void attachListeners() {
        view.getTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    view.getTxtId().setText(view.getTable().getValueAt(row, 0).toString());
                    view.getTxtName().setText(view.getTable().getValueAt(row, 1).toString());
                    view.getCmbCategory().setSelectedItem(view.getTable().getValueAt(row, 2).toString());
                    view.getTxtPrice().setText(view.getTable().getValueAt(row, 3).toString());
                }
            }
        });

        view.getUpdateButton().addActionListener(e -> updateProduct());
        view.getDeleteButton().addActionListener(e -> deleteProduct());
        view.getClearButton().addActionListener(e -> clearFields());
    }

    private void updateProduct() {
        try {
            int id = Integer.parseInt(view.getTxtId().getText().trim());
            String name = view.getTxtName().getText().trim();
            String categoryName = (String) view.getCmbCategory().getSelectedItem();
            double price = Double.parseDouble(view.getTxtPrice().getText().trim());

            if (name.isEmpty() || categoryName == null || categoryName.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Please fill all fields!");
                return;
            }

            Category category = categoryDao.getCategoryByName(categoryName);
            if (category == null) {
                JOptionPane.showMessageDialog(view, "Selected category not found!");
                return;
            }

            // Use constructor including categoryName for correct table display
            Product p = new Product(id, name, category.getId(), category.getName(), price);

            if (productDao.updateProduct(p)) {
                JOptionPane.showMessageDialog(view, "Product updated successfully!");
                loadProducts();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(view, "Failed to update product!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Invalid ID or price!");
        }
    }

    private void deleteProduct() {
        try {
            int id = Integer.parseInt(view.getTxtId().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this product?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (productDao.deleteProduct(id)) {
                    JOptionPane.showMessageDialog(view, "Product deleted successfully!");
                    loadProducts();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(view, "Failed to delete product!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Please select a valid product to delete!");
        }
    }

    private void clearFields() {
        view.getTxtId().setText("");
        view.getTxtName().setText("");
        if (view.getCmbCategory().getItemCount() > 0) view.getCmbCategory().setSelectedIndex(0);
        view.getTxtPrice().setText("");
    }
}
