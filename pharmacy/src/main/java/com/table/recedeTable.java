package com.table;

import com.datamodel.Recede;
import com.datamodel.WayApply;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class recedeTable extends AbstractTableModel {
    private int column = 8;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "тип накладной", "дата прихода", "Склад", "Поставщик", "Препарат", "Количество", "Цена"
    };

    private ArrayList<String[]> ResultSets;

    public recedeTable(List<Recede> recedeList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < recedeList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(recedeList.get(i).getId()),
                        recedeList.get(i).getTypeInvoice(),
                        String.valueOf(recedeList.get(i).getDateComing()),
                        recedeList.get(i).getPlaceStorage().getPlaceStorage(),
                        recedeList.get(i).getSupplier().getTitle(),
                        recedeList.get(i).getPreparation().getTitle(),
                        String.valueOf(recedeList.get(i).getCount()),
                        String.valueOf(recedeList.get(i).getCost())
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
