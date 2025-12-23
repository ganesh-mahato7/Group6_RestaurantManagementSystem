package model;

/**
 *
 * @author Bidhya
 */
public class userdata {
    private int id;
    private String username;
    private String password;
    private String email;

    private String Username;

    private String role;


    private String role;
    private String mobileNumber; 
    private String fullName;    

    
    public userdata() {
        // Default constructor
    }
    
    public userdata(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public userdata(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public userdata(String username, String email, String password, String role){
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public userdata(String username, String fullName, String email, String password, String role, String mobileNumber){
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.mobileNumber = mobileNumber;
    }

    // ID getter and setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    // Username getter and setter
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Password getter and setter
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password;
    }
    
    // Email getter and setter
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    // Role getter and setter
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    // Full name getter and setter
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    // Mobile number getter and setter
    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    
    // Deprecated - use getId() instead
    @Deprecated
    public int user_id(){
        return id;


    }

    
}


    // Deprecated - use setId() instead
    @Deprecated
    public void setUser_id(int user_id){
        this.id = user_id;
    }


