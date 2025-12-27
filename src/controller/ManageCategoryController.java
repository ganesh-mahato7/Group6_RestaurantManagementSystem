package controller;

import dao.CategoryDao;
import model.Category;
import view.ManageCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        List<Category> categories = dao.getAllCategories();
        DefaultTableModel model = (DefaultTableModel) view.getViewCategoryTable().getModel();
        model.setRowCount(0); // Clear table
        for (Category c : categories) {
            model.addRow(new Object[]{c.getId(), c.getName()});
        }
    }

    // Attach button and table listeners
    private void attachListeners() {
        // Save button
        view.getSaveButton().addActionListener(e -> saveCategory());

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

    // Add/save new category
    public void saveCategory() {
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
    public void deleteCategory(int id) {
        boolean success = dao.deleteCategory(id);
        if (success) {
            JOptionPane.showMessageDialog(view, "Category deleted successfully!");
            loadCategories();
        } else {
            JOptionPane.showMessageDialog(view, "Error deleting category!");
        }
    }
}
