package com.luxoft.ushych;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.ui.MenuBar;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
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
        SashForm mainSash = new SashForm(parent, SWT.FILL);
        viewController = new ViewController(mainSash);
        mainSash.setLayout(new FillLayout());
        parent.pack();
        getShell().setMenuBar(new MenuBar(getShell()).getMenuBar());
        getShell().setText("JFace homework log");
        getShell().setSize(800, 400);
        getShell().setLayout(new GridLayout(1, false));
        return parent;
    }

    public static void main(String[] args) {
        JFaceListWindow listWindow = new JFaceListWindow();
        listWindow.setBlockOnOpen(true);
        listWindow.open();
        Display.getCurrent().dispose();

    }

}
