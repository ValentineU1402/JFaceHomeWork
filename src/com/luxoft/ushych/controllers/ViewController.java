package com.luxoft.ushych.controllers;

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

    public SignScreen getSignScreen() {
        return signScreen;
    }

    public TableList getTableList() {
        return table;
    }

    public void addUserParameters(String name, int group, boolean taskDone) {
        User user = new User(name, group, taskDone);
        userController.addUser(user);
        table.addUserItem(user);
    }
}
