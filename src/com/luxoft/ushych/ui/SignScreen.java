package com.luxoft.ushych.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.models.User;

import org.eclipse.jface.dialogs.MessageDialog;
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

    private User currentUser;

    public SignScreen(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.NONE);
        Composite composite = new Composite(parent, SWT.BORDER);
        composite.setLayout(new FillLayout(SWT.VERTICAL));
        groupFields = new Group(composite, SWT.FILL);
        groupButtons = new Group(composite, SWT.NONE);
        viewController = controller;
        createComposite();

    }

    public void updateForm(String name, String group, boolean taskDone) {
        List<Text> listTexts = Stream.of(groupFields.getChildren()).filter(field -> field instanceof Text)
                .map(field -> (Text) field).collect(Collectors.toList());
        listTexts.get(0).setText(name);
        listTexts.get(1).setText(group);
        Stream.of(groupFields.getChildren()).filter(child -> child instanceof Button).map(btn -> (Button) btn)
                .forEach(btn -> btn.setSelection(taskDone));
        this.currentUser = new User(name, group, taskDone);
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
        } else if (title.equalsIgnoreCase("Name")) {
            textName.addListener(SWT.Verify, getFilterLatterListener());
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
                    updateOrder();
                    break;
                case ("Delete"):
                    deleteOrder();
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
        Stream.of(groupFields.getChildren()).forEach(child -> {
            if (child != null) {
                if (child instanceof Text) {
                    ((Text) child).setText("");
                }
                if (child instanceof Button) {
                    ((Button) child).setSelection(false);
                }
            }
        });
    }

    private void deleteOrder() {
        getForUpdateContentList(groupFields.getChildren());
        if (MessageDialog.openConfirm(this.getShell(), "Save", "Are you sure?")) {
            viewController.deleteSelectionRowTable(true);
        }
    }

    private void updateOrder() {

        if (MessageDialog.openConfirm(this.getShell(), "Save", "Are you sure?")) {
            List<String> listContents = getForUpdateContentList(groupFields.getChildren());
            viewController.updateUser(listContents.get(0), listContents.get(1), listContents.get(2), currentUser);
        }
    }

    private void newOrder() {
        List<String> listContents = getForUpdateContentList(groupFields.getChildren());
        viewController.addUserParameters(listContents.get(0), listContents.get(1), listContents.get(2));
    }

    private List<String> getForUpdateContentList(Control[] controls) {
        List<String> listContents = new ArrayList<>();
        Stream.of(controls).forEach(child -> {
            if (child != null) {
                if (child instanceof Text) {
                    if (checkEmptyField((Text) child)) {
                        throw new IllegalArgumentException("Empty field");
                    }
                    listContents.add(((Text) child).getText());
                    ((Text) child).setText("");

                }
                if (child instanceof Button) {
                    listContents.add(String.valueOf(((Button) child).getSelection()));
                    ((Button) child).setSelection(false);
                }
            }
        });
        return listContents;
    }

    private boolean checkEmptyField(Text text) {
        boolean result = false;
        if (text.getText().isBlank() || text.getText().isEmpty()) {
            MessageDialog.openInformation(this.getShell(), "Info", "Empty field!");
            result = true;
        }
        return result;
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

    private Listener getFilterLatterListener() {
        return new Listener() {
            @Override
            public void handleEvent(Event e) {
                String string = e.text;
                if (string.matches(".*[(\\d*)]")) {
                    e.doit = false;
                    return;
                }
            }

        };
    }

}
