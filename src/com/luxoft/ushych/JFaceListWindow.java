package com.luxoft.ushych;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.ui.MenuBar;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
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
        SashForm form = new SashForm(parent, SWT.HORIZONTAL);
        viewController = new ViewController(form);
        form.setLayout(new FillLayout(SWT.HORIZONTAL));
        form.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));

        getShell().setMenuBar(new MenuBar(getShell(), viewController).getMenuBar());
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
