/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class Dashboard extends JFrame {

    private static final Logger logger = Logger.getLogger(Dashboard.class.getName());

    public Dashboard() {
        initComponents();
        setTitle("Nepal Restaurant Management System");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Dashboard stays open
        setVisible(true);
    }

    // ===== ROLE BASED ACCESS =====
    public void setScrumMasterAccess() {
        PlaceOrder.setEnabled(true);
        BillAndOrderPlaced.setEnabled(true);
        ViewEditDeleteProduct.setEnabled(true);
        NewProduct.setEnabled(true);
        ManageCategory.setEnabled(true);
        VerifyUser.setEnabled(true);
        ChangePassword.setEnabled(true);

        ViewEditDeleteProduct.setVisible(true);
        NewProduct.setVisible(true);
        ManageCategory.setVisible(true);
        VerifyUser.setVisible(true);
    }

    public void setStaffAccess() {
        PlaceOrder.setEnabled(true);
        BillAndOrderPlaced.setEnabled(true);
        ChangePassword.setEnabled(true);

        ViewEditDeleteProduct.setVisible(false);
        NewProduct.setVisible(false);
        ManageCategory.setVisible(false);
        VerifyUser.setVisible(false);
    }

    public void setWaiterAccess() {
        PlaceOrder.setEnabled(true);
        BillAndOrderPlaced.setEnabled(true);
        ChangePassword.setEnabled(true);

        ViewEditDeleteProduct.setVisible(false);
        NewProduct.setVisible(false);
        ManageCategory.setVisible(false);
        VerifyUser.setVisible(false);
    }

    // ===== MVC LISTENER HOOKS =====
    public void addPlaceOrderListener(ActionListener listener) {
        PlaceOrder.addActionListener(listener);
    }

    public void addBillAndOrderPlacedListener(ActionListener listener) {
        BillAndOrderPlaced.addActionListener(listener);
    }

    public void addViewEditDeleteProductListener(ActionListener listener) {
        ViewEditDeleteProduct.addActionListener(listener);
    }

    public void addNewProductListener(ActionListener listener) {
        NewProduct.addActionListener(listener);
    }

    public void addManageCategoryListener(ActionListener listener) {
        ManageCategory.addActionListener(listener);
    }

    public void addVerifyUserListener(ActionListener listener) {
        VerifyUser.addActionListener(listener);
    }

    public void addChangePasswordListener(ActionListener listener) {
        ChangePassword.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        Logout.addActionListener(listener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        PlaceOrder = new javax.swing.JButton();
        BillAndOrderPlaced = new javax.swing.JButton();
        ViewEditDeleteProduct = new javax.swing.JButton();
        NewProduct = new javax.swing.JButton();
        ManageCategory = new javax.swing.JButton();
        VerifyUser = new javax.swing.JButton();
        ChangePassword = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nepal Restaurant Management System");
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setResizable(false);

        jPanel1.setName("mainPanel"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(33, 37, 41));
        jPanel2.setName("sidebarPanel"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(270, 700));
        jPanel2.setLayout(new java.awt.GridLayout(8, 1, 0, 10));

        PlaceOrder.setBackground(new java.awt.Color(52, 58, 64));
        PlaceOrder.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        PlaceOrder.setForeground(new java.awt.Color(255, 255, 255));
        PlaceOrder.setText("Place Order ");
        PlaceOrder.setBorderPainted(false);
        PlaceOrder.setFocusPainted(false);
        PlaceOrder.setMaximumSize(new java.awt.Dimension(20, 10));
        PlaceOrder.setMinimumSize(new java.awt.Dimension(20, 10));
        PlaceOrder.addActionListener(this::PlaceOrderActionPerformed);
        jPanel2.add(PlaceOrder);

        BillAndOrderPlaced.setBackground(new java.awt.Color(52, 58, 64));
        BillAndOrderPlaced.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        BillAndOrderPlaced.setForeground(new java.awt.Color(255, 255, 255));
        BillAndOrderPlaced.setText("Bill and Order Placed");
        BillAndOrderPlaced.setBorderPainted(false);
        BillAndOrderPlaced.setFocusPainted(false);
        BillAndOrderPlaced.addActionListener(this::BillAndOrderPlacedActionPerformed);
        jPanel2.add(BillAndOrderPlaced);

        ViewEditDeleteProduct.setBackground(new java.awt.Color(52, 58, 64));
        ViewEditDeleteProduct.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        ViewEditDeleteProduct.setForeground(new java.awt.Color(255, 255, 255));
        ViewEditDeleteProduct.setText("View, Edit and Delete Product");
        ViewEditDeleteProduct.setBorderPainted(false);
        ViewEditDeleteProduct.setFocusPainted(false);
        ViewEditDeleteProduct.addActionListener(this::ViewEditDeleteProductActionPerformed);
        jPanel2.add(ViewEditDeleteProduct);

        NewProduct.setBackground(new java.awt.Color(52, 58, 64));
        NewProduct.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        NewProduct.setForeground(new java.awt.Color(255, 255, 255));
        NewProduct.setText("New Produuct");
        NewProduct.setBorderPainted(false);
        NewProduct.setFocusPainted(false);
        NewProduct.addActionListener(this::NewProductActionPerformed);
        jPanel2.add(NewProduct);

        ManageCategory.setBackground(new java.awt.Color(52, 58, 64));
        ManageCategory.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        ManageCategory.setForeground(new java.awt.Color(255, 255, 255));
        ManageCategory.setText("Manage Category");
        ManageCategory.setBorderPainted(false);
        ManageCategory.setFocusPainted(false);
        ManageCategory.addActionListener(this::ManageCategoryActionPerformed);
        jPanel2.add(ManageCategory);

        VerifyUser.setBackground(new java.awt.Color(52, 58, 64));
        VerifyUser.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        VerifyUser.setForeground(new java.awt.Color(255, 255, 255));
        VerifyUser.setText("Verify Users");
        VerifyUser.setBorderPainted(false);
        VerifyUser.setFocusPainted(false);
        VerifyUser.addActionListener(this::VerifyUserActionPerformed);
        jPanel2.add(VerifyUser);

        ChangePassword.setBackground(new java.awt.Color(52, 58, 64));
        ChangePassword.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        ChangePassword.setForeground(new java.awt.Color(255, 255, 255));
        ChangePassword.setText("Change Password");
        ChangePassword.setBorderPainted(false);
        ChangePassword.setFocusPainted(false);
        ChangePassword.addActionListener(this::ChangePasswordActionPerformed);
        jPanel2.add(ChangePassword);

        Logout.setBackground(new java.awt.Color(52, 58, 64));
        Logout.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 13)); // NOI18N
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("Logout");
        Logout.setBorderPainted(false);
        Logout.setFocusPainted(false);
        Logout.addActionListener(this::LogoutActionPerformed);
        jPanel2.add(Logout);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(245, 240, 225));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.setName("contentPanel"); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout(15, 15));

        jPanel4.setBackground(new java.awt.Color(245, 240, 225));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel4.setName("cardsPanel"); // NOI18N
        jPanel4.setLayout(new java.awt.GridLayout(1, 3, 15, 15));
        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);
        jPanel4.getAccessibleContext().setAccessibleName("");

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePasswordActionPerformed
        JOptionPane.showMessageDialog(this, "Change Password clicked!");
    }//GEN-LAST:event_ChangePasswordActionPerformed

    private void VerifyUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerifyUserActionPerformed
        // Open the VerifyUsers window
    java.awt.EventQueue.invokeLater(() -> {
        VerifyUsers verifyUsers = new VerifyUsers();
        verifyUsers.setVisible(true);
    });
    }//GEN-LAST:event_VerifyUserActionPerformed

    private void NewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewProductActionPerformed
        // Open AddNewProduct as a new window without closing Dashboard
    AddNewProduct addProductView = new AddNewProduct();
    addProductView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only this window closes
    addProductView.setVisible(true);
    addProductView.setLocationRelativeTo(this); // Center relative to Dashboard
    }//GEN-LAST:event_NewProductActionPerformed

    private void ViewEditDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEditDeleteProductActionPerformed
        // Open the View/Edit/Delete Product window
    ViewEditDeleteProduct viewEditDeleteProduct = new ViewEditDeleteProduct();

    // Make sure closing this window only disposes it, not the dashboard
    viewEditDeleteProduct.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

    // Show the product management window
    viewEditDeleteProduct.setVisible(true); 
    }//GEN-LAST:event_ViewEditDeleteProductActionPerformed

    private void BillAndOrderPlacedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BillAndOrderPlacedActionPerformed
        // Open the ViewBillOrderPlacedDetails window
    ViewBillOrderPlacedDetails viewBillFrame = new ViewBillOrderPlacedDetails();

    // Ensure closing this window does not close the dashboard
    viewBillFrame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

    // Show the bill/order details window
    viewBillFrame.setVisible(true);
    }//GEN-LAST:event_BillAndOrderPlacedActionPerformed

    private void PlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaceOrderActionPerformed
        PlaceOrder placeOrder = new PlaceOrder();
        placeOrder.setLocationRelativeTo(this); // center on screen
        placeOrder.setVisible(true);
    }//GEN-LAST:event_PlaceOrderActionPerformed

    private void ManageCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageCategoryActionPerformed
        // Open ManageCategory as a new window without closing Dashboard
    ManageCategory manageCategoryView = new ManageCategory();
    manageCategoryView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Only this window closes
    manageCategoryView.setVisible(true);
    manageCategoryView.setLocationRelativeTo(this); // Center relative to Dashboard
    }//GEN-LAST:event_ManageCategoryActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
         int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", 
                                                    "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // close current window
            // Optional: redirect to login page
        }
    }//GEN-LAST:event_LogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            logger.severe(ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BillAndOrderPlaced;
    private javax.swing.JButton ChangePassword;
    private javax.swing.JButton Logout;
    private javax.swing.JButton ManageCategory;
    private javax.swing.JButton NewProduct;
    private javax.swing.JButton PlaceOrder;
    private javax.swing.JButton VerifyUser;
    private javax.swing.JButton ViewEditDeleteProduct;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}

