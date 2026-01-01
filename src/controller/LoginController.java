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

        // ✅ Attach listeners ONCE (IMPORTANT)
        this.loginView.addLoginListener(e -> authenticateUser());
        this.loginView.addRegisterListener(e -> openSignUpForm());
        this.loginView.addForgotPasswordListener(e -> openResetPassword());
    }

    // ================= OPEN LOGIN =================
    public void open() {
        loginView.setLocationRelativeTo(null);
        loginView.setVisible(true);
    }

    // ================= NAVIGATION =================

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

    // ================= AUTHENTICATION =================

    private void authenticateUser() {
        try {
            String email = loginView.getEmailInput().trim();
            String password = new String(loginView.getPasswordInput()).trim();

            // 1️⃣ Input validation
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Please enter email and password!");
                return;
            }

            // 2️⃣ Fetch user
            User user = userDao.getUserByEmail(email);
            if (user == null) {
                JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
                return;
            }

            // 3️⃣ Approval check (SCRUM MASTER BYPASS)
            if (!user.isAdmin() && !user.isApproved()) {
                JOptionPane.showMessageDialog(
                        loginView,
                        "Your account is not approved yet.\nPlease wait for admin approval."
                );
                return;
            }

            // 4️⃣ Password verification
            boolean verified;
            String storedPassword = user.getPassword();

            if (storedPassword != null && storedPassword.startsWith("$2")) {
                verified = PasswordService.verifyPassword(password, storedPassword);
            } else {
                verified = password.equals(storedPassword);
            }

            if (!verified) {
                JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
                return;
            }

            // 5️⃣ Set session
            UserSession.getInstance().setUser(user);

            // 6️⃣ Open dashboard with role access
            Dashboard dashboard = new Dashboard();

            switch (user.getRole()) {
                case User.ROLE_SCRUM_MASTER:
                    dashboard.setScrumMasterAccess();
                    break;
                case User.ROLE_STAFF:
                    dashboard.setStaffAccess();
                    break;
                case User.ROLE_WAITER:
                default:
                    dashboard.setWaiterAccess();
                    break;
            }

            dashboard.setLocationRelativeTo(null);
            dashboard.setVisible(true);
            loginView.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    loginView,
                    "Login failed. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
