package com;

import com.dataaccess.UnitDaoImpl;
import com.datamodel.Unit;
import com.table.unitTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class UnitForm extends JFrame{
    private List<Unit> unitList;
    private int selectedId;
    private int selectedRow;

    public UnitForm() {
        super("Ед измерения");

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
                UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
                Unit newUnit = new Unit();

                if(textField1.getText().length() > 0) {
                    newUnit.setTitle(textField1.getText());

                    unitDao.add(newUnit);

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
                UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
                Unit newUnit;

                if(textField1.getText().length() > 0 & selectedRow > -1) {
                    newUnit = unitList.get(selectedRow);
                    newUnit.setTitle(textField1.getText());

                    unitDao.edit(newUnit);

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
                UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
                Unit newUnit;

                if(textField1.getText().length() > 0 & selectedRow > -1) {
                    newUnit = unitList.get(selectedRow);

                    unitDao.remove(newUnit);

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
        UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
        unitList = unitDao.list();

        panelTable.setLayout( new BorderLayout());

        table1.setModel(new unitTable(unitList));

        panelTable.add(table1, BorderLayout.CENTER);
        panelTable.add(table1.getTableHeader(), BorderLayout.NORTH);

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
        textField1.setText(unitList.get(sel).getTitle());
    }
    private JTextField textField1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTable table1;
    private JPanel rootPanel;
    private JPanel panelControl;
    private JPanel panelTable;
}
