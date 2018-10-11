package com.table;

import com.datamodel.Education;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EducationTable extends AbstractTableModel {
    private int column = 2;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Образование"
    };

    private ArrayList<String[]> ResultSets;

    public EducationTable(List<Education> educationList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < educationList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(educationList.get(i).getId()),
                        educationList.get(i).getModeEducation(),
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
