package controller;

import dao.UserDao;
import model.User;
import model.UserSession;
import utils.PasswordService;
import view.ChangePassword;
import view.Login;

import javax.swing.JOptionPane;

public class ChangePasswordController {

    private final ChangePassword view;
    private final UserDao userDao;

    public ChangePasswordController(ChangePassword view) {
        this.view = view;
        this.userDao = new UserDao();

        // Attach listeners here (GOOD)
        this.view.addUpdateListener(e -> handleUpdate());
        this.view.addClearListener(e -> handleClear());
    }

    // ================= UPDATE PASSWORD =================
    private void handleUpdate() {
        try {
            String oldPassword = view.getOldPassword().trim();
            String newPassword = view.getNewPassword().trim();
            String confirmPassword = view.getConfirmPassword().trim();

            // 1. Empty check
            if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(view, "All fields are required!");
                return;
            }

            // 2. Match check
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(view, "Passwords do not match!");
                return;
            }

            // 3. Length rule
            if (newPassword.length() < 6) {
                JOptionPane.showMessageDialog(view, "Password must be at least 6 characters!");
                return;
            }

            // 4. Session validation
            UserSession session = UserSession.getInstance();
            if (!session.isLoggedIn()) {
                JOptionPane.showMessageDialog(view, "Session expired. Please login again.");
                view.dispose();
                openLogin();
                return;
            }

            // 5. Fetch user
            User user = userDao.getUserByEmail(session.getEmail());
            if (user == null) {
                JOptionPane.showMessageDialog(view, "User not found!");
                return;
            }

            // 6. Old password verification
            if (!PasswordService.verifyPassword(oldPassword, user.getPassword())) {
                JOptionPane.showMessageDialog(view, "Old password is incorrect!");
                return;
            }

            // 7. Prevent reuse
            if (PasswordService.verifyPassword(newPassword, user.getPassword())) {
                JOptionPane.showMessageDialog(view, "New password cannot be same as old!");
                return;
            }

            // 8. Hash and update
            String hashedPassword = PasswordService.hashPassword(newPassword);
            boolean updated = userDao.updatePasswordByEmail(user.getEmail(), hashedPassword);

            if (!updated) {
                JOptionPane.showMessageDialog(view, "Password update failed!");
                return;
            }

            // 9. Logout & redirect
            session.logout();
            JOptionPane.showMessageDialog(
                    view,
                    "Password changed successfully. Please login again."
            );

            view.dispose();
            openLogin();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Unexpected error occurred!");
        }
    }

    // ================= CLEAR =================
    private void handleClear() {
        view.clearFields();
    }

    // ================= OPEN LOGIN =================
    private void openLogin() {
        Login loginView = new Login();
        new LoginController(loginView); // attach listeners
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }
}
