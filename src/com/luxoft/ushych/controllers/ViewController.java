package com.luxoft.ushych.controllers;

import com.luxoft.ushych.models.User;
import com.luxoft.ushych.view_models.MenuBar;
import com.luxoft.ushych.view_models.SignScreen;
import com.luxoft.ushych.view_models.TableList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

public class ViewController {

    private UserController userController;
    private static ViewController viewController;

    private Display display;
    private Shell shell;

    private ViewController() {
        display = new Display();
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        userController = new UserController();
        shell.setText("JFace homework log");
        shell.setSize(800, 400);
        shell.setLayout(new FillLayout());
    }

    public static ViewController getInstance() {
        if (viewController == null) {
            viewController = new ViewController();
        }
        return viewController;
    }

    public void show() {
        Menu menuBar = new MenuBar(shell).getMenuBar();
        SashForm mainSash = new SashForm(shell, SWT.BORDER | SWT.HORIZONTAL);
        mainSash.setLayout(new FillLayout());
        Table table = new TableList(mainSash).getTable();
        Composite signScreen = new SignScreen(mainSash).getComposite();
        mainSash.setWeights(new int[] { 1, 1 });
        shell.setMenuBar(menuBar);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    public void addUser(User user) {
        userController.addUser(user);
    }

}
