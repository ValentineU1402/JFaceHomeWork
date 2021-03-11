package com.luxoft.ushych.view_models;

import java.util.stream.Stream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableList {

    Table table;

    public TableList(Composite parent) {
        table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION);
        table.setHeaderVisible(true);
        table.setHeaderBackground(new Color(new RGB(160, 160, 160)));
        String[] titlesColumn = { "Name", "Group", "SWT done" };

        Stream.of(titlesColumn).forEach(title -> new TableColumn(table, SWT.NULL).setText(title));

        for (int loopIndex = 0; loopIndex < 20; loopIndex++) {
            TableItem item = new TableItem(table, SWT.BORDER);
            item.setText(2, "No");
        }
        
        Stream.of(table.getColumns()).forEach(TableColumn::pack);
    }

    public Table getTable() {
        return table;
    }
}
