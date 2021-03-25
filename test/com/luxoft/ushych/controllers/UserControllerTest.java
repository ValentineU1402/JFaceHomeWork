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
        expectedUser = new User("Tom", "32", false);
        expectedUserList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedUserList.add(new User("Tom" + i, "32" + i, false));
        }
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
        User userTest = new User("Test", "1", false);
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
    void whenSortUserByGroup_shouldUserSort() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("Group");
        Collections.sort(expectedUserList, groupCompare);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    @Test
    void whenSortUserByTaskDone_shouldUserSort() {
        expectedUserList.forEach(user -> controller.addUser(user));
        controller.getBySort("SWT done");
        Collections.sort(expectedUserList, taskDoneCompare);
        assertArrayEquals(ARRAY_EQUALS_STRING, expectedUserList.toArray(), controller.getUsersList().toArray());
    }

    private Comparator<User> getNameComparator() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        };
    }

    private Comparator<User> getGroupComparator() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int firstGroup = Integer.parseInt(o1.getGroup());
                int secondGroup = Integer.parseInt(o2.getGroup());
                return firstGroup > secondGroup ? 1 : firstGroup == secondGroup ? 0 : -1;
            }
        };
    }

    private Comparator<User> getTaskDoneComparator() {
        return new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int i = o1.getTaskDone() == true && o2.getTaskDone() == false ? -1
                        : o1.getTaskDone() == true && o2.getTaskDone() == true ? 0
                                : o1.getTaskDone() == false && o2.getTaskDone() == false ? 0 : 1;
                if (i == 0) {
                    i = o1.getName().compareToIgnoreCase(o2.getName());
                }
                return i;

            }
        };
    }

}
