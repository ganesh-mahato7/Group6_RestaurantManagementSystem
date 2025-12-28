/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.CategoryDao;
import dao.ProductDao;
import controller.BillController;
import model.Bill;
import model.BillItem;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrder extends javax.swing.JFrame {

    public PlaceOrder() {
        initComponents();
        initCategoryCombo();
        initListeners();

        Quantity.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        ProductPrice.setEditable(false);
        Total.setEditable(false);
    }

    // Load categories on startup
    private void initCategoryCombo() {
        CategoryDao categoryDao = new CategoryDao();
        List<String> categoryNames = new ArrayList<>();
        for (var c : categoryDao.getAllCategories()) {
            categoryNames.add(c.getName());
        }
        Category.setModel(new DefaultComboBoxModel<>(categoryNames.toArray(new String[0])));
    }

    // Add table listeners
    private void initListeners() {
        // Update product fields when a product is selected
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0) {
                    String productName = jTable1.getValueAt(selectedRow, 0).toString();
                    ProductDao productDao = new ProductDao();
                    Product p = productDao.getProductByName(productName);
                    if (p != null) {
                        ProductName.setText(p.getName());
                        ProductPrice.setText(String.valueOf(p.getPrice()));
                        calculateTotal();
                    }
                }
            }
        });

        // Update total when quantity changes
        Quantity.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                calculateTotal();
            }
        });

        // Clear product selection
        Clear.addActionListener(e -> clearProductSelection());
    }

    // Calculate total based on price and quantity
    private void calculateTotal() {
    try {
        double price = Double.parseDouble(ProductPrice.getText().isEmpty() ? "0" : ProductPrice.getText());
        int qty = (int) Quantity.getValue();
        double total = price * qty;
        Total.setText(String.format("%.2f", total));
    } catch (NumberFormatException e) {
        Total.setText("0.00");
    }
}

    // Clear product selection fields
    private void clearProductSelection() {
        ProductName.setText("");
        ProductPrice.setText("");
        Quantity.setValue(1);
        Total.setText("");
        jTable1.clearSelection();
    }
    
    private void updateGrandTotal() {
    DefaultTableModel cart = (DefaultTableModel) jTable2.getModel();
    double grandTotal = 0;
    for (int i = 0; i < cart.getRowCount(); i++) {
        Object value = cart.getValueAt(i, 3);
        double total = 0;
        if (value instanceof Number) {
            total = ((Number) value).doubleValue();
        } else if (value instanceof String) {
            total = Double.parseDouble((String) value);
        }
        grandTotal += total;
    }
    grandtotalnumber.setText(String.format("%.2f", grandTotal));
}
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CustomerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PhoneNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        Category = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        ProductName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ProductPrice = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Quantity = new javax.swing.JSpinner();
        jLabel13 = new javax.swing.JLabel();
        Total = new javax.swing.JTextField();
        Clear = new javax.swing.JButton();
        AddToCart = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        GrandTotal = new javax.swing.JLabel();
        grandtotalnumber = new javax.swing.JLabel();
        GenerateBill = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 24)); // NOI18N
        jLabel1.setText("Place Order");

        jLabel2.setText("Bill ID:");

        jLabel3.setText("--");

        jLabel4.setText("Customer Detail");

        jLabel5.setText("Customer Name");

        jLabel6.setText("Phone Number");

        jLabel7.setText("Email");

        jLabel8.setText("Category");

        Category.addActionListener(this::CategoryActionPerformed);

        jLabel9.setText("Search");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setText("Name");

        jLabel11.setText("Price");

        jLabel12.setText("Quantity");

        jLabel13.setText("Total");

        Clear.setBackground(new java.awt.Color(204, 0, 0));
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setText("Clear");

        AddToCart.setBackground(new java.awt.Color(0, 153, 102));
        AddToCart.setForeground(new java.awt.Color(255, 255, 255));
        AddToCart.setText("Add To Cart");
        AddToCart.addActionListener(this::AddToCartActionPerformed);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price ", "Quantity", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        GrandTotal.setText("Grand Total");

        grandtotalnumber.setText("000");

        GenerateBill.setBackground(new java.awt.Color(0, 153, 102));
        GenerateBill.setForeground(new java.awt.Color(255, 255, 255));
        GenerateBill.setText("Generate Bill");
        GenerateBill.addActionListener(this::GenerateBillActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(141, 141, 141)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(GrandTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(grandtotalnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GenerateBill, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(294, 294, 294)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(AddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(Quantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ProductName, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel12))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ProductPrice)
                                            .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Clear)
                                    .addComponent(AddToCart))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GrandTotal)
                    .addComponent(grandtotalnumber)
                    .addComponent(GenerateBill))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GenerateBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateBillActionPerformed
        // TODO add your handling code here:
        try {
        DefaultTableModel cart = (DefaultTableModel) jTable2.getModel();
        if (cart.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Cart is empty");
            return;
        }

        ArrayList<BillItem> items = new ArrayList<>();
        double subTotal = 0;

        for (int i = 0; i < cart.getRowCount(); i++) {
            String name = cart.getValueAt(i, 0).toString();
            double price = Double.parseDouble(cart.getValueAt(i, 1).toString());
            int qty = Integer.parseInt(cart.getValueAt(i, 2).toString());
            double total = Double.parseDouble(cart.getValueAt(i, 3).toString());

            items.add(new BillItem(name, price, qty, total));
            subTotal += total;
        }

        double discount = subTotal * 0.05;   // 5%
        double tax = (subTotal - discount) * 0.13; // 13%
        double grandTotal = subTotal - discount + tax;

        BillController controller = new BillController();
        Bill bill = new Bill();

        bill.setBillNumber(controller.generateBillNumber());
        bill.setCustomerName(CustomerName.getText());
        bill.setPhone(PhoneNumber.getText());
        bill.setEmail(Email.getText());
        bill.setSubTotal(subTotal);
        bill.setDiscount(discount);
        bill.setTax(tax);
        bill.setGrandTotal(grandTotal);
        bill.setCreatedBy(1); // logged-in user id
        bill.setItems(items);

        controller.generateBill(bill);

        jLabel3.setText(bill.getBillNumber());
        grandtotalnumber.setText(String.format("%.2f", grandTotal));

        JOptionPane.showMessageDialog(
            this,
            "Bill Generated Successfully!\nBill No: " + bill.getBillNumber(),
            "Success",
            JOptionPane.INFORMATION_MESSAGE
        );

        // Clear cart & fields
        cart.setRowCount(0);
        CustomerName.setText("");
        PhoneNumber.setText("");
        Email.setText("");
        clearProductSelection();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        e.printStackTrace();
    }
    }//GEN-LAST:event_GenerateBillActionPerformed

    private void AddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartActionPerformed
        // TODO add your handling code here:
        if (ProductName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a product first");
            return;
        }
        try {
            DefaultTableModel cart = (DefaultTableModel) jTable2.getModel();
            double price = Double.parseDouble(ProductPrice.getText());
            int qty = (int) Quantity.getValue();
            double total = price * qty;

            cart.addRow(new Object[]{
                    ProductName.getText(),
                    price,
                    qty,
                    total
            });

            // Update grand total
            double grandTotal = 0;
            for (int i = 0; i < cart.getRowCount(); i++) {
                grandTotal += (double) cart.getValueAt(i, 3);
            }
            grandtotalnumber.setText(String.format("%.2f", grandTotal));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_AddToCartActionPerformed

    private void CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoryActionPerformed
        String selectedCategory = (String) Category.getSelectedItem();
    if (selectedCategory != null && !selectedCategory.isEmpty()) {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.getProductsByCategoryName(selectedCategory); // now works

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // clear previous items
        for (Product p : products) {
            model.addRow(new Object[]{p.getName()});
        }
    }
    }//GEN-LAST:event_CategoryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new PlaceOrder().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToCart;
    private javax.swing.JComboBox<String> Category;
    private javax.swing.JButton Clear;
    private javax.swing.JTextField CustomerName;
    private javax.swing.JTextField Email;
    private javax.swing.JButton GenerateBill;
    private javax.swing.JLabel GrandTotal;
    private javax.swing.JTextField PhoneNumber;
    private javax.swing.JTextField ProductName;
    private javax.swing.JTextField ProductPrice;
    private javax.swing.JSpinner Quantity;
    private javax.swing.JTextField Search;
    private javax.swing.JTextField Total;
    private javax.swing.JLabel grandtotalnumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}
