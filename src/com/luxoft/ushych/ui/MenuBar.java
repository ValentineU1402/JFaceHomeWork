package com.luxoft.ushych.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MenuBar {

    private Menu menu;

    public MenuBar(Decorations parent) {
        menu = new Menu(parent, SWT.BAR);
        createFileMenuItem(parent);
        createEditMenuItem(parent);
        createHelpMenuItem(parent);
    }

    private void createFileMenuItem(Decorations parent) {
        MenuItem fileMenuItem = new MenuItem(menu, SWT.CASCADE);
        fileMenuItem.setText("File");
        Menu fileMenu = new Menu(parent, SWT.DROP_DOWN);
        fileMenuItem.setMenu(fileMenu);
        MenuItem itemNew = new MenuItem(fileMenu, SWT.PUSH);
        itemNew.setText("New");
        MenuItem itemExit = new MenuItem(fileMenu, SWT.PUSH);
        itemExit.setText("Exit");
        itemExit.addSelectionListener(getItemExitListener(parent));
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
