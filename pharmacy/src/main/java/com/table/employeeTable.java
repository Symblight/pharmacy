package com.table;

import com.datamodel.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class employeeTable extends AbstractTableModel {
    private int column = 10;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Фамилия", "Имя", "Отчество", "Адрес", "Номер телефона", "Образование", "Должность", "Начало работы", "Зарплата"
    };

    private ArrayList<String[]> ResultSets;

    public employeeTable(List<Employee> employeeList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < employeeList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(employeeList.get(i).getId()),
                        employeeList.get(i).getLastName(),
                        employeeList.get(i).getFirstName(),
                        employeeList.get(i).getMiddleName(),
                        employeeList.get(i).getAddress(),
                        employeeList.get(i).getNumberPhone(),
                        employeeList.get(i).getEducation().getModeEducation(),
                        employeeList.get(i).getPosition().getPosition(),
                        String.valueOf(employeeList.get(i).getStartWork()),
                        String.valueOf(employeeList.get(i).getSalary())
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
