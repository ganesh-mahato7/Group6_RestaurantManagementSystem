package model;

/**
 *
 * @author ACER
 */
public class userdata {
    private int id;
    private String username;
    private String password;
    private String email;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    private String Username;
=======
    private String role;
>>>>>>> Stashed changes
=======
    private String role;
>>>>>>> Stashed changes
=======
    private String role;
>>>>>>> Stashed changes
    
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
    
    // Deprecated - use getId() instead
    @Deprecated
    public int user_id(){
        return id;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    }
<<<<<<< Updated upstream
    public void setUsername(String Username){
        this.Username = Username;
    }
    public String getUsername(){
        return Username;
    }
}

=======
=======
    }
>>>>>>> Stashed changes
=======
    }
>>>>>>> Stashed changes
    
    // Deprecated - use setId() instead
    @Deprecated
    public void setUser_id(int user_id){
        this.id = user_id;
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
}
>>>>>>> Stashed changes
=======
}
>>>>>>> Stashed changes
=======
}
>>>>>>> Stashed changes
