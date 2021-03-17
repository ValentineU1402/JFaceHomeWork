package com.luxoft.ushych.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.models.User;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableList extends Composite {

    private final int FIRST_COLUMN_WEIGHT = 300;
    private final int SECOND_COLUMN_WEIGHT = FIRST_COLUMN_WEIGHT / 2;
    private final int THIRD_COLUMN_WEIGHT = SECOND_COLUMN_WEIGHT / 2;

    private ViewController controller;

    private TableViewer viewer;

    public TableList(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.VERTICAL);
        this.controller = controller;
        viewer = new TableViewer(parent, SWT.FILL);

        Map<String, Integer> titlesColumn = new LinkedHashMap<>();
        titlesColumn.put("Name", FIRST_COLUMN_WEIGHT);
        titlesColumn.put("Group", SECOND_COLUMN_WEIGHT);
        titlesColumn.put("SWT done", THIRD_COLUMN_WEIGHT);

        titlesColumn.entrySet().forEach(entry -> createColumn(entry.getKey(), entry.getValue()));
        viewer.getTable().setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, false, true));
        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setHeaderBackground(new Color(new RGB(160, 160, 160)));

    }

    public void addUserItem(User user) {
        TableItem item = new TableItem(viewer.getTable(), SWT.FILL);
        item.setText(0, user.getName());
        item.setText(1, String.valueOf(user.getGroup()));
        item.setText(2, String.valueOf(user.getTaskDone()));

    }

    private void createColumn(String name, int weight) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);
        column.getColumn().setWidth(weight);
        column.getColumn().setText(name);
        column.setLabelProvider(new ColumnLabelProvider());
    }

}
