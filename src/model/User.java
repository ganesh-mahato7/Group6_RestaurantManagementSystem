package model;

public class User {

    // ================== ROLE CONSTANTS ==================
    public static final String ROLE_SCRUM_MASTER = "SCRUM MASTER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_STAFF = "STAFF";
    public static final String ROLE_WAITER = "WAITER";

    // ================== STATUS CONSTANTS ==================
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_APPROVED = "Approved";

    // ================== CORE FIELDS ==================
    private int id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String status;

    // ================== CONSTRUCTORS ==================

    // Default constructor
    public User() {
        this.role = ROLE_WAITER;
        this.status = STATUS_PENDING;
    }

    // Full constructor (login / DB fetch)
    public User(int id, String fullName, String email, String password, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);
        setStatus(status);
    }

    // Constructor for Verify Users screen (no password)
    public User(int id, String fullName, String email, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        setRole(role);
        setStatus(status);
    }

    // Constructor for registration
    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);
        this.status = STATUS_PENDING; // default on signup
    }

    // ================== GETTERS & SETTERS ==================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * ⚠ Password should always be hashed before setting
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ================== ROLE ==================

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            this.role = ROLE_WAITER;
        } else {
            this.role = role.trim().toUpperCase();
        }
    }

    // ================== STATUS ==================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            this.status = STATUS_PENDING;
        } else {
            this.status = status.trim();
        }
    }

    // ================== ROLE CHECKS ==================

    public boolean isAdmin() {
        return ROLE_SCRUM_MASTER.equals(role) || ROLE_ADMIN.equals(role);
    }

    public boolean isStaff() {
        return ROLE_STAFF.equals(role);
    }

    public boolean isWaiter() {
        return ROLE_WAITER.equals(role);
    }

    // ================== STATUS CHECKS ==================

    public boolean isApproved() {
        return STATUS_APPROVED.equalsIgnoreCase(status);
    }

    public boolean isPending() {
        return STATUS_PENDING.equalsIgnoreCase(status);
    }

    // ================== DEBUG ==================

    @Override
    public String toString() {
        return fullName + " (" + email + ") [" + role + "] - " + status;
    }
}
