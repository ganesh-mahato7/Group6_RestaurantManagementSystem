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

    public User() {
        this.role = ROLE_WAITER;
        this.status = STATUS_PENDING;
    }

    public User(int id, String fullName, String email, String password, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);
        setStatus(status);
    }

    public User(int id, String fullName, String email, String role, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        setRole(role);
        setStatus(status);
    }

    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role);

        // 🔐 SCRUM MASTER auto-approved
        if (ROLE_SCRUM_MASTER.equals(this.role)) {
            this.status = STATUS_APPROVED;
        } else {
            this.status = STATUS_PENDING;
        }
    }

    // ================== GETTERS & SETTERS ==================

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getStatus() { return status; }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            this.role = ROLE_WAITER;
        } else {
            this.role = role.trim().toUpperCase();
        }
    }

    public void setStatus(String status) {
        // 🔐 SCRUM MASTER always approved
        if (ROLE_SCRUM_MASTER.equals(this.role)) {
            this.status = STATUS_APPROVED;
        } else if (status == null || status.trim().isEmpty()) {
            this.status = STATUS_PENDING;
        } else {
            this.status = status.trim();
        }
    }

    // ================== ROLE CHECKS ==================

    public boolean isSuperAdmin() {
        return ROLE_SCRUM_MASTER.equals(role);
    }

    public boolean isAdmin() {
        return isSuperAdmin() || ROLE_ADMIN.equals(role);
    }

    public boolean isStaff() {
        return ROLE_STAFF.equals(role);
    }

    public boolean isWaiter() {
        return ROLE_WAITER.equals(role);
    }

    // ================== STATUS CHECKS ==================

    public boolean isApproved() {
        return isSuperAdmin() || STATUS_APPROVED.equalsIgnoreCase(status);
    }

    public boolean isPending() {
        return !isApproved();
    }

    @Override
    public String toString() {
        return fullName + " (" + email + ") [" + role + "] - " + status;
    }
}
