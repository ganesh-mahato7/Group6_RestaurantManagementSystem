<<<<<<< Updated upstream
<<<<<<< Updated upstream
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.RegistrationDao;
=======
package controller;

import dao.userDao;
>>>>>>> Stashed changes
=======
package controller;

import dao.userDao;
>>>>>>> Stashed changes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.userdata;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import view.Registration;
import view.Login;

/**
 *
 * @author ACER
 */
public class RegistrationController {
    private final RegistrationDao userdao = new RegistrationDao();
    private final Registration userView;
    
    public RegistrationController(Registration userView) {
        this.userView =  userView;

        userView.AddConfirmListener(new ConfirmActionListener());

        userView.AddLoginListener(new SignInActionListener());

        userView.AddCancelListener(e -> close());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class ConfirmActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ex) {
            try {
                String mobileNumber = userView.getMobileNumber().getText();
                String email = userView.getEmail().getText();
                String fullName = userView.getFullName().getText();
                String password = userView.getassword().getText();

                userdata UserData = new userdata( email, password);

                boolean exists = userdao.checkUser(UserData);
                if (exists) {
                    JOptionPane.showMessageDialog(userView,
                            "User already exists with this email or mobile number.");
                } else {
                    userdao.signUp(UserData);
                    JOptionPane.showMessageDialog(userView,
                            "Registration successful! Please log in.");

                    Login loginView = new Login();
                    LoginController loginController = new LoginController(loginView);

                    close();             
                    loginController.open();
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(userView, "Error: " + e.getMessage());
            }
        }
    }

    class SignInActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Login loginView = new Login();
            LoginController loginController = new LoginController(loginView);

            close();
            loginController.open();
        }
    }
 
=======
=======
>>>>>>> Stashed changes
import utils.PasswordService;
import view.Login;
import view.Registration;

/** 
 * Controller for handling user registration
 */
public class RegistrationController {
    private final userDao userDao = new userDao();
    private final Registration registrationView;

    public RegistrationController(Registration registrationView) {
        this.registrationView = registrationView;
        // Note: View handles button events directly; this controller manages data and validation
    }

    public void open() {
        registrationView.setVisible(true);
    }

    /**
     * Validate and register a new user.
     * Called by the view when the registration button is clicked.
     */
    public boolean registerUser(String fullName, String email, String password, String confirmPassword, String role, boolean agreedToTerms) {
        // Validation
        if (fullName == null || fullName.trim().isEmpty() || 
            email == null || email.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
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

        if (!agreedToTerms) {
            JOptionPane.showMessageDialog(registrationView, 
                "Please agree to the Terms and Conditions.", 
                "Validation Error", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if user already exists
        userdata tempUser = new userdata(fullName, email, "");
        if (userDao.check(tempUser)) {
            JOptionPane.showMessageDialog(registrationView, 
                "An account with this email or username already exists.", 
                "Registration Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Hash the password before storing
        String hashedPassword = PasswordService.hashPassword(password);

        // Create user with hashed password
        userdata newUser = new userdata(fullName, email, hashedPassword);
        userDao.signUp(newUser);

        JOptionPane.showMessageDialog(registrationView, 
            "Registration successful! You can now log in.", 
            "Success", 
            JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
