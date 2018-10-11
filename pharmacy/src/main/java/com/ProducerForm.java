package com;

import com.dataaccess.ProducerDaoImpl;
import com.datamodel.Producer;
import com.table.producerTable;

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

public class ProducerForm extends JFrame {
    private List<Producer> producerList;
    private int selectedId;
    private int selectedRow;

    public ProducerForm() {
        super("Изготовители");

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
                    if(checkDate(textFieldDateCreate.getText())) {
                        ProducerDaoImpl producerDao = new ProducerDaoImpl(App.statement);
                        Producer producer = new Producer();

                        producer.setTitle(textFieldTitle.getText());
                        producer.setAddress(textFieldAddress.getText());
                        producer.setNumberPhone(textFieldNumberPhone.getText());
                        producer.setDateCreate(parseDate(textFieldDateCreate.getText()));
                        producer.setEmail(textFieldEmail.getText());

                        producerDao.add(producer);

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
                    if(checkDate(textFieldDateCreate.getText())) {
                        ProducerDaoImpl producerDao = new ProducerDaoImpl(App.statement);
                        Producer producer = producerList.get(selectedRow);

                        producer.setTitle(textFieldTitle.getText());
                        producer.setAddress(textFieldAddress.getText());
                        producer.setNumberPhone(textFieldNumberPhone.getText());
                        producer.setDateCreate(parseDate(textFieldDateCreate.getText()));
                        producer.setEmail(textFieldEmail.getText());

                        producerDao.edit(producer);

                        initialState();
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
                    ProducerDaoImpl producerDao = new ProducerDaoImpl(App.statement);
                    Producer producer = producerList.get(selectedRow);

                    producerDao.remove(producer);

                    initialState();
                } else {
                    JOptionPane.showMessageDialog(null, "Выберете поле!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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

    private void initialState() {
        ProducerDaoImpl producerDao = new ProducerDaoImpl(App.statement);
        producerList = producerDao.list();

        panelTabel.setLayout( new BorderLayout());

        table1.setModel(new producerTable(producerList));

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
        String email = textFieldEmail.getText();
        String agent = textFieldDateCreate.getText();

        if(title.length() > 0 && address.length() > 0 && number.length() > 0 &&  email.length() > 0 && agent.length() > 0) {
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
        textFieldTitle.setText(producerList.get(sel).getTitle());
        textFieldDateCreate.setText(String.valueOf(producerList.get(sel).getDateCreate()));
        textFieldAddress.setText(producerList.get(sel).getAddress());
        textFieldEmail.setText(producerList.get(sel).getEmail());
        textFieldNumberPhone.setText(producerList.get(sel).getNumberPhone());
    }

    private JTextField textFieldTitle;
    private JTextField textFieldDateCreate;
    private JTextField textFieldNumberPhone;
    private JTextField textFieldEmail;
    private JPanel panelTabel;
    private JTable table1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JPanel rootPanel;
    private JTextField textFieldAddress;
    private JPanel panelControl;
}
