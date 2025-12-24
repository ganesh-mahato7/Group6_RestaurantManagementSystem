package utils;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static boolean sendEmail(String toEmail, String subject, String body) {

        final String fromEmail = "ganeshmahato1412@gmail.com"; 
        final String appPassword = "kxgdotlszodxhrhd"; // Gmail App Password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, appPassword);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg);
            System.out.println("Email Sent Successfully!");
            return true;

        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

/**
 * Email sender utility for sending OTP and password reset emails
 * This class wraps EmailService for backward compatibility with existing code
 */
public class EmailSender {
    
    /**
     * Sends an OTP email using the configured SMTP settings
     * @param toEmail Recipient email address
     * @param otpCode The OTP code to send
     * @return true if email was sent successfully, false otherwise
     */
    public static boolean sendOTPEmail(String toEmail, String otpCode) {
        return EmailService.sendOTPEmail(toEmail, otpCode);
    }
    
    /**
     * Sends an email using the configured SMTP settings
     * @param toEmail Recipient email address
     * @param subject Email subject
     * @param messageBody Email message body
     * @return true if email was sent successfully, false otherwise
     */
    public static boolean sendEmail(String toEmail, String subject, String messageBody) {
        // For generic email, we'll use OTP email structure
        return EmailService.sendOTPEmail(toEmail, messageBody);

    }
}
