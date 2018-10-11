package com;

import com.dataaccess.*;
import com.datamodel.*;
import com.table.preparationTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PreparationForm extends JFrame {
    private List<Preparation> preparationList;
    private List<Supplier> supplierList;
    private List<AppointmentMedicines> appointmentMedicinesList;
    private List<Unit> unitList;
    private List<PlaceStorage> placeStorageList;
    private List<ModeRelease> modeReleaseList;
    private List<WayApply> wayApplyList;
    private int selectedId;
    private int selectedRow;

    public PreparationForm() {
        super("Препараты");

        this.getContentPane().add(rootPanel);
        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        initialState();

        table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTableMouseClicked(evt);
            }
        });

        BtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmptyTextBox()){
                    if(checkDate(textFieldReleaseDate.getText()) || checkDate(textFieldShelfDate.getText())){
                        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
                        Preparation preparation = new Preparation();

                        preparation.setTitle(textFieldTitle.getText());
                        preparation.setCount(Integer.parseInt(textFieldCount.getText()));
                        preparation.setCost(Double.parseDouble(textFieldCost.getText()));
                        preparation.setReleaseDate(parseDate(textFieldReleaseDate.getText()));
                        preparation.setShelfLife(parseDate(textFieldShelfDate.getText()));

                        Supplier supplier = supplierList.get(comboBoxSupplier.getSelectedIndex());
                        AppointmentMedicines appointmentMedicines = appointmentMedicinesList.get(comboBoxAppMed.getSelectedIndex());
                        Unit unit = unitList.get(comboBoxUnit.getSelectedIndex());
                        PlaceStorage placeStorage = placeStorageList.get(comboBoxPlaceStorage.getSelectedIndex());
                        ModeRelease modeRelease = modeReleaseList.get(comboBoxModeRelease.getSelectedIndex());
                        WayApply wayApply = wayApplyList.get(comboBoxWayApp.getSelectedIndex());

                        preparation.setSupplier(supplier);
                        preparation.setAppointmentMedicines(appointmentMedicines);
                        preparation.setUnit(unit);
                        preparation.setPlaceStorage(placeStorage);
                        preparation.setModeRelease(modeRelease);
                        preparation.setWayApply(wayApply);

                        preparationDao.add(preparation);

                        initialState();
                    } else {
                        JOptionPane.showMessageDialog(null, "Неправильный формат времени!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните поле!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        BtnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmptyTextBox() && selectedRow > -1){
                    if(checkDate(textFieldReleaseDate.getText()) || checkDate(textFieldShelfDate.getText())){
                        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
                        Preparation preparation = preparationList.get(selectedRow);

                        preparation.setTitle(textFieldTitle.getText());
                        preparation.setCount(Integer.parseInt(textFieldCount.getText()));
                        preparation.setCost(Double.parseDouble(textFieldCost.getText()));
                        preparation.setReleaseDate(parseDate(textFieldReleaseDate.getText()));
                        preparation.setShelfLife(parseDate(textFieldShelfDate.getText()));

                        Supplier supplier = supplierList.get(comboBoxSupplier.getSelectedIndex());
                        AppointmentMedicines appointmentMedicines = appointmentMedicinesList.get(comboBoxAppMed.getSelectedIndex());
                        Unit unit = unitList.get(comboBoxUnit.getSelectedIndex());
                        PlaceStorage placeStorage = placeStorageList.get(comboBoxPlaceStorage.getSelectedIndex());
                        ModeRelease modeRelease = modeReleaseList.get(comboBoxModeRelease.getSelectedIndex());
                        WayApply wayApply = wayApplyList.get(comboBoxWayApp.getSelectedIndex());

                        preparation.setSupplier(supplier);
                        preparation.setAppointmentMedicines(appointmentMedicines);
                        preparation.setUnit(unit);
                        preparation.setPlaceStorage(placeStorage);
                        preparation.setModeRelease(modeRelease);
                        preparation.setWayApply(wayApply);

                        preparationDao.edit(preparation);

                        initialState();
                    } else {
                        JOptionPane.showMessageDialog(null, "Неправильный формат времени!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните поле!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        BtnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedRow > -1 ) {
                    PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
                    Preparation preparation = preparationList.get(selectedRow);

                    preparationDao.remove(preparation);

                    initialState();
                } else {
                    JOptionPane.showMessageDialog(null, "Выберете поле!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initialState() {
        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
        preparationList = preparationDao.list();

        panelTabel.setLayout( new BorderLayout());

        table1.setModel(new preparationTable(preparationList));

        panelTabel.add(table1, BorderLayout.CENTER);
        panelTabel.add(table1.getTableHeader(), BorderLayout.NORTH);

        SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
        supplierList = supplierDao.list();

        AppointmentMedicinesImpl appointmentMedicinesDao = new AppointmentMedicinesImpl(App.statement);
        appointmentMedicinesList = appointmentMedicinesDao.list();

        UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
        unitList = unitDao.list();

        PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
        placeStorageList = placeStorageDao.list();

        ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(App.statement);
        modeReleaseList = modeReleaseDao.list();

        WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(App.statement);
        wayApplyList = wayApplyDao.list();

        loadComboboxUnits();
        loadComboboxSupplier();
        loadComboboxAppointMed();
        loadComboboxPlaceStorage();
        loadComboboxModeRelease();
        loadComboboxWayApply();

        if(!App.currentRole.getRole().equals("admin")) {
            panelControl.setVisible(false);

            panelControl.setOpaque(false);
        }
    }

    private Date parseDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateDB = null;

        try {
            java.util.Date dateStr = format.parse(date);
            dateDB = new java.sql.Date(dateStr.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateDB;
    }

    private boolean checkDate(String date) {
        Pattern p = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        Matcher m = p.matcher(date);
        return m.matches();
    }

    private boolean checkIsEmptyTextBox(){
        String count = textFieldCount.getText();
        String shelf = textFieldShelfDate.getText();
        String cpst = textFieldCost.getText();
        String release = textFieldReleaseDate.getText();
        String title = textFieldTitle.getText();

        if(count.length() > 0 && shelf.length() > 0 && cpst.length() > 0 && release.length() > 0 && title.length() > 0) {
            return false;
        }
        return true;
    }

    private void loadComboboxSupplier() {

        comboBoxSupplier.removeAllItems();

        for(int i = 0; i < supplierList.size(); i++) {
            comboBoxSupplier.addItem(supplierList.get(i).getTitle());
        }
    }

    private void loadComboboxAppointMed() {

        comboBoxAppMed.removeAllItems();

        for(int i = 0; i < appointmentMedicinesList.size(); i++) {
            comboBoxAppMed.addItem(appointmentMedicinesList.get(i).getGroup());
        }
    }

    private void loadComboboxPlaceStorage() {

        comboBoxPlaceStorage.removeAllItems();

        for(int i = 0; i < placeStorageList.size(); i++) {
            comboBoxPlaceStorage.addItem(placeStorageList.get(i).getPlaceStorage());
        }
    }

    private void loadComboboxModeRelease() {

        comboBoxModeRelease.removeAllItems();

        for(int i = 0; i < modeReleaseList.size(); i++) {
            comboBoxModeRelease.addItem(modeReleaseList.get(i).getTitle());
        }
    }

    private void loadComboboxWayApply() {
        comboBoxWayApp.removeAllItems();

        for(int i = 0; i < wayApplyList.size(); i++) {
            comboBoxWayApp.addItem(wayApplyList.get(i).getTitle());
        }
    }

    private void loadComboboxUnits() {

        comboBoxUnit.removeAllItems();

        for(int i = 0; i < unitList.size(); i++) {
            comboBoxUnit.addItem(unitList.get(i).getTitle());
        }
    }

    private void tableTableMouseClicked(MouseEvent evt) {
        int sel = table1.getSelectedRow();
        Object id = table1.getValueAt(sel, 0);

        selectedId = Integer.parseInt(id.toString());
        selectedRow = table1.getSelectedRow();

        setDataTextFields(sel);
    }

    private void setDataTextFields(int sel) {
        textFieldCost.setText(String.valueOf(preparationList.get(sel).getCost()));
        textFieldCount.setText(String.valueOf(preparationList.get(sel).getCount()));
        textFieldTitle.setText(preparationList.get(sel).getTitle());
        textFieldReleaseDate.setText(String.valueOf(preparationList.get(sel).getReleaseDate()));
        textFieldShelfDate.setText(String.valueOf(preparationList.get(sel).getShelfLife()));

        comboBoxSupplier.setSelectedIndex(preparationList.get(selectedRow).getSupplier().getId()-1);
        comboBoxAppMed.setSelectedIndex(preparationList.get(selectedRow).getAppointmentMedicines().getId()-1);
        comboBoxPlaceStorage.setSelectedIndex(preparationList.get(selectedRow).getPlaceStorage().getId()-1);
        comboBoxModeRelease.setSelectedIndex(preparationList.get(selectedRow).getModeRelease().getId()-1);
        comboBoxWayApp.setSelectedIndex(preparationList.get(selectedRow).getWayApply().getId()-1);
        comboBoxUnit.setSelectedIndex(preparationList.get(selectedRow).getUnit().getId()-1);
    }

    private JTextField textFieldTitle;
    private JTextField textFieldCost;
    private JTextField textFieldCount;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel panelTabel;
    private JTable table1;
    private JComboBox comboBoxSupplier;
    private JComboBox comboBoxAppMed;
    private JComboBox comboBoxUnit;
    private JComboBox comboBoxPlaceStorage;
    private JTextField textFieldReleaseDate;
    private JTextField textFieldShelfDate;
    private JComboBox comboBoxModeRelease;
    private JComboBox comboBoxWayApp;
    private JPanel rootPanel;
    private JPanel panelControl;
}
