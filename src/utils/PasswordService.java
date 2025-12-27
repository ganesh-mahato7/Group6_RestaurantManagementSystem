package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {

    // Hash a plain text password
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Verify a plain password against a hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
