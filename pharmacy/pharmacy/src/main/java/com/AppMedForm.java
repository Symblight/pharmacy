package com;

import com.dataaccess.AppointmentMedicinesImpl;
import com.datamodel.AppointmentMedicines;
import com.table.appMedTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class AppMedForm extends JFrame{
    private List<AppointmentMedicines> appointmentMedicinesList;
    private int selectedId;
    private int selectedRow;

    public AppMedForm() {
        super("Назначение лекарств");

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
                AppointmentMedicinesImpl appointmentMedicinesDao = new AppointmentMedicinesImpl(App.statement);
                AppointmentMedicines newAppointmentMedicines = new AppointmentMedicines();

                if(textField1.getText().length() > 0 && textField2.getText().length() > 0) {
                    newAppointmentMedicines.setGroup(textField1.getText());
                    newAppointmentMedicines.setDescription(textField2.getText());

                    appointmentMedicinesDao.add(newAppointmentMedicines);

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
                AppointmentMedicinesImpl appointmentMedicinesDao = new AppointmentMedicinesImpl(App.statement);
                AppointmentMedicines newAppointmentMedicines;

                if(textField1.getText().length() > 0 && textField2.getText().length() > 0 && selectedRow > 0) {
                    newAppointmentMedicines = appointmentMedicinesList.get(selectedRow);

                    newAppointmentMedicines.setGroup(textField1.getText());
                    newAppointmentMedicines.setDescription(textField2.getText());

                    appointmentMedicinesDao.edit(newAppointmentMedicines);

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
                AppointmentMedicinesImpl appointmentMedicinesDao = new AppointmentMedicinesImpl(App.statement);
                AppointmentMedicines newAppointmentMedicines;

                if(textField1.getText().length() > 0 && textField2.getText().length() > 0 && selectedRow > 0) {
                    newAppointmentMedicines = appointmentMedicinesList.get(selectedRow);

                    appointmentMedicinesDao.remove(newAppointmentMedicines);

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
        AppointmentMedicinesImpl appointmentMedicinesDao = new AppointmentMedicinesImpl(App.statement);
        appointmentMedicinesList = appointmentMedicinesDao.list();

        panelEducation.setLayout( new BorderLayout());

        table1.setModel(new appMedTable(appointmentMedicinesList));

        panelEducation.add(table1, BorderLayout.CENTER);
        panelEducation.add(table1.getTableHeader(), BorderLayout.NORTH);

        if(!App.currentRole.getRole().equals("admin")) {
            panelControl.setVisible(false);

            panelControl.setOpaque(false);
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
        textField1.setText(appointmentMedicinesList.get(sel).getGroup());
        textField2.setText(appointmentMedicinesList.get(sel).getDescription());
    }

    private JTextField textField1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel panelEducation;
    private JTable table1;
    private JPanel rootPanel;
    private JTextField textField2;
    private JPanel panelControl;
}
