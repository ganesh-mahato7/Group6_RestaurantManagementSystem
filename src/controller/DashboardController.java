package controller;

import model.UserSession;
import view.AddNewProduct;
import view.Dashboard;
import view.Login;

import javax.swing.JOptionPane;

public class DashboardController {

    private final Dashboard dashboard;
    private final UserSession session;

    public DashboardController(Dashboard dashboard) {
        this.dashboard = dashboard;
        this.session = UserSession.getInstance();
    }

    public void open() {
        applyRoleAccess();
        attachListeners();
        dashboard.setVisible(true);
    }

    private void applyRoleAccess() {
        String role = session.getRole();
        if (role == null || role.isEmpty()) {
            JOptionPane.showMessageDialog(dashboard, "Unauthorized access: no role found.");
            System.exit(0);
        }

        role = role.trim().toUpperCase();
        switch (role) {
            case "SCRUM MASTER" -> dashboard.setScrumMasterAccess();
            case "STAFF" -> dashboard.setStaffAccess();
            case "WAITER" -> dashboard.setWaiterAccess();
            default -> {
                JOptionPane.showMessageDialog(dashboard, "Unauthorized role: " + role);
                System.exit(0);
            }
        }
    }

    private void attachListeners() {

        // ✅ NEW PRODUCT navigation
        dashboard.addNewProductListener(e -> openAddNewProduct());

        // Logout
        dashboard.addLogoutListener(e -> logout());
    }

    private void openAddNewProduct() {
        AddNewProduct addNewProduct = new AddNewProduct();
        addNewProduct.setLocationRelativeTo(null);
        addNewProduct.setVisible(true);

        dashboard.dispose(); // optional (remove if you want dashboard to stay open)
    }

    private void logout() {
        session.clearSession();
        dashboard.dispose();

        Login login = new Login();
        new LoginController(login).open();
    }
}
