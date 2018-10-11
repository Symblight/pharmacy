package com.table;

import com.datamodel.Producer;
import com.datamodel.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class producerTable extends AbstractTableModel {
    private int column = 6;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Название", "Адрес", "Дата основания", "телефон", "Почта"
    };

    private ArrayList<String[]> ResultSets;

    public producerTable(List<Producer> producerList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < producerList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(producerList.get(i).getId()),
                        producerList.get(i).getTitle(),
                        producerList.get(i).getAddress(),
                        String.valueOf(producerList.get(i).getDateCreate()),
                        producerList.get(i).getNumberPhone(),
                        producerList.get(i).getEmail(),
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
