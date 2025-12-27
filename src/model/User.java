package model;

public class User {

    private String fullName;
    private String email;
    private String password;
    private String role;

    // Required for frameworks / tools
    public User() {}

    public User(String fullName, String email, String password, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        setRole(role); // normalize role
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

    // ⚠ Keep getter only if absolutely needed
    public String getPassword() {
        return password;
    }

    // Only DAO / service should set password
    public void setPassword(String password) {
        this.password = password;
    }

    // Normalize role (IMPORTANT)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null) {
            this.role = "WAITER";
        } else {
            this.role = role.trim().toUpperCase();
        }
    }

    // 🔒 Convenience methods (BEST PRACTICE)
    public boolean isAdmin() {
        return "SCRUM MASTER".equals(role) || "ADMIN".equals(role);
    }

    public boolean isStaff() {
        return "STAFF".equals(role);
    }

    public boolean isWaiter() {
        return "WAITER".equals(role);
    }
}
