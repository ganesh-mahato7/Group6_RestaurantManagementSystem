package model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class OTPStore {
    public static String email;
    public static int currentOTP;
    public static Instant expiresAt;
    private static final Map<String, String> otpMap = new HashMap<>();

    public static String createAndStoreOTP(String userEmail, Instant expiryTime) {
        email = userEmail;
        currentOTP = (int) (Math.random() * 900000) + 100000; // 6-digit OTP
        expiresAt = expiryTime;
        otpMap.put(userEmail, String.valueOf(currentOTP));
        return String.valueOf(currentOTP);
    }

    public static boolean verifyOTP(String userEmail, String otpInput) {
        if (!otpMap.containsKey(userEmail)) return false;
        boolean valid = otpMap.get(userEmail).equals(otpInput) && Instant.now().isBefore(expiresAt);
        if (valid) clearOTP(userEmail);
        return valid;
    }

    public static void clearOTP(String userEmail) {
        otpMap.remove(userEmail);
        if (email.equals(userEmail)) {
            email = null;
            currentOTP = 0;
            expiresAt = null;
        }
    }
}
