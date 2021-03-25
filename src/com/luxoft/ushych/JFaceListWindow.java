package com.luxoft.ushych;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.ui.MenuBar;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class JFaceListWindow extends ApplicationWindow {

    private ViewController viewController;

    public JFaceListWindow() {
        super(null);
    }

    @Override
    protected Control createContents(Composite parent) {
        getShell().setMenuBar(new MenuBar(getShell(), new ViewController(parent)).getMenuBar());
        getShell().setText("JFace homework log");
        getShell().setSize(700, 500);

        getShell().pack();
        return parent;
    }

    public static void main(String[] args) {
        JFaceListWindow listWindow = new JFaceListWindow();
        listWindow.setBlockOnOpen(true);
        listWindow.open();
        Display.getCurrent().dispose();
    }

}
