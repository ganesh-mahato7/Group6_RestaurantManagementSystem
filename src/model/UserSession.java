package model;

/**
 * Singleton class to store the current logged-in user session
 */
public class UserSession {

    private static UserSession instance;

    private String fullName;
    private String email;
    private String role;

    // Private constructor to prevent external instantiation
    private UserSession() {}

    /**
     * Get the singleton instance of UserSession
     */
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Initialize session from a User object
     */
    public void setUser(User user) {
        if (user == null) return;

        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.role = user.getRole();

        System.out.println("=== UserSession initialized ===");
        System.out.println("Full Name: " + fullName);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
    }

    /**
     * Clear session data (used internally and on logout)
     */
    public void clearSession() {
        this.fullName = null;
        this.email = null;
        this.role = null;
    }

    // ------------------ Getters ------------------
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    // ------------------ Session checks ------------------
    public boolean isLoggedIn() {
        return email != null && !email.isEmpty();
    }

    public boolean isAdmin() {
        return "SCRUM MASTER".equalsIgnoreCase(role) || "ADMIN".equalsIgnoreCase(role);
    }

    public boolean isStaff() {
        return "STAFF".equalsIgnoreCase(role);
    }

    public boolean isWaiter() {
        return "WAITER".equalsIgnoreCase(role);
    }

    /**
     * Log out the current user and clear the session
     */
    public void logout() {
        if (isLoggedIn()) {
            System.out.println("=== Logging out user: " + email + " ===");
        }
        clearSession();
    }
}
