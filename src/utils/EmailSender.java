package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Email sender utility for sending OTP and password reset emails
 * This class wraps EmailService for backward compatibility with existing code
 */
public class EmailSender {
    
    /**
     * Sends an email using the configured SMTP settings
     * @param toEmail Recipient email address
     * @param subject Email subject
     * @param messageBody Email message body
     * @return true if email was sent successfully, false otherwise
     */
    public static boolean sendEmail(String toEmail, String subject, String messageBody) {
        // Use the existing EmailService
        return EmailService.sendOTPEmail(toEmail, messageBody);
    }
}
