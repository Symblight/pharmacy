package com;

import com.dataaccess.SupplierDaoImpl;
import com.datamodel.Supplier;
import com.table.supplierTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class SupplierForm extends JFrame {
    private List<Supplier> supplierList;
    private int selectedId;
    private int selectedRow;

    public SupplierForm() {
        super("Поставщики");

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

                    SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
                    Supplier supplier = new Supplier();

                    supplier.setTitle(textFieldTitle.getText());
                    supplier.setAddress(textFieldAddress.getText());
                    supplier.setNumberPhone(textFieldNumberPhone.getText());
                    supplier.setPosition(textFieldPosition.getText());
                    supplier.setEmail(textFieldEmail.getText());
                    supplier.setAgent(textFieldAgent.getText());

                    supplierDao.add(supplier);

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
                    SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
                    Supplier supplier = supplierList.get(selectedRow);

                    supplier.setTitle(textFieldTitle.getText());
                    supplier.setAddress(textFieldAddress.getText());
                    supplier.setNumberPhone(textFieldNumberPhone.getText());
                    supplier.setPosition(textFieldPosition.getText());
                    supplier.setEmail(textFieldEmail.getText());
                    supplier.setAgent(textFieldAgent.getText());

                    supplierDao.edit(supplier);

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
                    SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
                    Supplier supplier = supplierList.get(selectedRow);

                    supplierDao.remove(supplier);

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
        SupplierDaoImpl supplierDao = new SupplierDaoImpl(App.statement);
        supplierList = supplierDao.list();

        panelTabel.setLayout( new BorderLayout());

        table1.setModel(new supplierTable(supplierList));

        panelTabel.add(table1, BorderLayout.CENTER);
        panelTabel.add(table1.getTableHeader(), BorderLayout.NORTH);

        if(!App.currentRole.getRole().equals("admin")) {
            panelControl.setVisible(false);

            panelControl.setOpaque(false);
        }
    }

    private boolean checkIsEmptyTextBox(){
        String title = textFieldTitle.getText();
        String address = textFieldAddress.getText();
        String number = textFieldNumberPhone.getText();
        String lic = textFieldPosition.getText();
        String email = textFieldEmail.getText();
        String agent = textFieldAgent.getText();

        if(title.length() > 0 && address.length() > 0 && number.length() > 0 && lic.length() > 0 && email.length() > 0 && agent.length() > 0) {
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
        textFieldTitle.setText(supplierList.get(sel).getTitle());
        textFieldPosition.setText(supplierList.get(sel).getPosition());
        textFieldAddress.setText(supplierList.get(sel).getAddress());
        textFieldAgent.setText(supplierList.get(sel).getAgent());
        textFieldEmail.setText(supplierList.get(sel).getEmail());
        textFieldNumberPhone.setText(supplierList.get(sel).getNumberPhone());
    }

    private JPanel rootPanel;
    private JTable table1;
    private JPanel panelTabel;
    private JTextField textFieldTitle;
    private JTextField textFieldAgent;
    private JTextField textFieldPosition;
    private JTextField textFieldAddress;
    private JTextField textFieldNumberPhone;
    private JTextField textFieldEmail;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel panelControl;
}
