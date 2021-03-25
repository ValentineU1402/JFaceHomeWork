package com.luxoft.ushych.controllers;

import java.util.List;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.ui.SignScreen;
import com.luxoft.ushych.ui.TableList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * The ViewController is controller of main view
 *
 * @author vushych@luxoft.com
 * @see UserController
 * @see TableList
 * @see SignScreen
 */
public class ViewController {

    private UserController userController;

    private TableList tableList;
    private SignScreen signScreen;

    /**
     * Instance of this class allows to control element that located on main display
     * 
     * @param parent instances of this class are controls which are capable of containing other controls.
     */
    public ViewController(Composite parent) {
        SashForm form = new SashForm(parent, SWT.HORIZONTAL);
        form.setLayout(new FillLayout(SWT.HORIZONTAL));
        form.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
        this.tableList = new TableList(form, this);
        this.signScreen = new SignScreen(form, this);
        this.userController = new UserController(this);
    }

    /**
     * Create record in TableList and appends element to UserController
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check for checkBox element.
     * 
     * @see UserController
     * @see TableList
     * @see User
     */
    public void addUserParameters(String name, String group, boolean taskDone) {
        User user = new User(name, group, taskDone);
        userController.addUser(user);
        tableList.addUserItem(user.getName(), user.getGroup(), taskDone);
    }

    /**
     * Modified form on the view by incoming parameters
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check for checkBox element.
     * 
     * @see SignScreen
     */
    public void updateFieldsForm(String name, String group, boolean taskDone) {
        signScreen.fillForm(name, group, taskDone);
    }

    /**
     * Modified table on the view by incoming parameters. Modified List<User> in UserController
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check for checkBox element.
     * @param oldUser sample of earlier selection element.
     * 
     * @see SignScreen
     * @see TableList
     * @see User
     */
    public void updateUser(String name, String group, boolean taskDone, User oldUser) {
        User user = new User(name, group, taskDone);
        userController.updateUser(oldUser, user);
        tableList.updateSelectionRow(user.getName(), user.getGroup(), taskDone);
    }

     /**
      * Delete selection row on TableList
      *
      * @param status confirm deleting
      * @see TableList
      */
    public void deleteSelectionRowTable(boolean status) {
        tableList.deleteSelectionItem(status);
    }

    /**
     * Remove all users from List<User> in UserController
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check value.
     * @see UserController
     * @see User
     * @see TableList
     */
    public void removeUser(String name, String group, boolean taskDone) {
        userController.removeUser(new User(name, group, taskDone));
    }

    /**
     * Make panel TableList with empty cell in each row
     *
     * @see TableList
     */
    public void doEmptyTableList() {
        tableList.deleteList();
    }

    /**
     * Save current state of TableList
     * 
     * @param answer confirm for save file
     * @see UserController
     * @see User
     * @see TableList
     */
    public void saveFile(boolean answer) {
        if (answer) {
            userController.saveUserToFile();
        }
    }

    /**
     * Provide List<User> to TableList
     * 
     * @param userList contains list User
     * @see TableList
     * @see UserController
     * @see User
     * 
     */
    public void setListUsersOnTable(List<User> usersList) {
        doEmptyTableList();
        usersList.stream().forEach(
                user -> tableList.addUserItem(user.getName(), user.getGroup(), user.getTaskDone()));
    }

    /**
     * Sort user list by specified column name
     * 
     * @see TableList
     * @see UserController
     */
    public void sortTable(String columnBySort) {
        setListUsersOnTable(userController.getBySort(columnBySort));
    }
}
