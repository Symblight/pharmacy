package com.table;

import com.datamodel.Preparation;
import com.datamodel.Unit;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class preparationTable extends AbstractTableModel {
    private int column = 12;
    private int rownum;

    private String[] columnNames
            = {
            "ID", "Название", "Изготовитель", "Назначение", "Единица измерения", "Место хранения", "Дата выпуска", "Срок годности", "Стоимость", "Рецепт", "Количество", "Способ применения"
    };

    private ArrayList<String[]> ResultSets;

    public preparationTable(List<Preparation> preparationList) {
        ResultSets = new ArrayList();
        try {
            for (int i = 0; i < preparationList.size(); i++) {
                String[] row
                        = {
                        String.valueOf(preparationList.get(i).getId()),
                        preparationList.get(i).getTitle(),
                        preparationList.get(i).getSupplier().getTitle(),
                        preparationList.get(i).getAppointmentMedicines().getGroup(),
                        preparationList.get(i).getUnit().getTitle(),
                        preparationList.get(i).getPlaceStorage().getPlaceStorage(),
                        String.valueOf(preparationList.get(i).getReleaseDate()),
                        String.valueOf(preparationList.get(i).getShelfLife()),
                        String.valueOf(preparationList.get(i).getCost()),
                        preparationList.get(i).getModeRelease().getTitle(),
                        String.valueOf(preparationList.get(i).getCount()),
                        preparationList.get(i).getWayApply().getTitle()
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
