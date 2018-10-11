package com;

import com.dataaccess.PlaceStorageDaoImpl;
import com.dataaccess.PreparationDaoImpl;
import com.dataaccess.RecedeDaoImpl;
import com.dataaccess.SupplierDaoImpl;
import com.datamodel.PlaceStorage;
import com.datamodel.Preparation;
import com.datamodel.Recede;
import com.datamodel.Supplier;
import com.table.recedeTable;

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

public class RecedeForm extends JFrame {
    private List<Recede> recedeList;
    private List<Supplier> supplierList;
    private List<PlaceStorage> placeStorageList;
    private List<Preparation> preparationList;
    private int selectedId;
    private int selectedRow;

    public RecedeForm() {
        super("Приход");

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
                    if(checkDate(textFieldDateComing.getText())) {
                        RecedeDaoImpl recedeDao = new RecedeDaoImpl(App.statement);
                        Recede recede = new Recede();

                        recede.setCost(Double.parseDouble(textFieldCost.getText()));
                        recede.setCount(Integer.parseInt(textFieldCount.getText()));
                        recede.setDateComing(parseDate(textFieldDateComing.getText()));
                        recede.setTypeInvoice(textFieldType.getText());

                        Supplier supplier = supplierList.get(comboBoxSupplier.getSelectedIndex());
                        PlaceStorage placeStorage = placeStorageList.get(comboBoxPlaceStorage.getSelectedIndex());
                        Preparation preparation = preparationList.get(comboBoxPreparation.getSelectedIndex());

                        recede.setSupplier(supplier);
                        recede.setPlaceStorage(placeStorage);
                        recede.setPreparation(preparation);

                        recedeDao.add(recede);

                        recedeDao.updateCountPreparation(recede, "sum");

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
                    if(checkDate(textFieldDateComing.getText())) {
                        RecedeDaoImpl recedeDao = new RecedeDaoImpl(App.statement);
                        Recede recede = recedeList.get(selectedRow);

                        recede.setCost(Double.parseDouble(textFieldCost.getText()));
                        recede.setCount(Integer.parseInt(textFieldCount.getText()));
                        recede.setDateComing(parseDate(textFieldDateComing.getText()));
                        recede.setTypeInvoice(textFieldType.getText());

                        Supplier supplier = supplierList.get(comboBoxSupplier.getSelectedIndex());
                        PlaceStorage placeStorage = placeStorageList.get(comboBoxPlaceStorage.getSelectedIndex());
                        Preparation preparation = preparationList.get(comboBoxPreparation.getSelectedIndex());

                        recede.setSupplier(supplier);
                        recede.setPlaceStorage(placeStorage);
                        recede.setPreparation(preparation);

                        recedeDao.edit(recede);
                        recedeDao.updateCountPreparation(recede, "edit");

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
                    RecedeDaoImpl recedeDao = new RecedeDaoImpl(App.statement);
                    Recede recede = recedeList.get(selectedRow);

                    recedeDao.remove(recede);
                    recedeDao.updateCountPreparation(recede, "diff");

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
        RecedeDaoImpl recedeDao = new RecedeDaoImpl(App.statement);
        recedeList = recedeDao.list();

        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
        preparationList = preparationDao.list();

        SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
        supplierList = supplierDao.list();

        PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
        placeStorageList = placeStorageDao.list();

        loadComboboxPlaceStorage();
        loadComboboxSupplier();
        loadComboboxPreparation();

        panelTabel.setLayout( new BorderLayout());

        table1.setModel(new recedeTable(recedeList));

        panelTabel.add(table1, BorderLayout.CENTER);
        panelTabel.add(table1.getTableHeader(), BorderLayout.NORTH);

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
        String title = textFieldCount.getText();
        String address = textFieldType.getText();
        String number = textFieldCost.getText();
        String lic = textFieldDateComing.getText();

        if(title.length() > 0 && address.length() > 0 && number.length() > 0 && lic.length() > 0) {
            return false;
        }

        return true;
    }

    private void loadComboboxPlaceStorage() {

        comboBoxPlaceStorage.removeAllItems();

        for(int i = 0; i < placeStorageList.size(); i++) {
            comboBoxPlaceStorage.addItem(placeStorageList.get(i).getPlaceStorage());
        }
    }

    private void loadComboboxSupplier() {

        comboBoxSupplier.removeAllItems();

        for(int i = 0; i < supplierList.size(); i++) {
            comboBoxSupplier.addItem(supplierList.get(i).getTitle());
        }
    }

    private void loadComboboxPreparation() {

        comboBoxPreparation.removeAllItems();

        for(int i = 0; i<preparationList.size(); i++) {
            comboBoxPreparation.addItem(preparationList.get(i).getTitle());
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
        textFieldCost.setText(String.valueOf(recedeList.get(sel).getCost()));
        textFieldCount.setText(String.valueOf(recedeList.get(sel).getCount()));
        textFieldDateComing.setText(String.valueOf(recedeList.get(sel).getDateComing()));
        textFieldType.setText(recedeList.get(sel).getTypeInvoice());

        comboBoxPlaceStorage.setSelectedIndex(recedeList.get(selectedRow).getPlaceStorage().getId()-1);
        comboBoxPreparation.setSelectedIndex(recedeList.get(selectedRow).getPreparation().getId()-1);
        comboBoxSupplier.setSelectedIndex(recedeList.get(selectedRow).getSupplier().getId()-1);
    }


    private JTextField textFieldType;
    private JTextField textFieldDateComing;
    private JPanel panelTabel;
    private JTable table1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel rootPanel;
    private JComboBox comboBoxPlaceStorage;
    private JComboBox comboBoxSupplier;
    private JComboBox comboBoxPreparation;
    private JTextField textFieldCost;
    private JLabel е;
    private JTextField textFieldCount;
    private JPanel panelControl;
}
