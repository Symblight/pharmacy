package com.table;

import com.datamodel.Pharmacy;
import com.datamodel.WayApply;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class pharmacyTable extends AbstractTableModel {
    private int column = 5;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Название", "Адрес", "Номер телефона", "Лицензия"
    };

    private ArrayList<String[]> ResultSets;

    public pharmacyTable(List<Pharmacy> pharmacyList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < pharmacyList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(pharmacyList.get(i).getId()),
                        pharmacyList.get(i).getTitle(),
                        pharmacyList.get(i).getAddress(),
                        pharmacyList.get(i).getNumberPhone(),
                        pharmacyList.get(i).getLicense()
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
