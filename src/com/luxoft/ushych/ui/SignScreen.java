package com.luxoft.ushych.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.luxoft.ushych.controllers.ViewController;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class SignScreen extends Composite {

    private ViewController viewController;

    private Group groupFields;
    private Group groupButtons;

    public SignScreen(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.NONE);
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        // composite.setLayout(new GridLayout(1, true));
        // composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
        groupFields = new Group(composite, SWT.FILL);
        groupButtons = new Group(composite, SWT.NONE);
        viewController = controller;
        createComposite();
    }

    private void createComposite() {
        Map<String, Integer> fields = new LinkedHashMap<>();
        fields.put("Name", SWT.NONE);
        fields.put("Group", SWT.NONE);
        fields.put("SWT task done", SWT.CHECK);
        createGroupField(fields);
        String[] btnTitles = { "New", "Save", "Delete", "Cansel" };
        createGroupButtons(btnTitles);
    }

    private void createGroupField(Map<String, Integer> fieldParameters) {

        fieldParameters.entrySet().stream().forEach(entry -> {
            createLabel(entry.getKey());
            if (entry.getValue() != SWT.CHECK) {
                createTextField(entry.getKey());
            } else {
                new Button(groupFields, SWT.CHECK)
                        .setLayoutData(new GridData(GridData.END, GridData.FILL, false, false));
            }
        });
        // groupFields.setLayout(new FillLayout(SWT.VERTICAL));
        groupFields.setLayout(new GridLayout(2, false));
        groupFields.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
    }

    private void createLabel(String text) {
        Label labelName = new Label(groupFields, SWT.CENTER);
        labelName.setText(text);
        labelName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    }

    private void createTextField(String title) {
        Text textName = new Text(groupFields, SWT.BORDER);
        textName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        if (title.equalsIgnoreCase("Group")) {
            textName.addListener(SWT.Verify, getFilterDigitListener());
        }
    }

    private void createGroupButtons(String[] titles) {
        groupButtons.setLayout(new FillLayout(SWT.HORIZONTAL));
        Stream.of(titles).forEach(str -> createButton(str));
        groupButtons.pack();
    }

    private Button createButton(String name) {
        Button btn = new Button(groupButtons, SWT.PUSH);
        btn.setText(name);
        btn.setLayoutData(new GridData(GridData.END, GridData.END, true, false));
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                switch (name) {
                case ("New"):
                    newOrder();
                    break;
                case ("Save"):
                    saveOrder();
                    break;
                case ("Edit"):
                    editOrder();
                    break;
                case ("Cansel"):
                    canselOrder();
                    break;
                default:
                    break;
                }
            }

        });

        return btn;
    }

    private void canselOrder() {

    }

    private void editOrder() {
        // TODO Auto-generated method stub

    }

    private void saveOrder() {
        // TODO Auto-generated method stub

    }

    private void newOrder() {
        Control[] controls = groupFields.getChildren();
        List<String> listContents = new ArrayList<>();
        for (int i = 0; i < controls.length; i++) {
            if (controls[i] != null) {
                if (controls[i] instanceof Text) {
                    listContents.add(((Text) controls[i]).getText());
                    ((Text) controls[i]).setText("");

                }
                if (controls[i] instanceof Button) {
                    listContents.add(String.valueOf(((Button) controls[i]).getSelection()));
                    ((Button) controls[i]).setText("");
                }
            }
        }
        viewController.addUserParameters(listContents.get(0), listContents.get(1), listContents.get(2));
    }

    private void doEmptyField() {

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

}
