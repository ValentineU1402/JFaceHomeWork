package com.luxoft.ushych.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import com.luxoft.ushych.controllers.ViewController;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableList extends Composite {

    private final int FIRST_COLUMN_WEIGHT = 300;
    private final int SECOND_COLUMN_WEIGHT = FIRST_COLUMN_WEIGHT / 2;
    private final int THIRD_COLUMN_WEIGHT = SECOND_COLUMN_WEIGHT / 2;

    private ViewController viewController;

    private TableViewer tableViewer;

    public TableList(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.VERTICAL);
        this.viewController = controller;
        tableViewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                IStructuredSelection selection = event.getStructuredSelection();
                System.out.println(selection);
                // RowData rowData = (RowData) selection.getFirstElement();
                // System.out.println(rowData);
            }
        });

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

    public void addUserItem(String name, String group, Image image) {
        TableItem item = new TableItem(tableViewer.getTable(), SWT.FILL);
        item.setText(0, name);
        item.setText(1, group);
        item.setImage(2, image);

    }

    private void createColumn(String name, int weight) {
        TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.CENTER);
        column.getColumn().setWidth(weight);
        column.getColumn().setText(name);
        column.setLabelProvider(new ColumnLabelProvider());
    }


}
