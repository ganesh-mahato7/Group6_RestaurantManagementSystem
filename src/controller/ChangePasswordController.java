/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import model.User;
import model.UserSession;
import utils.PasswordService;
import view.ChangePassword;

import javax.swing.JOptionPane;


/**
 *
 * @author Bidhya
 */
public class ChangePasswordController {
    private final ChangePassword view;
    private final UserDao userDao;

    public ChangePasswordController(ChangePassword view) {
        this.view = view;
        this.userDao = new UserDao();

        // Attach listeners
        this.view.addUpdateListener(e -> updatePassword());
        this.view.addClearListener(e -> clearFields());
    }

    public void open() {
        view.setVisible(true);
    }

    private void updatePassword() {

        String oldPassword = view.getOldPassword();
        String newPassword = view.getNewPassword();
        String confirmPassword = view.getConfirmPassword();

        // 1. Empty check
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required!");
            return;
        }

        // 2. Match check
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(view, "New password and confirm password do not match!");
            return;
        }

        // 3. Session check
        UserSession session = UserSession.getInstance();
        if (!session.isLoggedIn()) {
            JOptionPane.showMessageDialog(view, "Session expired. Please login again.");
            return;
        }

        // 4. Fetch user
        User user = userDao.getUserByEmail(session.getEmail());
        if (user == null) {
            JOptionPane.showMessageDialog(view, "User not found!");
            return;
        }

        // 5. Verify old password
        boolean verified;
        String storedPassword = user.getPassword();

        if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$")) {
            verified = PasswordService.verifyPassword(oldPassword, storedPassword);
        } else {
            verified = oldPassword.equals(storedPassword);
        }

        if (!verified) {
            JOptionPane.showMessageDialog(view, "Old password is incorrect!");
            return;
        }

        // 6. Update password
        String hashedPassword = PasswordService.hashPassword(newPassword);
        boolean updated = userDao.updatePasswordByEmail(user.getEmail(), hashedPassword);

        if (updated) {
            JOptionPane.showMessageDialog(view, "Password changed successfully!");
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Failed to change password!");
        }
    }

    private void clearFields() {
        view.clearFields();
    }
}
