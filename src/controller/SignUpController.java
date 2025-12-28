package controller;

import dao.UserDao;
import model.User;
import utils.PasswordService;
import view.SignUpForm;

import javax.swing.JOptionPane;

public class SignUpController {
    private final SignUpForm signUpForm;
    private final UserDao userDao;

    public SignUpController(SignUpForm signUpForm) {
        this.signUpForm = signUpForm;
        this.userDao = new UserDao();

        // Attach listener to register button
        this.signUpForm.AddRegistrationListner(e -> registerUser());

        // Optional: attach login navigation listener
        this.signUpForm.AddLoginNavigationListener(e -> openLogin());
    }

    private void registerUser() {
        try {
            String fullName = signUpForm.getFullNameInput();
            String email = signUpForm.getEmailInput();
            String password = signUpForm.getPasswordInput();
            String confirmPassword = signUpForm.getConfirmPasswordInput();
            String role = signUpForm.getSelectedRole();

            if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(signUpForm, "All fields are required!");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(signUpForm, "Passwords do not match!");
                return;
            }

            if (userDao.existsByEmail(email)) {
                JOptionPane.showMessageDialog(signUpForm, "Email already registered!");
                return;
            }

            // Hash password before saving
            String hashedPassword = PasswordService.hashPassword(password);

            // ✅ Use updated User constructor (without phone/address)
            User user = new User(
                    0,               // id (auto-generated)
                    fullName,
                    email,
                    hashedPassword,  // password
                    role,
                    "Pending"        // default status
            );

            if (userDao.registerUser(user)) {
                JOptionPane.showMessageDialog(signUpForm, "Registration successful!");
                signUpForm.dispose(); // close signup form
            } else {
                JOptionPane.showMessageDialog(signUpForm, "Registration failed!");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(signUpForm, "Error: " + ex.getMessage());
        }
    }

    private void openLogin() {
        signUpForm.dispose(); // close signup
        // Optionally open Login form
        // new LoginController(new Login()).open();
    }

    public void open() {
        signUpForm.setVisible(true);
    }
}
