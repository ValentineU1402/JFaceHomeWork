package com.luxoft.ushych.ui;

import com.luxoft.ushych.controllers.ViewController;
import com.luxoft.ushych.models.User;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableList extends Composite implements IStructuredContentProvider {

    private ViewController controller;

    private TableViewer viewer;

    public TableList(SashForm parent, ViewController controller) {
        super(parent.getParent(), SWT.BORDER);
        this.controller = controller;
        viewer = new TableViewer(parent, SWT.BORDER | SWT.V_SCROLL);
        // viewer.setContentProvider(ArrayContentProvider.getInstance());
        // viewer.setInput(getElements(viewer));
        String[] titlesColumn = { "Name", "Group", "SWT done" };

        //
        // Stream.of(titlesColumn)
        // .forEach(title -> new TableViewerColumn(viewer, SWT.NONE).getColumn().setText(title));
        TableViewerColumn first = new TableViewerColumn(viewer, SWT.NONE);
        first.getColumn().setWidth(200);
        first.getColumn().setText("Name");
        first.setLabelProvider(new ColumnLabelProvider());
        TableViewerColumn second = new TableViewerColumn(viewer, SWT.NONE);
        second.getColumn().setWidth(100);
        second.getColumn().setText("Group");
        second.setLabelProvider(new ColumnLabelProvider());
        TableViewerColumn three = new TableViewerColumn(viewer, SWT.NONE);
        three.getColumn().setWidth(90);
        three.getColumn().setText("SWT done");
        three.setLabelProvider(new ColumnLabelProvider());

        Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setHeaderBackground(new Color(new RGB(160, 160, 160)));

    }

    private void create(String str) {
        new TableColumn(viewer.getTable(), SWT.BORDER | SWT.FULL_SELECTION).setText(str);
    }

    public void addUserItem(User user) {
        TableItem item = new TableItem(viewer.getTable(), SWT.BORDER);
        item.setText(0, user.getName());
        item.setText(1, String.valueOf(user.getGroup()));
        item.setText(2, String.valueOf(user.getTaskDone()));

    }

    @Override
    public Object[] getElements(Object inputElement) {
        String[] titles = { "s", "w", "a" };
        return titles;
    }

}
