package com.luxoft.ushych.controllers;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.ushych.models.User;

public class UserController {
    private List<User> usersList;
    
    public UserController() {
        usersList = new ArrayList<>();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
