package model;

public class User {

    private String email;
    private String password;
    private String phone;
    private String address;

    // For admin login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // For order now
    public User(String email, String phone, String address) {
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}
