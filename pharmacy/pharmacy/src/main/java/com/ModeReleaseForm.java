package com;

import com.dataaccess.ModeReleaseDaoImpl;
import com.datamodel.ModeRelease;
import com.table.ModeReleaseTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class ModeReleaseForm extends JFrame {
    private List<ModeRelease> modeReleaseList;
    private int selectedId;
    private int selectedRow;

    public ModeReleaseForm(){
        super("Форма выпуска");

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
                ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(App.statement);
                ModeRelease newModeRelease = new ModeRelease();

                if(textField1.getText().length() > 0) {
                    newModeRelease.setTitle(textField1.getText());

                    modeReleaseDao.add(newModeRelease);

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
                ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(App.statement);
                ModeRelease newModeRelease;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newModeRelease = modeReleaseList.get(selectedRow);
                    newModeRelease.setTitle(textField1.getText());

                    modeReleaseDao.edit(newModeRelease);

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
                ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(App.statement);
                ModeRelease newModeRelease;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newModeRelease = modeReleaseList.get(selectedRow);

                    modeReleaseDao.remove(newModeRelease);

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
        ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(App.statement);
        modeReleaseList = modeReleaseDao.list();

        panelModel.setLayout( new BorderLayout());

        table1.setModel(new ModeReleaseTable(modeReleaseList));

        panelModel.add(table1, BorderLayout.CENTER);
        panelModel.add(table1.getTableHeader(), BorderLayout.NORTH);


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
        textField1.setText(modeReleaseList.get(sel).getTitle());
    }

    private JTextField textField1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTable table1;
    private JPanel rootPanel;
    private JPanel panelModel;
    private JPanel panelControl;
}
