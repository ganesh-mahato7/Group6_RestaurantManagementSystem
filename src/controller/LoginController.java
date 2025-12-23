/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.loginDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.userdata;
import view.Login;
import view.SignUpForm;
import view.ResetPassword;


/**
 *
 * @author ACER
 */
public class LoginController {
    private final loginDao logindao = new loginDao();
    private final Login login;

    public LoginController(Login login) {
        this.login = login;

        login.AddLoginListner(new LoginListener());
        login.AddRegisterListner(new RegisterListener());
        login.AddForgotPasswordListener(new ForgotPasswordListener());
    }

    public void open() {
        this.login.setVisible(true);
    }

    public void close() {
        this.login.dispose();
    }

    // Listener for login button
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = login.getEmailText().getText().trim();
                String password = new String(login.getPasswordText().getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(login, "Please enter email and password.");
                    return;
                }

                userdata user = new userdata(email, password);
                boolean check = logindao.login(user);

                if (check) {
                    JOptionPane.showMessageDialog(login, "Login successful");
                    // You can redirect to dashboard here
                } else {
                    JOptionPane.showMessageDialog(login, "Invalid credentials");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(login, "An error occurred: " + ex.getMessage());
            }
        }
    }

    // Listener for register button
    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            SignUpForm registration = new SignUpForm();
            RegistrationController signUpController = new RegistrationController(registration);

            RegistrationController registrationController = new RegistrationController(registration);



            SignUpForm registrationView = new SignUpForm();

            Registration registrationView = new Registration();

            RegistrationController registrationController = new RegistrationController(registrationView);
            close();
            registrationController.open();
        }
    }

    // Listener for forgot password
    class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = login.getEmailText().getText().trim();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(login, "Please enter your email first.");
                return;
            }

            ResetPassword resetPasswordView = new ResetPassword(email);
            resetPasswordView.setVisible(true);
            close();
        }
    }
}