/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class userdata {
    private int user_id;
    private String password;
    private String email;
    private String Username;
    
    public userdata( String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setUser_id (int user_id){
        this.user_id = user_id;
    }
    public int user_id(){
        return user_id;
    }
    public void setUsername(String Username){
        this.Username = Username;
    }
    public String getUsername(){
        return Username;
    }
}

