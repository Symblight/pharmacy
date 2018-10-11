package com.table;

import com.datamodel.ModeRelease;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModeReleaseTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Форма выпуска"
    };

    private ArrayList<String[]> ResultSets;

    public ModeReleaseTable(List<ModeRelease> modeReleaseList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < modeReleaseList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(modeReleaseList.get(i).getId()),
                        modeReleaseList.get(i).getTitle(),
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
