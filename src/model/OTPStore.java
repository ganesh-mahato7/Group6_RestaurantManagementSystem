/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class OTPStore {

    public static int currentOTP;
    public static String email;

    private static final Map<String, OtpEntry> otpMap = new HashMap<>();
    private static final Random random = new Random();

    private static class OtpEntry {
        final String code;
        final Instant expiresAt;

        OtpEntry(String code, Instant expiresAt) {
            this.code = code;
            this.expiresAt = expiresAt;
        }
    }

    /**
     * Generates a 6-digit OTP, stores it for the given email, and records the expiration time.
     * Also updates legacy fields currentOTP and email for backward compatibility.
     */
    public static String createAndStoreOTP(String email, Instant expiresAt) {
        String otp = String.format("%06d", random.nextInt(900000) + 100000);
        otpMap.put(email, new OtpEntry(otp, expiresAt));
        currentOTP = Integer.parseInt(otp);
        OTPStore.email = email;
        System.out.println("Generated OTP for " + email + ": " + otp + " (expires " + expiresAt + ")");
        return otp;
    }

    // Verify OTP with expiry check
    public static boolean verifyOTP(String email, String enteredOtp) {
        OtpEntry entry = otpMap.get(email);
        if (entry == null) {
            return false;
        }
        boolean expired = Instant.now().isAfter(entry.expiresAt);
        if (expired) {
            otpMap.remove(email);
            return false;
        }
        boolean valid = entry.code.equals(enteredOtp);
        if (valid) {
            otpMap.remove(email);
        }
        return valid;
    }

    // Remove OTP once used or cancelled
    public static void clearOTP(String email) {
        otpMap.remove(email);
    }
}
