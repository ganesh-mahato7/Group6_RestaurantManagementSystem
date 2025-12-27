package controller;

import dao.PasswordResetDao;
import dao.UserDao;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import utils.EmailService;
import view.ResetPassword;

/**
 * Controller for orchestrating password recovery flow.
 * Views (ResetPassword, Verification, NewPassword) handle their own UI events and navigation.
 */
public class PasswordRecoveryController {

    private final UserDao userDao = new UserDao();
    private final PasswordResetDao resetDao = new PasswordResetDao();
    private final ResetPassword resetPasswordView;

    public PasswordRecoveryController(ResetPassword resetPasswordView) {
        this.resetPasswordView = resetPasswordView;
    }

    /** Open the reset password view */
    public void open() {
        resetPasswordView.setVisible(true);
    }

    /**
     * Validate if email exists and initiate OTP flow.
     * @param email The email to send OTP to
     * @return true if OTP created and email sent successfully
     */
    public boolean initiatePasswordReset(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        try {
            if (!userDao.existsByEmail(email)) {
                return false;
            }

            // Generate OTP
            String otp = generateOtp();
            Instant expiresAt = Instant.now().plus(10, ChronoUnit.MINUTES);

            // Create token in database and send OTP email
            boolean tokenCreated = resetDao.createToken(email, otp, expiresAt);
            boolean emailSent = EmailService.sendOTPEmail(email, otp);

            return tokenCreated && emailSent;

        } catch (Exception e) {
            System.err.println("Error initiating password reset: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /** Generate a 6-digit OTP code */
    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(code);
    }
}
