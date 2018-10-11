package com.table;

import com.datamodel.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class orderTable extends AbstractTableModel {
    private int column = 4;
    private int rownum;

    private String[] columnNames
            = {
            "Наименование препарата", "ед измерения", "количество", "стоимость"
    };

    private ArrayList<String[]> ResultSets;

    public orderTable(List<Order> orderList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < orderList.size(); i++) {
                String[] row
                        = {
                        orderList.get(i).getPreparation().getTitle(),
                        orderList.get(i).getUnit(),
                        String.valueOf(orderList.get(i).getCount()),
                        String.valueOf(orderList.get(i).getCost()),
                     //   String.valueOf(orderList.get(i).getResult()),
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
