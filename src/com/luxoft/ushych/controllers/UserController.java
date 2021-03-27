package com.luxoft.ushych.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.luxoft.ushych.models.User;

/**
 * The UserController is controller of item`s users
 *
 * @author vushych@luxoft.com
 */
public class UserController {

    @SuppressWarnings("unused")
    private ViewController controller;

    private List<User> usersList;
    /**
     * Instance without parameters that create empty List<User>
     *
     * @see User
     */
    public UserController() {
        usersList = new ArrayList<>();
    }

    /**
     * Instance of this class allows realize control quantity saved users. It allows to control view values on TableList
     *
     * @param controller ViewController is controller element on the display
     * @see ViewController
     * @see TableList
     * @see User
     */
    public UserController(ViewController controller) {
        usersList = backUpSavedFileOrEmptyList();
        this.controller = controller;
        if (!usersList.isEmpty()) {
            controller.setListUsersOnTable(usersList);
        }
    }

    /**
     * Save list to file from current state of list
     */
    public void saveUserToFile() {
        try {
            File file = new File("save.ser");
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(usersList);
            objectOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Appends the specified element to the end of this list
     * 
     * @param user specified element
     */
    public void addUser(User user) {
        usersList.add(user);
    }

    /**
     * Update earlier append element
     * 
     * @param oldUser sample of earlier append element
     * @param newUser sample of update element
     */
    public void updateUser(User oldUser, User newUser) {
        usersList.stream().filter(user -> oldUser.equals(user)).forEach(user -> {
            user.setName(newUser.getName());
            user.setGroup(newUser.getGroup());
            user.setTaskDone(newUser.getTaskDone());
        });
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present (optional operation). If
     * this list does not contain the element, it is unchanged.
     */
    public void removeUser(User user) {
        usersList.remove(user);
    }

    /**
     * Sort user list by specified column name
     * 
     * @return sorted list by specified name
     * @see TableList
     */
    public List<User> getBySort(String nameColumnTable) {
        if (nameColumnTable.equals("Name")) {
            sort(Comparator.comparing(User::getName));
        } else if (nameColumnTable.equals("Group")) {
            sort(Comparator.comparing(User::getGroup));
        } else if (nameColumnTable.equals("SWT done")) {
            sort(Comparator.comparing(User::getTaskDone));
        }
        return usersList;
    }

    private void sort(Comparator<User> comparator) {
        if (isSortedByComparator(comparator)) {
            Collections.reverse(usersList);
        } else {
            Collections.sort(usersList, comparator);
        }
    }

    /**
     * Return current user list
     * 
     * @return all added users in the list
     */
    public List<User> getUsersList() {
        return usersList;
    }

    @SuppressWarnings("unchecked")
    private ArrayList<User> backUpSavedFileOrEmptyList() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("save.ser"))) {
            return (ArrayList<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    private boolean isSortedByComparator(Comparator<User> comparator) {
        List<User> checkList = new ArrayList<>();
        checkList.addAll(usersList);
        Collections.sort(checkList, comparator);
        return usersList.equals(checkList);
    }
}
