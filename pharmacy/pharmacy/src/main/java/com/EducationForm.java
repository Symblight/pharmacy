package com;

import com.dataaccess.EducationDaoImpl;
import com.datamodel.Education;
import com.table.EducationTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class EducationForm extends JFrame {
    private List<Education> educationList;
    private int selectedId;
    private int selectedRow;

    public EducationForm(){
        super("Образование");

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
                EducationDaoImpl educationDao = new EducationDaoImpl(App.statement);
                Education newEducation = new Education();

                if(textField1.getText().length() > 0) {
                    newEducation.setModeEducation(textField1.getText());

                    educationDao.add(newEducation);

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
                EducationDaoImpl educationDao = new EducationDaoImpl(App.statement);
                Education newEducation;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newEducation = educationList.get(selectedRow);
                    newEducation.setModeEducation(textField1.getText());

                    educationDao.edit(newEducation);

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
                EducationDaoImpl educationDao = new EducationDaoImpl(App.statement);
                Education newEducation;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newEducation = educationList.get(selectedRow);

                    educationDao.remove(newEducation);

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
        EducationDaoImpl educationDao = new EducationDaoImpl(App.statement);
        educationList = educationDao.list();

        panelEducation.setLayout( new BorderLayout());

        table1.setModel(new EducationTable(educationList));

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
        textField1.setText(educationList.get(sel).getModeEducation());
    }

    private JTable table1;
    private JPanel rootPanel;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTextField textField1;
    private JPanel panelEducation;
    private JPanel panelControl;

}
