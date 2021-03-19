package com.luxoft.ushych.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.ushych.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    private UserController controller;

    private User testUser;
    private List<User> userTestList;

    @BeforeEach
    void setUp() {
        controller = new UserController();
        testUser = new User("Tom", "32", false);
        userTestList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userTestList.add(new User("Tom", "32" + i, false));
        }
    }

    @Test
    void whenAddOneUser_shouldAddOneUser() {
        controller.addUser(testUser);
        assertEquals(1, controller.getUsersList().size());
        assertEquals(testUser, controller.getUsersList().get(0));
    }

    @Test
    void whenAddSeveral_shouldAddSeveral() {
        userTestList.forEach(user -> controller.addUser(user));
        assertEquals(userTestList.size(), controller.getUsersList().size());
        // (userTestList.toArray(), controller.getUsersList().toArray());
    }

}
