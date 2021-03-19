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

    public void updateUser(User oldUser, User newUser) {
        usersList.stream().filter(user -> oldUser.equals(user)).forEach(user -> {
            user.setName(newUser.getName());
            user.setGroup(newUser.getGroup());
            user.setGroup(newUser.getGroup());
        });
    }

    public void removeUser(User user) {
        usersList.remove(user);
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
