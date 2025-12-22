/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;

/**
 *
 * @author Bidhya
 */
public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String position; 
    private double salary;
    private Date hireDate;
    private Date birthDate;
    private String gender;
    private String status; 
    private int userId; 
    public Staff() {}
    
    public Staff(String firstName, String lastName, String email, String phone, 
                String position, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.status = "Active";
    }
}
