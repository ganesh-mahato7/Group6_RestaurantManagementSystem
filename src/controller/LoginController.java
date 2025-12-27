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

        // Attach Sign Up navigation listener
        this.loginView.addRegisterListener(e -> openSignUpForm());

        // Attach Reset Password listener
        this.loginView.addForgotPasswordListener(e -> openResetPassword());
    }

    public void open() {
        loginView.setVisible(true);
        loginView.addLoginListener(e -> authenticateUser());
    }

    // Open Sign Up form
    private void openSignUpForm() {
        SignUpForm signUpForm = new SignUpForm();
        SignUpController signUpController = new SignUpController(signUpForm);
        signUpController.open();
        loginView.dispose();
    }

    // Open Reset Password form
    private void openResetPassword() {
        String email = loginView.getEmailInput(); // optional: prefill email
        ResetPassword resetForm = new ResetPassword(email);
        resetForm.setVisible(true);
        loginView.dispose(); // close login window
    }

    // Authenticate user and open Dashboard on success
    private void authenticateUser() {
        try {
            String email = loginView.getEmailInput();
            String password = new String(loginView.getPasswordInput());

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Please enter both email and password!");
                return;
            }

            // Check if user exists
            if (!userDao.existsByEmail(email)) {
                JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
                return;
            }

            // Fetch user data
            User user = userDao.getUserByEmail(email);
            if (user == null) {
                JOptionPane.showMessageDialog(loginView, "User not found!");
                return;
            }

            // Verify password
            String storedPassword = user.getPassword();
            boolean verified;
            if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$")) {
                verified = PasswordService.verifyPassword(password, storedPassword);
            } else {
                verified = password.equals(storedPassword);
            }

            if (verified) {
                JOptionPane.showMessageDialog(loginView, "Login successful!");

                // ===== Set session =====
                UserSession session = UserSession.getInstance();
                session.setUser(user);

                // ===== Open Dashboard with role-based access =====
                Dashboard dashboard = new Dashboard();

                switch (session.getRole().toUpperCase()) {
                    case "SCRUM MASTER":
                        dashboard.setScrumMasterAccess();
                        break;
                    case "STAFF":
                        dashboard.setStaffAccess();
                        break;
                    case "WAITER":
                        dashboard.setWaiterAccess();
                        break;
                    default:
                        // Default to minimal access if role unknown
                        dashboard.setWaiterAccess();
                        break;
                }

                dashboard.setVisible(true);

                // Close login window
                loginView.dispose();

            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid credentials!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginView, "Error: " + ex.getMessage());
        }
    }
}
