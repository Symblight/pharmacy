package com;

import com.dataaccess.WayApplyDaoImpl;
import com.datamodel.WayApply;
import com.table.WayApplyTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class WayApplyForm extends JFrame{
    private List<WayApply> wayApplyList;
    private int selectedId;
    private int selectedRow;

    public WayApplyForm() {
        super("Способ применения");

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
                WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(App.statement);
                WayApply newWayApply = new WayApply();

                if(textField1.getText().length() > 0) {
                    newWayApply.setTitle(textField1.getText());

                    wayApplyDao.add(newWayApply);

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
                WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(App.statement);
                WayApply newWayApply;

                if(textField1.getText().length() > 0 && selectedRow > -1) {
                    newWayApply = wayApplyList.get(selectedRow);
                    newWayApply.setTitle(textField1.getText());

                    wayApplyDao.edit(newWayApply);

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
                WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(App.statement);
                WayApply newWayApply;

                if(textField1.getText().length() > 0 && selectedRow > -1) {
                    newWayApply = wayApplyList.get(selectedRow);

                    wayApplyDao.remove(newWayApply);

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
        WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(App.statement);
        wayApplyList = wayApplyDao.list();

        panelTable.setLayout( new BorderLayout());

        table1.setModel(new WayApplyTable(wayApplyList));

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
        textField1.setText(wayApplyList.get(sel).getTitle());
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
