package com.table;

import com.datamodel.Supplier;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class supplierTable extends AbstractTableModel {
    private int column = 7;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Название", "Представитель", "Должность", "Адресс", "Номер телефона", "Почта"
    };

    private ArrayList<String[]> ResultSets;

    public supplierTable(List<Supplier> supplierList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < supplierList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(supplierList.get(i).getId()),
                        supplierList.get(i).getTitle(),
                        supplierList.get(i).getAgent(),
                        supplierList.get(i).getPosition(),
                        supplierList.get(i).getAddress(),
                        supplierList.get(i).getNumberPhone(),
                        supplierList.get(i).getEmail(),
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
