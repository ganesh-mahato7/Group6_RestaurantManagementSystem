package model;

public class User {

    // Core fields
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String status;

    // Status constants
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_APPROVED = "Approved";

    // Default constructor
    public User() {}

    // Constructor for login/registration
    public User(int id, String fullName, String email, String password, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);
        this.status = status;
    }

    // Constructor for VerifyUsers screen (without password)
    public User(int id, String fullName, String email, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        setRole(role);
        this.status = status;
    }

    // Constructor for registration (without id and status)
    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = STATUS_PENDING;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) {
        if (role == null) {
            this.role = "WAITER";
        } else {
            this.role = role.trim().toUpperCase();
        }
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Convenience methods for roles
    public boolean isAdmin() { return "SCRUM MASTER".equals(role) || "ADMIN".equals(role); }
    public boolean isStaff() { return "STAFF".equals(role); }
    public boolean isWaiter() { return "WAITER".equals(role); }

    @Override
    public String toString() {
        return fullName + " (" + email + ") - " + status;
    }
}
