package com.luxoft.ushych.controllers;

import java.util.List;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.services.GroupComparator;
import com.luxoft.ushych.services.NameComparator;
import com.luxoft.ushych.services.TaskDoneComparator;
import com.luxoft.ushych.ui.SignScreen;
import com.luxoft.ushych.ui.TableList;

import org.eclipse.swt.custom.SashForm;

public class ViewController {

    private UserController userController;

    private TableList table;
    private SignScreen signScreen;

    public ViewController(SashForm parent) {
        this.table = new TableList(parent, this);
        this.signScreen = new SignScreen(parent, this);
        this.userController = new UserController(this);
    }

    public void addUserParameters(String name, String group, String taskDone) {
        User user = new User(name, group, Boolean.valueOf(taskDone.trim()));
        userController.addUser(user);
        table.addUserItem(user.getName(), String.valueOf(user.getGroup()), taskDone);
    }

    public void updateFieldsForm(String name, String group, boolean taskDone) {
        signScreen.updateForm(name, group, taskDone);
    }

    public void updateUser(String name, String group, String taskDone, User oldUser) {
        User user = new User(name, group, Boolean.valueOf(taskDone.trim()));
        userController.updateUser(oldUser, user);
        table.updateSelectionRow(user.getName(), String.valueOf(user.getGroup()), taskDone);
    }

    public void deleteSelectionRowTable(boolean status) {
        userController.removeUser(null);
        table.deleteSelectionItem(status);
    }

    public void removeUser(String name, String group, boolean taskDone) {
        userController.removeUser(new User(name, group, taskDone));
    }

    public void doEmptyTableList() {
        table.deleteList();
    }

    public void saveFile(boolean answer) {
        if (answer) {
            userController.saveUserToFile();
        }
    }

    public SignScreen getSignScreen() {
        return signScreen;
    }

    public TableList getTableList() {
        return table;
    }

    public List<User> getUserList() {
        return userController.getUsersList();
    }

    public void setListUsersOnTable(List<User> usersList) {
        doEmptyTableList();
        usersList.stream().forEach(
                user -> table.addUserItem(user.getName(), user.getGroup(), Boolean.toString(user.getTaskDone())));
    }

    public void sortTable(String columnBySort) {
        switch (columnBySort) {
        case ("Name"):
            setListUsersOnTable(userController.getBySort(new NameComparator()));
            break;
        case ("Group"):
            setListUsersOnTable(userController.getBySort(new GroupComparator()));
            break;
        case ("SWT done"):
            setListUsersOnTable(userController.getBySort(new TaskDoneComparator()));
            break;
        default:
            break;
        }

    }
}
