package com.table;

import com.datamodel.Unit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class unitTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Ед измерения"
    };

    private ArrayList<String[]> ResultSets;

    public unitTable(List<Unit> unitList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < unitList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(unitList.get(i).getId()),
                        unitList.get(i).getTitle(),
                };
                ResultSets.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getRowCount() {
        return ResultSets.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = ResultSets.get(rowIndex);
        return row[columnIndex];
    }
    @Override
    public String getColumnName(int param) {
        return columnNames[param];
    }
}
