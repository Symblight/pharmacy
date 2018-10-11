package com.table;

import com.datamodel.AppointmentMedicines;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class appMedTable extends AbstractTableModel {
    private int column = 3;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Группа", "Описание"
    };

    private ArrayList<String[]> ResultSets;

    public appMedTable(List<AppointmentMedicines> appointmentMedicinesList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < appointmentMedicinesList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(appointmentMedicinesList.get(i).getId()),
                        appointmentMedicinesList.get(i).getGroup(),
                        appointmentMedicinesList.get(i).getDescription(),
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
