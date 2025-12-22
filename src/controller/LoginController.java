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
import view.Registration;
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

        login.AddLoginListner(new LoginListner());
        login.AddRegisterListner(new RegisterListener());
        login.AddForgotPasswordListener(new ForgotPasswordListener());
    }

    public void open() {
        this.login.setVisible(true);
    }

    public void close() {
        this.login.dispose();
    }

    class LoginListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           try {
                String email = login.getEmailText().getText();
                String password = login.getPasswordText().getText();
                userdata userdata = new userdata(email, password);
                boolean check = logindao.login(userdata);

                if (check) {
                    JOptionPane.showMessageDialog(login, "Login successful");
                } else {
                    JOptionPane.showMessageDialog(login, "Invalid credentials");
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Registration registration = new Registration();
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            RegistrationController signUpController = new RegistrationController(registration);
=======
            RegistrationController registrationController = new RegistrationController(registration);
>>>>>>> Stashed changes
=======
            RegistrationController registrationController = new RegistrationController(registration);
>>>>>>> Stashed changes

            close();
            registrationController.open();
        }
    }

    class ForgotPasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
            String email = login.getEmailText().getText().trim();

            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(login, "Please enter your email first.");
                return;
            }

            ResetPassword resetView = new ResetPassword(email);
            resetView.setVisible(true);
=======
            view.ResetPassword resetPasswordView = new view.ResetPassword();
            resetPasswordView.setVisible(true);
            close();
>>>>>>> Stashed changes
=======
            view.ResetPassword resetPasswordView = new view.ResetPassword();
            resetPasswordView.setVisible(true);
            close();
>>>>>>> Stashed changes
        }
    }
}
