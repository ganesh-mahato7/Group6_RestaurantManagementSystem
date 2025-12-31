package controller;

import dao.UserDao;
import model.User;
import model.UserSession;
import utils.PasswordService;
import view.Dashboard;
import view.Login;
import view.ResetPassword;
import view.SignUpForm;

import javax.swing.JOptionPane;

public class LoginController {

    private final Login loginView;
    private final UserDao userDao;

    public LoginController(Login loginView) {
        this.loginView = loginView;
        this.userDao = new UserDao();

        // ✅ Attach ALL listeners here
        this.loginView.addLoginListener(e -> authenticateUser());
        this.loginView.addRegisterListener(e -> openSignUpForm());
        this.loginView.addForgotPasswordListener(e -> openResetPassword());
    }

    // Optional helper
    public void open() {
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }

    // ----------------- Navigation -----------------

    private void openSignUpForm() {
        SignUpForm signUpForm = new SignUpForm();
        new SignUpController(signUpForm).open();
        loginView.dispose();
    }

    private void openResetPassword() {
        String email = loginView.getEmailInput();
        ResetPassword resetForm = new ResetPassword(email);
        resetForm.setLocationRelativeTo(null);
        resetForm.setVisible(true);
        loginView.dispose();
    }

    // ----------------- Authentication -----------------

   private void authenticateUser() {
    try {
        String email = loginView.getEmailInput();
        String password = new String(loginView.getPasswordInput());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(loginView, "Please enter email and password!");
            return;
        }

        if (!userDao.existsByEmail(email)) {
            JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
            return;
        }

        User user = userDao.getUserByEmail(email);
        if (user == null) {
            JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
            return;
        }

        // 🔒 ✅ APPROVAL CHECK (THIS IS THE FIX)
        if (!"APPROVED".equalsIgnoreCase(user.getStatus())) {
            JOptionPane.showMessageDialog(
                    loginView,
                    "Your account is not approved yet.\nPlease wait for admin approval."
            );
            return;
        }

        // 🔐 Password verification
        boolean verified;
        String storedPassword = user.getPassword();

        if (storedPassword.startsWith("$2")) {
            verified = PasswordService.verifyPassword(password, storedPassword);
        } else {
            verified = password.equals(storedPassword);
        }

        if (!verified) {
            JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
            return;
        }

        // ✅ Login success
        UserSession.getInstance().setUser(user);

        Dashboard dashboard = new Dashboard();
        switch (user.getRole().toUpperCase()) {
            case "SCRUM MASTER":
                dashboard.setScrumMasterAccess();
                break;
            case "STAFF":
                dashboard.setStaffAccess();
                break;
            case "WAITER":
            default:
                dashboard.setWaiterAccess();
                break;
        }

        dashboard.setLocationRelativeTo(null);
        dashboard.setVisible(true);
        loginView.dispose();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(loginView, "Login error: " + ex.getMessage());
    }
}
}