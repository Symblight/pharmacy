package com.table;

import com.datamodel.PlaceStorage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class placeStorageTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Место хранения"
    };

    private ArrayList<String[]> ResultSets;

    public placeStorageTable(List<PlaceStorage> placeStorageList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < placeStorageList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(placeStorageList.get(i).getId()),
                        placeStorageList.get(i).getPlaceStorage(),
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
