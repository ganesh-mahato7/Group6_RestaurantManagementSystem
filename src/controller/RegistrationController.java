/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.RegistrationDao;
import javax.swing.JOptionPane;
import model.userdata;
import utils.PasswordService;
import view.Login;
import view.Registration;

/**
 * Controller for handling user registration
 */
public class RegistrationController {
    private final RegistrationDao userDao = new RegistrationDao();
    private final Registration registrationView;

    public RegistrationController(Registration registrationView) {
        this.registrationView = registrationView;
    }

    public void open() {
        registrationView.setVisible(true);
    }

    /**
     * Validate and register a new user.
     * Called by the view when the registration button is clicked.
     */
    public boolean registerUser(String fullName, String email, String password, String confirmPassword, String mobileNumber) {
        // Validation
        if (fullName == null || fullName.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            mobileNumber == null || mobileNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(registrationView, 
                "Please fill in all required fields.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(registrationView, 
                "Please enter a valid email address.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(registrationView, 
                "Password must be at least 6 characters long.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(registrationView, 
                "Passwords do not match.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if user already exists
        userdata tempUser = new userdata(fullName, email, password, mobileNumber);
        if (userDao.checkUser(tempUser)) {
            JOptionPane.showMessageDialog(registrationView, 
                "An account with this email or mobile number already exists.", 
                "Registration Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Hash the password before storing
        String hashedPassword = PasswordService.hashPassword(password);

        // Create user object
        userdata newUser = new userdata(fullName, email, hashedPassword, mobileNumber);

        // Save user
        userDao.signUp(newUser);

        JOptionPane.showMessageDialog(registrationView, 
            "Registration successful! You can now log in.", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

        // Open login view
        Login loginView = new Login();
        LoginController loginController = new LoginController(loginView);
        registrationView.dispose();
        loginController.open();

        return true;
    }
}
