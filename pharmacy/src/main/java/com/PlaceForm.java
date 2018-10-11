package com;

import com.dataaccess.PlaceStorageDaoImpl;
import com.datamodel.PlaceStorage;
import com.table.placeStorageTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlaceForm extends JFrame{
    private List<PlaceStorage> placeStorageList;
    private int selectedId;
    private int selectedRow;

    public PlaceForm() {
        super("Место хранения");

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
                PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
                PlaceStorage newPlaceStorage = new PlaceStorage();

                if(textField1.getText().length() > 0) {
                    newPlaceStorage.setPlaceStorage(textField1.getText());

                    placeStorageDao.add(newPlaceStorage);

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
                PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
                PlaceStorage newPlaceStorage;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newPlaceStorage = placeStorageList.get(selectedRow);
                    newPlaceStorage.setPlaceStorage(textField1.getText());

                    placeStorageDao.edit(newPlaceStorage);

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
                PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
                PlaceStorage newPlaceStorage;

                if(textField1.getText().length() > 0  && selectedRow > -1) {
                    newPlaceStorage = placeStorageList.get(selectedRow);

                    placeStorageDao.remove(newPlaceStorage);

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
        PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(App.statement);
        placeStorageList = placeStorageDao.list();

        panelPlace.setLayout( new BorderLayout());

        table1.setModel(new placeStorageTable(placeStorageList));

        panelPlace.add(table1, BorderLayout.CENTER);
        panelPlace.add(table1.getTableHeader(), BorderLayout.NORTH);

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
        textField1.setText(placeStorageList.get(sel).getPlaceStorage());
    }

    private JTextField textField1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTable table1;
    private JPanel rootPanel;
    private JPanel panelPlace;
    private JPanel panelControl;
}
