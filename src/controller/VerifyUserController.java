/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import model.User;

import java.util.ArrayList;

public class VerifyUserController {

    private final UserDao userDao = new UserDao();

    // Get all users matching search
    public ArrayList<User> getUsers(String emailSearch) {
        return userDao.getAllUsers(emailSearch);
    }

    // Update user status
    public boolean changeUserStatus(int id, String newStatus) {
        return userDao.updateUserStatus(id, newStatus);
    }
}
