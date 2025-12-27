package controller;

import dao.CategoryDao;
import model.Category;
import view.ManageCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public final class ManageCategoryController {

    private final ManageCategory view;
    private final CategoryDao dao;

    public ManageCategoryController(ManageCategory view) {
        this.view = view;
        this.dao = new CategoryDao(); // Handles DB operations
        open(); // Initialize when controller is created
    }

    // Initialize: load table and attach listeners
    public void open() {
        loadCategories();
        attachListeners();
    }

    // Load all categories into the table
    private void loadCategories() {
        SwingUtilities.invokeLater(() -> {
            List<Category> categories = dao.getAllCategories();
            DefaultTableModel model = (DefaultTableModel) view.getViewCategoryTable().getModel();
            model.setRowCount(0); // Clear table

            for (Category c : categories) {
                model.addRow(new Object[]{c.getId(), c.getName()});
            }
        });
    }

    // Attach listeners to buttons and table
    private void attachListeners() {
        // Save button
        view.getSaveButton().addActionListener(e -> saveCategory());

        // Clear button
        view.getClearButton().addActionListener(e -> view.getAddCategoryField().setText(""));

        // Delete category on table row click
        view.getViewCategoryTable().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = view.getViewCategoryTable().getSelectedRow();
                if (row >= 0) {
                    int id = Integer.parseInt(view.getViewCategoryTable().getValueAt(row, 0).toString());
                    int confirm = JOptionPane.showConfirmDialog(view, "Delete this category?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteCategory(id);
                    }
                }
            }
        });
    }

    // Save/add a new category
    private void saveCategory() {
        String name = view.getAddCategoryField().getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter a category name!");
            return;
        }

        boolean success = dao.addCategory(name);
        if (success) {
            JOptionPane.showMessageDialog(view, "Category added successfully!");
            view.getAddCategoryField().setText("");
            loadCategories();
        } else {
            JOptionPane.showMessageDialog(view, "Category already exists or could not be added.");
        }
    }

    // Delete category by ID
    private void deleteCategory(int id) {
        boolean success = dao.deleteCategory(id);
        if (success) {
            JOptionPane.showMessageDialog(view, "Category deleted successfully!");
            loadCategories();
        } else {
            JOptionPane.showMessageDialog(view, "Error deleting category!");
        }
    }
}
