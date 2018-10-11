package com;

import com.dataaccess.PositionDaoImpl;
import com.datamodel.Position;
import com.table.positionTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class PostionForm extends JFrame{
    private List<Position> positionList;
    private int selectedId;
    private int selectedRow;

    public PostionForm() {
        super("Должности");

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
                PositionDaoImpl positionDao = new PositionDaoImpl(App.statement);
                Position newPosition = new Position();

                if(textField1.getText().length() > 0) {
                    newPosition.setPosition(textField1.getText());

                    positionDao.add(newPosition);

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
                PositionDaoImpl positionDao = new PositionDaoImpl(App.statement);
                Position newPosition;

                if(textField1.getText().length() > 0 && selectedRow > -1) {
                    newPosition = positionList.get(selectedRow);
                    newPosition.setPosition(textField1.getText());

                    positionDao.edit(newPosition);

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
                PositionDaoImpl positionDao = new PositionDaoImpl(App.statement);
                Position newPosition;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newPosition = positionList.get(selectedRow);

                    positionDao.remove(newPosition);

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
        PositionDaoImpl positionDao = new PositionDaoImpl(App.statement);
        positionList = positionDao.list();

        panelTable.setLayout( new BorderLayout());

        table1.setModel(new positionTable(positionList));

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
        textField1.setText(positionList.get(sel).getPosition());
    }

    private JTextField textField1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTable table1;
    private JPanel rootPanel;
    private JPanel panelTable;
    private JPanel panelControl;
}
