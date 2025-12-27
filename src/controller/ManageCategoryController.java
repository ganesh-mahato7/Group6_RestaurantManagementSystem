package controller;

import dao.CategoryDao;
import model.Category;
import view.ManageCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ManageCategoryController {

    private final ManageCategory view;
    private final CategoryDao dao;

    public ManageCategoryController(ManageCategory view) {
        this.view = view;
        this.dao = new CategoryDao(); // Handles DB operations
    }

    // Call this from the UI constructor
    public void open() {
        loadCategories();
        attachListeners();
    }

    // Load all categories into table
    private void loadCategories() {
        try {
            List<Category> categories = dao.getAllCategories();
            DefaultTableModel model = (DefaultTableModel) view.getViewCategoryTable().getModel();
            model.setRowCount(0); // Clear table
            for (Category c : categories) {
                model.addRow(new Object[]{c.getId(), c.getName()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error loading categories: " + e.getMessage());
        }
    }

    // Attach button and table listeners
    private void attachListeners() {
        // Save button
        view.getSaveButton().addActionListener(e -> addCategory());

        // Clear button
        view.getClearButton().addActionListener(e -> view.getAddCategoryField().setText(""));

        // Table row click to delete
        view.getViewCategoryTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = view.getViewCategoryTable().getSelectedRow();
                if (row >= 0) {
                    Object idObj = view.getViewCategoryTable().getValueAt(row, 0);
                    int id = Integer.parseInt(idObj.toString());
                    int confirm = JOptionPane.showConfirmDialog(view, "Delete this category?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteCategory(id);
                    }
                }
            }
        });
    }

    // Add new category
    private void addCategory() {
        String name = view.getAddCategoryField().getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a category name!");
            return;
        }

        try {
            boolean success = dao.addCategory(name); // returns true if inserted
            if (success) {
                JOptionPane.showMessageDialog(view, "Category added successfully!");
                view.getAddCategoryField().setText("");
                loadCategories();
            } else {
                JOptionPane.showMessageDialog(view, "Category already exists or could not be added.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error adding category: " + e.getMessage());
        }
    }

    // Delete category by ID
    private void deleteCategory(int id) {
        try {
            boolean success = dao.deleteCategory(id);
            if (success) {
                JOptionPane.showMessageDialog(view, "Category deleted successfully!");
                loadCategories();
            } else {
                JOptionPane.showMessageDialog(view, "Error deleting category!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error deleting category: " + e.getMessage());
        }
    }

    // Optional helper method for UI to call save directly
    public boolean saveCategory(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return false;
        }
        try {
            boolean success = dao.addCategory(categoryName.trim());
            if (success) {
                loadCategories();
            }
            return success;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error saving category: " + e.getMessage());
            return false;
        }
    }
}
