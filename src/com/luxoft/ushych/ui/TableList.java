package com.luxoft.ushych.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.ui.resources_manager.MyManagerResource;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableList extends Composite {
    private ViewController viewController;

    private TableViewer tableViewer;

    private MyManagerResource resourceManager;

    private final int FIRST_COLUMN_WEIGHT = 300;
    private final int SECOND_COLUMN_WEIGHT = FIRST_COLUMN_WEIGHT / 2;
    private final int THIRD_COLUMN_WEIGHT = SECOND_COLUMN_WEIGHT / 2;

    public TableList(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.VERTICAL);
        this.resourceManager = new MyManagerResource();
        this.viewController = controller;
        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
        tableViewer.getTable().addSelectionListener(getSelectionAdapter());

        Map<String, Integer> titlesColumn = new LinkedHashMap<>();
        titlesColumn.put("Name", FIRST_COLUMN_WEIGHT);
        titlesColumn.put("Group", SECOND_COLUMN_WEIGHT);
        titlesColumn.put("SWT done", THIRD_COLUMN_WEIGHT);

        titlesColumn.entrySet().forEach(entry -> createColumn(entry.getKey(), entry.getValue()));
        tableViewer.getTable().setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, true));
        // StructuredSelection structuredSelection = new StructuredSelection(controller.getUserList());
        // tableViewer.setSelection(structuredSelection, true);
        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setHeaderBackground(new Color(new RGB(160, 160, 160)));

    }

    public void addUserItem(String name, String group, String taskDone) {
        Image checkImage = resourceManager.getCheckImage(Boolean.valueOf(taskDone));
        TableItem item = new TableItem(tableViewer.getTable(), SWT.FILL);
        item.setText(0, name);
        item.setText(1, group);
        item.setImage(2, checkImage);
    }

    public void updateSelectionRow(String name, String group, String taskDone) {
        TableItem[] selection = tableViewer.getTable().getSelection();
        Image checkImage = resourceManager.getCheckImage(Boolean.valueOf(taskDone));
        for (TableItem item : selection) {
            item.setText(0, name);
            item.setText(1, group);
            item.setImage(2, checkImage);
        }
    }

    public void deleteSelectionItem(boolean status) {
        TableItem[] selection = tableViewer.getTable().getSelection();
        for (TableItem item : selection) {
            viewController.removeUser(item.getText(0), item.getText(1), resourceManager.checkStatus(item.getImage(2)));
            item.dispose();
        }
    }

    public void deleteList() {
        tableViewer.getTable().removeAll();
    }

    private void createColumn(String name, int weight) {
        TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.CENTER);
        column.getColumn().setWidth(weight);
        column.getColumn().setText(name);
        column.setLabelProvider(new ColumnLabelProvider());
    }

    private SelectionAdapter getSelectionAdapter() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] selection = ((Table) e.getSource()).getSelection();
                for (TableItem item : selection) {
                    viewController.updateFieldsForm(item.getText(0), item.getText(1),
                            resourceManager.checkStatus(item.getImage(2)));

                }
            }
        };
    }
}
