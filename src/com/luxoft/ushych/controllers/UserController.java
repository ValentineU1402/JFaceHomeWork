package com.luxoft.ushych.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.services.GroupComparator;
import com.luxoft.ushych.services.NameComparator;
import com.luxoft.ushych.services.TaskDoneComparator;

public class UserController {

    private ViewController viewController;

    private List<User> usersList;

    private boolean sort;

    private boolean sortByName;
    private boolean sortByGroup;
    private boolean sortByTaskDone;

    public UserController() {
        usersList = new ArrayList<>();
    }

    public UserController(ViewController viewController) {
        usersList = backUpSavedFileOrEmptyList();
        this.viewController = viewController;
        if (!usersList.isEmpty()) {
            viewController.setListUsersOnTable(usersList);
        }
        this.sort = false;

        sortByName = false;
        sortByGroup = false;
        sortByTaskDone = false;

    }

    public void saveUserToFile() {
        try {
            File file = new File("save.ser");
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(usersList);
            objectOutputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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



    public List<User> getBySort(String nameColumnTable) {
        if (nameColumnTable.equals("Name")) {
            if (sortByName) {
                Collections.reverse(usersList);
                sortByName = false;
            } else {
                sortByName = true;
                usersList.sort(new NameComparator());
            }
        } else if (nameColumnTable.equals("Group")) {
            if (sortByGroup) {
                Collections.reverse(usersList);
                sortByGroup = false;
            } else {
                sortByGroup = true;
                usersList.sort(new GroupComparator());
            }
        } else if (nameColumnTable.equals("SWT done")) {
            if (sortByTaskDone) {
                Collections.reverse(usersList);
                sortByTaskDone = false;
            } else {
                sortByTaskDone = true;
                usersList.sort(new TaskDoneComparator());
            }
        }

        return usersList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    // private void sort(Comparator<User> comparator, Boolean checkSort) {
    // if (checkSort) {
    // checkSort = false;
    // Collections.reverse(usersList);
    // } else {
    // checkSort = true;
    // usersList.sort(comparator);
    // }
    // }

    private ArrayList<User> backUpSavedFileOrEmptyList() {
        try {
            FileInputStream fileInputStream;

            fileInputStream = new FileInputStream("save.ser");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            return (ArrayList<User>) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
