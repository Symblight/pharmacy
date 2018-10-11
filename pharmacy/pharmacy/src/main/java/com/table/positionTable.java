package com.table;

import com.datamodel.Position;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class positionTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Должность"
    };

    private ArrayList<String[]> ResultSets;

    public positionTable(List<Position> positionList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < positionList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(positionList.get(i).getId()),
                        positionList.get(i).getPosition(),
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
