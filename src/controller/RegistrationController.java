/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.RegistrationDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.userdata;
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
 
}
