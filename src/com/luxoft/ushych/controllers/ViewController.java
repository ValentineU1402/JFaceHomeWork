package com.luxoft.ushych.controllers;

import java.util.List;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.ui.SignScreen;
import com.luxoft.ushych.ui.TableList;
import com.luxoft.ushych.ui.resources_manager.MyManagerResource;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;

public class ViewController {

    private MyManagerResource resourceManager;
    private UserController userController;

    private TableList table;
    private SignScreen signScreen;

    public ViewController(SashForm parent) {
        this.userController = new UserController();
        this.table = new TableList(parent, this);
        this.signScreen = new SignScreen(parent, this);
        this.resourceManager = new MyManagerResource();
    }

    public SignScreen getSignScreen() {
        return signScreen;
    }

    public TableList getTableList() {
        return table;
    }

    public void addUserParameters(String name, String group, String taskDone) {
        User user = new User(name, group, Boolean.valueOf(taskDone.trim()));
        // JFaceResources.
        // Bundle bundle = Platform
        // URL url = FileLocator.find(bundle,
        // new Path(Icons.CHECK_IN.getPath()),
        // null);
        // ImageDescriptor descriptor = ImageDescriptor.createFromFile(this.getClass(), name);
        // Image checkImage = resourceManager.createImage(descriptor);

        Image checkImage = resourceManager.getCheckImage(user.getTaskDone());
        // User user = new User(name, group, taskDone);
        userController.addUser(user);

        table.addUserItem(user.getName(), String.valueOf(user.getGroup()), checkImage);
    }

    public List<User> getUserList() {
        return userController.getUsersList();
    }
}
