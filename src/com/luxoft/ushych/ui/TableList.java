package com.luxoft.ushych.ui;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.ui.resources.ManagerResource;

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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * TableList is composite on application that provide control to rows on the table
 *
 * @author vushych@luxoft.com
 * @see Composite
 * @see ViewController
 * @see TableViewer
 */
public class TableList extends Composite {
    private ViewController viewController;

    private TableViewer tableViewer;

    private ManagerResource resourceManager;

    private final int FIRST_COLUMN_WEIGHT = 300;
    private final int SECOND_COLUMN_WEIGHT = 220;
    private final int THIRD_COLUMN_WEIGHT = 140;

    /**
     * View panel table part of application
     *
     * @param parent the SashForm element that may resize internal element
     * @param controller ViewController is controller element on the display
     * @see SashForm
     * @see ViewController
     */
    public TableList(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.VERTICAL);
        this.resourceManager = new ManagerResource();
        this.viewController = controller;
        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
        tableViewer.getTable().addSelectionListener(getSelectionAdapter());

        Map<String, Integer> titlesColumn = new LinkedHashMap<>();
        titlesColumn.put("Name", FIRST_COLUMN_WEIGHT);
        titlesColumn.put("Group", SECOND_COLUMN_WEIGHT);
        titlesColumn.put("SWT done", THIRD_COLUMN_WEIGHT);

        titlesColumn.entrySet().forEach(entry -> createColumn(entry.getKey(), entry.getValue()));
        tableViewer.getTable().setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, true));
        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setHeaderBackground(new Color(new RGB(160, 160, 160)));
    }

    /**
     * Add item to table for fill row of it
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check for checkBox element. This element converts to equivalent Image element.
     * 
     * @see ManagerResource
     */
    public void addUserItem(String name, String group, boolean taskDone) {
        Image checkImage = resourceManager.getCheckImage(Boolean.valueOf(taskDone));
        TableItem item = new TableItem(tableViewer.getTable(), SWT.CENTER);
        item.setText(0, name);
        item.setText(1, group);
        item.setImage(2, checkImage);
    }

    /**
     * Update item of table from edit form
     *
     * @param name value for a name field
     * @param group value for a group field
     * @param taskDone check for checkBox element. This element converts to equivalent Image element.
     * 
     * @see TableItem
     * @see ManagerResource
     */
    public void updateSelectionRow(String name, String group, boolean taskDone) {
        TableItem[] selection = tableViewer.getTable().getSelection();
        Image checkImage = resourceManager.getCheckImage(taskDone);
        for (TableItem item : selection) {
            item.setText(0, name);
            item.setText(1, group);
            item.setImage(2, checkImage);
        }
    }

    /**
     * Delete selection item
     *
     * @param status confirm deleting
     * 
     * @see TableItem
     */
    public void deleteSelectionItem(boolean status) {
        TableItem[] selection = tableViewer.getTable().getSelection();
        for (TableItem item : selection) {
            viewController.removeUser(item.getText(0), item.getText(1), resourceManager.checkStatus(item.getImage(2)));
            item.dispose();
        }
    }

    /**
     * Make panel table with empty cell in each row
     *
     */
    public void deleteList() {
        tableViewer.getTable().removeAll();
    }

    private void createColumn(String name, int weight) {
        TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.CENTER);
        column.getColumn().setWidth(weight);
        column.getColumn().setText(name);
        column.getColumn().setImage(resourceManager.getArrow(column.getColumn().getImage()));
        column.getColumn().setResizable(false);
        column.getColumn().addSelectionListener(getSelectionColumnListener());

    }

    private SelectionAdapter getSelectionColumnListener() {
        return new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Arrays.asList(tableViewer.getTable().getColumns()).stream()
                        .forEach(column -> column.setImage(resourceManager.getArrow(column.getImage())));
                tableViewer.getTable().getColumns();
                TableColumn selection = ((TableColumn) e.getSource());
                viewController.sortTable(selection.getText());
            }
        };
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
