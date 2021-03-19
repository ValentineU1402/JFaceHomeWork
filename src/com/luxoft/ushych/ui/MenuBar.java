package com.luxoft.ushych.ui;

import com.luxoft.ushych.controllers.ViewController;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MenuBar {

    private ViewController controller;

    private Menu menu;

    public MenuBar(Decorations parent, ViewController controller) {
        menu = new Menu(parent, SWT.BAR);
        this.controller = controller;
        createFileMenuItem(parent);
        createEditMenuItem(parent);
        createHelpMenuItem(parent);
    }

    private void createFileMenuItem(Decorations parent) {
        MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
        fileMenuItem.setText("File");
        Menu fileMenu = new Menu(parent, SWT.DROP_DOWN);
        fileMenuItem.setMenu(fileMenu);
        MenuItem item = new MenuItem(fileMenu, SWT.PUSH);
        item.setText("New");
        item.addSelectionListener(getItemNewListener(parent));
        item = new MenuItem(fileMenu, SWT.PUSH);
        item.setText("Save");
        item.addSelectionListener(getItemSaveListener(parent));
        item = new MenuItem(fileMenu, SWT.PUSH);
        item.setText("Exit");
        item.addSelectionListener(getItemExitListener(parent));
    }

    private SelectionListener getItemNewListener(Decorations parent) {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                controller.doEmptyTableList();
            }
        };
    }

    private SelectionListener getItemSaveListener(Decorations parent) {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                boolean answer = MessageDialog.openConfirm(parent.getShell(), "Save", "Do you want to save file?");
                controller.saveFile(answer);
            }
        };
    }

    private SelectionListener getItemExitListener(Decorations parent) {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                parent.getDisplay().dispose();
            }
        };
    }

    private void createEditMenuItem(Decorations parent) {
        MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
        fileMenuItem.setText("Edit");
    }

    private void createHelpMenuItem(Decorations parent) {
        MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
        fileMenuItem.setText("Help");
    }

    public Menu getMenuBar() {
        return menu;
    }

}
