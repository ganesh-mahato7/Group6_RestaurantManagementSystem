package controller;

import dao.PasswordResetDao;
import dao.userDao;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import utils.EmailService;
import view.ResetPassword;

/**
 * Controller for orchestrating password recovery flow.
 * NOTE: Views (ResetPassword, Verification, NewPassword) handle their own UI events and navigation.
 * This controller is provided for alternative flow control if needed.
 */
public class PasswordRecoveryController {
    private final userDao userDao = new userDao();
    private final PasswordResetDao resetDao = new PasswordResetDao();

    private final ResetPassword resetPasswordView;

    public PasswordRecoveryController(ResetPassword resetPasswordView) {
        this.resetPasswordView = resetPasswordView;
    }

    public void open() {
        resetPasswordView.setVisible(true);
    }

    /**
     * Validate if email exists and initiate OTP flow.
     * This is a helper method for programmatic use; views handle their own events.
     */
    public boolean initiatePasswordReset(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!userDao.existsByEmail(email)) {
            return false;
        }
        String otp = generateOtp();
        Instant expiresAt = Instant.now().plus(10, ChronoUnit.MINUTES);
        return resetDao.createToken(email, otp, expiresAt) && 
               EmailService.sendOTPEmail(email, otp);
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int code = 100000 + random.nextInt(900000); // 6 digits
        return String.valueOf(code);
    }
}
