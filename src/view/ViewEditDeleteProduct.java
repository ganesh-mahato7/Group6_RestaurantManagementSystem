/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ViewEditDeleteProductController;
import dao.CategoryDao;
import dao.ProductDao;
import model.Category;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewEditDeleteProduct extends JFrame {

    private final ProductDao productDao = new ProductDao();
    private final CategoryDao categoryDao = new CategoryDao();

    public ViewEditDeleteProduct() {
        initComponents();
        loadCategories();
        loadProducts();
        attachListeners();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        Name = new javax.swing.JTextField();
        Category = new javax.swing.JComboBox<>();
        Price = new javax.swing.JTextField();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("View, Edit and Delete Product");

        jLabel2.setText("ID");

        jLabel3.setText("Name");

        jLabel4.setText("Category");

        jLabel5.setText("Price");

        Name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameKeyReleased(evt);
            }
        });

        Category.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CategoryKeyReleased(evt);
            }
        });

        Update.setText("Update");
        Update.addActionListener(this::UpdateActionPerformed);

        Delete.setText("Delete");
        Delete.addActionListener(this::DeleteActionPerformed);

        Clear.setText("Clear");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Category", "Price"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(Clear))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Update)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(Delete)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Update)
                    .addComponent(Delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Clear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameKeyReleased
        // TODO add your handling code here:
        ValidateField();
    }//GEN-LAST:event_NameKeyReleased

    private void CategoryKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CategoryKeyReleased
        // TODO add your handling code here:
         ValidateField();
    }//GEN-LAST:event_CategoryKeyReleased

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        try {
            int id = Integer.parseInt(ID.getText().trim());
            String name = Name.getText().trim();
            String categoryName = (String) Category.getSelectedItem();
            double price = Double.parseDouble(Price.getText().trim());

            if (name.isEmpty() || categoryName == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            Category category = categoryDao.getCategoryByName(categoryName);
            if (category == null) {
                JOptionPane.showMessageDialog(this, "Category not found!");
                return;
            }

            Product p = new Product(id, name, category.getId(), category.getName(), price);

            if (productDao.updateProduct(p)) {
                JOptionPane.showMessageDialog(this, "Product updated successfully!");
                loadProducts();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update product!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID or price!");
        }
    }//GEN-LAST:event_UpdateActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        loadCategories();
        loadProducts();   // fill table with products
    }//GEN-LAST:event_formComponentShown

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            ID.setText(jTable1.getValueAt(row, 0).toString());
            Name.setText(jTable1.getValueAt(row, 1).toString());
            Category.setSelectedItem(jTable1.getValueAt(row, 2).toString());
            Price.setText(jTable1.getValueAt(row, 3).toString());
            ValidateField();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        try {
            int id = Integer.parseInt(ID.getText().trim());
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (productDao.deleteProduct(id)) {
                    JOptionPane.showMessageDialog(this, "Product deleted successfully!");
                    loadProducts();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete product!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Select a valid product to delete!");
        }
    }//GEN-LAST:event_DeleteActionPerformed

        // ======== Utility Methods ========
    public void loadCategories() {
        Category.removeAllItems();
        for (Category c : categoryDao.getAllCategories()) {
            Category.addItem(c.getName());
        }
        if (Category.getItemCount() > 0) Category.setSelectedIndex(0);
    }

    public void loadProducts() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Product p : productDao.getAllProducts()) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCategoryName(), p.getPrice()});
        }
    }

    public void clearFields() {
        ID.setText("");
        Name.setText("");
        if (Category.getItemCount() > 0) Category.setSelectedIndex(0);
        Price.setText("");
    }

    private void attachListeners() {
        // No additional listeners needed as handled above
    }

    private void ValidateField() {
        boolean enable = !Name.getText().trim().isEmpty()
                && Category.getSelectedItem() != null
                && !Price.getText().trim().isEmpty();
        Update.setEnabled(enable);
        Delete.setEnabled(!ID.getText().trim().isEmpty());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(() -> {
        ViewEditDeleteProduct view = new ViewEditDeleteProduct();
        ViewEditDeleteProductController controller = new ViewEditDeleteProductController(view);
        view.setVisible(true);
    });

    }
 
   // ======== MVC Getters ========
    public JTable getTable() { return jTable1; }
    public JTextField getTxtId() { return ID; }
    public JTextField getTxtName() { return Name; }
    public JComboBox<String> getCmbCategory() { return Category; }
    public JTextField getTxtPrice() { return Price; }
    public JButton getUpdateButton() { return Update; }
    public JButton getDeleteButton() { return Delete; }
    public JButton getClearButton() { return Clear; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Category;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Delete;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Price;
    private javax.swing.JButton Update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
