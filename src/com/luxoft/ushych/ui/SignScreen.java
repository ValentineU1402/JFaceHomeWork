package com.luxoft.ushych.ui;

import com.luxoft.ushych.controllers.ViewController;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class SignScreen extends Composite {

    private ViewController viewController;

    private Text textName;
    private Text textGroup;
    private Button buttonCheck;

    private Button newButton;
    private Button saveButton;
    private Button deleteButton;
    private Button cancelButton;

    public SignScreen(SashForm parent, ViewController controller) {
        super(parent, SWT.RESIZE);
        viewController = controller;
        createComposite();
    }

    private void createComposite() {
        this.setLayout(new FillLayout(SWT.VERTICAL | SWT.FILL));
        createNameField();
        createGroupField();
        createCheckField();
        createButtonField();
    }

    private Group createNameField() {
        Group nameField = new Group(this, SWT.NONE);
        nameField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(nameField, SWT.NONE);
        labelName.setText("Name");
        labelName.setLayoutData(getCenterGridDate());
        textName = new Text(nameField, SWT.LEFT | SWT.BORDER);
        textName.setLayoutData(getEndGridDate());
        return nameField;
    }

    private Group createGroupField() {
        Group groupField = new Group(this, SWT.NONE);
        groupField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(groupField, SWT.NONE);
        labelName.setText("Group");
        labelName.setLayoutData(getCenterGridDate());
        textGroup = new Text(groupField, SWT.LEFT | SWT.BORDER);
        textGroup.addListener(SWT.Verify, getFilterDigitListener());
        textGroup.setLayoutData(getEndGridDate());
        return groupField;
    }

    private Group createCheckField() {
        Group checkField = new Group(this, SWT.NONE);
        checkField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(checkField, SWT.NONE);
        labelName.setText("SWT task done");
        labelName.setLayoutData(getCenterGridDate());
        buttonCheck = new Button(checkField, SWT.CHECK);
        buttonCheck.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
        return checkField;
    }

    private Group createButtonField() {
        Group buttonField = new Group(this, SWT.NONE);
        buttonField.setLayout(new FillLayout());
        newButton = createButtonNew(buttonField);
        saveButton = createButtonSave(buttonField);
        deleteButton = new Button(buttonField, SWT.PUSH);
        deleteButton.setText("Delete");
        cancelButton = createButtonCancel(buttonField);

        return buttonField;
    }

    private Button createButtonNew(Composite parent) {
        Button result = new Button(parent, SWT.PUSH);
        result.setText("New");
        result.addSelectionListener(getSelectionListenerForCancel());
        return result;
    }

    private Button createButtonSave(Composite parent) {
        Button result = new Button(parent, SWT.PUSH);
        result.setText("Save");
        result.addSelectionListener(getSelectionListenerForSave());
        return result;
    }

    private Button createButtonCancel(Composite parent) {
        Button result = new Button(parent, SWT.PUSH);
        result.setText("Cancel");
        result.addSelectionListener(getSelectionListenerForCancel());
        return result;
    }

    private SelectionListener getSelectionListenerForSave() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                viewController.addUserParameters(textName.getText(), Integer.parseInt(textGroup.getText()),
                        buttonCheck.getSelection());
            }

        };
    }

    private SelectionAdapter getSelectionListenerForCancel() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                textName.setText("");
                textGroup.setText("");
                buttonCheck.setSelection(false);
            }

        };
    }

    private Listener getFilterDigitListener() {
        return new Listener() {
            @Override
            public void handleEvent(Event e) {
                String string = e.text;
                if (string.matches(".*[^(\\d*)]")) {
                    e.doit = false;
                    return;
                }
            }

        };
    }

    private GridData getCenterGridDate() {
        return new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_FILL);
    }

    private GridData getEndGridDate() {
        return new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
    }
}
