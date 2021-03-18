package com.luxoft.ushych.controllers;

import java.util.List;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.ui.SignScreen;
import com.luxoft.ushych.ui.TableList;

import org.eclipse.swt.custom.SashForm;

public class ViewController {

    private UserController userController;

    private TableList table;
    private SignScreen signScreen;

    public ViewController(SashForm parent) {
        this.userController = new UserController();
        this.table = new TableList(parent, this);
        this.signScreen = new SignScreen(parent, this);
    }

    public void updateFieldsForm(String name, String group, boolean taskDone) {
        signScreen.updateForm(name, group, taskDone);
    }

    public SignScreen getSignScreen() {
        return signScreen;
    }

    public TableList getTableList() {
        return table;
    }

    public void addUserParameters(String name, String group, String taskDone) {
        User user = new User(name, group, Boolean.valueOf(taskDone.trim()));
        userController.addUser(user);
        table.addUserItem(user.getName(), String.valueOf(user.getGroup()), taskDone);
    }

    public List<User> getUserList() {
        return userController.getUsersList();
    }
}
