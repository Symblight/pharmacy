package com.table;

import com.datamodel.WayApply;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class WayApplyTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Способ применения"
    };

    private ArrayList<String[]> ResultSets;

    public WayApplyTable(List<WayApply> wayApplyList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < wayApplyList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(wayApplyList.get(i).getId()),
                        wayApplyList.get(i).getTitle(),
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
