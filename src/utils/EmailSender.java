package utils;

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
