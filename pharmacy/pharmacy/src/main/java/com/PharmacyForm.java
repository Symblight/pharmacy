package com;

import com.dataaccess.PharmacyDaoImpl;
import com.datamodel.Pharmacy;
import com.table.pharmacyTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class PharmacyForm extends JFrame {
    private List<Pharmacy> pharmacyList;
    private int selectedId;
    private int selectedRow;

    public PharmacyForm() {
        super("Аптеки");

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
                    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(App.statement);
                    Pharmacy pharmacy = new Pharmacy();

                    pharmacy.setTitle(textFieldTittle.getText());
                    pharmacy.setAddress(textFieldAddress.getText());
                    pharmacy.setNumberPhone(textFieldNumberPhone.getText());
                    pharmacy.setLicense(textFieldLicenses.getText());

                    pharmacyDao.add(pharmacy);

                    initialState();

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
                    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(App.statement);
                    Pharmacy pharmacy = pharmacyList.get(selectedRow);

                    pharmacy.setTitle(textFieldTittle.getText());
                    pharmacy.setAddress(textFieldAddress.getText());
                    pharmacy.setNumberPhone(textFieldNumberPhone.getText());
                    pharmacy.setLicense(textFieldLicenses.getText());

                    pharmacyDao.edit(pharmacy);

                    initialState();
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
                    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(App.statement);
                    Pharmacy pharmacy = pharmacyList.get(selectedRow);

                    pharmacyDao.remove(pharmacy);

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
        PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(App.statement);
        pharmacyList = pharmacyDao.list();

        panelEducation.setLayout( new BorderLayout());

        table1.setModel(new pharmacyTable(pharmacyList));

        panelEducation.add(table1, BorderLayout.CENTER);
        panelEducation.add(table1.getTableHeader(), BorderLayout.NORTH);

        if(!App.currentRole.getRole().equals("admin")) {
            panelControl.setVisible(false);

            panelControl.setOpaque(false);
        }
    }

    private boolean checkIsEmptyTextBox(){
        String title = textFieldTittle.getText();
        String address = textFieldAddress.getText();
        String number = textFieldNumberPhone.getText();
        String lic = textFieldLicenses.getText();

        if(title.length() > 0 && address.length() > 0 && number.length() > 0 && lic.length() > 0 ) {
            return false;
        }

        return true;
    }

    private void tableTableMouseClicked(MouseEvent evt) {
        int sel = table1.getSelectedRow();
        Object id = table1.getValueAt(sel, 0);

        selectedId = Integer.parseInt(id.toString());
        selectedRow = table1.getSelectedRow();

        setDataTextFields(sel);
    }

    private void setDataTextFields(int sel) {
        textFieldTittle.setText(pharmacyList.get(sel).getTitle());
        textFieldAddress.setText(pharmacyList.get(sel).getAddress());
        textFieldLicenses.setText(pharmacyList.get(sel).getLicense());
        textFieldNumberPhone.setText(pharmacyList.get(sel).getNumberPhone());
    }


    private JTextField textFieldTittle;
    private JTextField textFieldAddress;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel panelEducation;
    private JTable table1;
    private JPanel rootPanel;
    private JTextField textFieldNumberPhone;
    private JTextField textFieldLicenses;
    private JPanel panelControl;
}
