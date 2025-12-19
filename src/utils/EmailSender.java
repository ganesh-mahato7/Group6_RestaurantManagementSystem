/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

<<<<<<< HEAD

import jakarta.mail.Authenticator;
=======
>>>>>>> f3f5b52867c1ffad07ff19ffa8036df73640c4ca
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
<<<<<<< HEAD

=======
>>>>>>> f3f5b52867c1ffad07ff19ffa8036df73640c4ca
import java.util.Properties;

/**
 *
 * @author Asus
 */
public class EmailSender {
<<<<<<< HEAD
    public static boolean sendEmail(String toEmail, String subject, String body) throws MessagingException {

        final String fromEmail = "ganeshmahato1412@gmail.com"; 
        final String appPassword = "kxgdotlszodxhrhd"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
=======
    public static boolean sendEmail(String toEmail, String subject, String body) {
        try {
            final String fromEmail = ""; // your email
            final String appPassword = ""; // Gmail App Password ONLY (App Password)

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");   // TLS
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });
>>>>>>> f3f5b52867c1ffad07ff19ffa8036df73640c4ca

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(body);

<<<<<<< HEAD
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(fromEmail));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        msg.setSubject(subject);
        msg.setText(body);

        Transport.send(msg);
        System.out.println("Email Sent Successfully!");
        return false;
  }
}
=======
            Transport.send(msg);
            System.out.println("Email Sent Successfully!");
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
>>>>>>> f3f5b52867c1ffad07ff19ffa8036df73640c4ca
