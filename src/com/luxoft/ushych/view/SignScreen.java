package com.luxoft.ushych.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SignScreen {

    private Composite composite;


    public SignScreen(Composite parent) {
        composite = new Composite(parent, SWT.RESIZE);
        createComposite();
    }

    private void createComposite() {
        composite.setLayout(new GridLayout(1, true));
        GridData gridDate = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
        createNameField().setLayoutData(gridDate);
        createGroupField().setLayoutData(gridDate);
        createCheckField().setLayoutData(gridDate);
        createButtonField().setLayoutData(gridDate);
    }

    private Group createNameField() {
        Group nameField = new Group(composite, SWT.NONE);
        nameField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(nameField, SWT.NONE);
        labelName.setText("Name");
        labelName.setLayoutData(getCenterGridDate());
        Text textName = new Text(nameField, SWT.BORDER);
        textName.setLayoutData(getEndGridDate());
        return nameField;
    }

    private Group createGroupField() {
        Group groupField = new Group(composite, SWT.NONE);
        groupField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(groupField, SWT.NONE);
        labelName.setText("Group");
        labelName.setLayoutData(getCenterGridDate());
        Text textName = new Text(groupField, SWT.BORDER);
        textName.setLayoutData(getEndGridDate());
        return groupField;
    }

    private Group createCheckField() {
        Group checkField = new Group(composite, SWT.NONE);
        checkField.setLayout(new GridLayout(2, true));
        Label labelName = new Label(checkField, SWT.NONE);
        labelName.setText("SWT task done");
        labelName.setLayoutData(getCenterGridDate());
        Button button = new Button(checkField, SWT.CHECK);
        checkField.setLayoutData(getEndGridDate());
        return checkField;
    }

    private Group createButtonField() {
        Group buttonField = new Group(composite, SWT.NONE);
        buttonField.setLayout(new FillLayout());
        Button newButton = new Button(buttonField, SWT.PUSH);
        newButton.setText("New");
        Button saveButton = new Button(buttonField, SWT.PUSH);
        saveButton.setText("Save");
        Button deleteButton = new Button(buttonField, SWT.PUSH);
        deleteButton.setText("Delete");
        Button cancelButton = new Button(buttonField, SWT.PUSH);
        cancelButton.setText("Cancel");

        return buttonField;
    }

    private GridData getCenterGridDate() {
        return new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_FILL);
    }

    private GridData getEndGridDate() {
        return new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
    }

    public Composite getComposite() {
        return composite;
    }
}
