package com.luxoft.ushych.controllers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.luxoft.ushych.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserControllerTest {

    private UserController controller;

    private User expectedUser;
    private List<User> expectedUserList;

    private Comparator<User> nameCompare;
    private Comparator<User> groupCompare;
    private Comparator<User> taskDoneCompare;

    private final String ARRAY_EQUALS_STRING = "Different array item";

    @BeforeEach
    void setUp() {
        controller = new UserController();
        expectedUser = new User("Tom", 32, false);
        expectedUserList = createExpectedUserList();
        nameCompare = getNameComparator();
        groupCompare = getGroupComparator();
        taskDoneCompare = getTaskDoneComparator();
    }

    @Test
    void whenAddOneUser_shouldAddOneUser() {
        controller.addUser(expectedUser);
        assertEquals(expectedUser, controller.getUsersList().get(0), "Different element");
    }

    @Test
    void whenAddSeveral_shouldAddSeveral() {
        expectedUserList.forEach(user -> controller.addUser(user));
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenUpdateAllUsers_shouldUpdateAllUsers() {
        User userTest = new User("Test", 1, false);
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getUsersList().stream().forEach(user -> controller.updateUser(user, userTest));
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenRemoveUsers_shouldUserRemove() {
        expectedUserList.forEach(user -> controller.addUser(user));
        expectedUserList.stream().limit(2).forEach(user -> controller.removeUser(user));
        assertEquals(expectedUserList.size() - 2, controller.getUsersList().size(), "Diferent array size");
    }

    @Test
    void whenSortUserByName_shouldUserSort() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("Name");
        Collections.sort(expectedUserList, nameCompare);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByNameTwice_shouldUserSortReverse() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("Name");
        controller.getBySort("Name");
        Collections.sort(expectedUserList, nameCompare);
        Collections.reverse(expectedUserList);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByGroup_shouldUserSort() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("Group");
        Collections.sort(expectedUserList, groupCompare);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByGroupTwice_shouldUserSortReverse() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("Group");
        controller.getBySort("Group");
        Collections.sort(expectedUserList, groupCompare);
        Collections.reverse(expectedUserList);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByTaskDone_shouldUserSort() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("SWT done");
        Collections.sort(expectedUserList, taskDoneCompare);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByTaskDoneTwise_shouldUserSortReverse() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("SWT done");
        controller.getBySort("SWT done");
        Collections.sort(expectedUserList, taskDoneCompare);
        Collections.reverse(expectedUserList);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    private List<User> createExpectedUserList() {
        List<User> result = new ArrayList<>();
        result.add(new User("Valentyn", 1, true));
        result.add(new User("Andry", 2, false));
        result.add(new User("Tom", 3, true));
        result.add(new User("Eliza", 2, true));
        result.add(new User("Mary", 1, false));
        result.add(new User("Karl", 1, true));
        result.add(new User("Stive", 3, false));
        result.add(new User("Sem", 2, true));
        result.add(new User("Eugeniy", 1, false));
        result.add(new User("Dima", 3, false));
        result.add(new User("Ronn", 2, true));

        return result;

    }

    private Comparator<User> getNameComparator() {
        return Comparator.comparing(User::getName);
    }

    private Comparator<User> getGroupComparator() {
        return Comparator.comparing(User::getGroup);
    }

    private Comparator<User> getTaskDoneComparator() {
        return Comparator.comparing(User::getTaskDone);
    }

}
