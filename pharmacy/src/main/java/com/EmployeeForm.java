package com;

import com.dataaccess.EducationDaoImpl;
import com.dataaccess.EmployeeDaoImpl;
import com.dataaccess.PositionDaoImpl;
import com.datamodel.Education;
import com.datamodel.Employee;
import com.datamodel.Position;
import com.table.employeeTable;

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

public class EmployeeForm extends JFrame{
    private List<Employee> employeeList;
    private List<Position> positionList;
    private List<Education> educationList;
    private int selectedId;
    private int selectedRow;

    public EmployeeForm() {
        super("Сотрудники");

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
                if (!checkIsEmptyTextBox()) {
                    if(checkDate(textFieldStartWork.getText())){
                        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(App.statement);
                        Employee employee = new Employee();

                        employee.setLastName(textFieldLastName.getText());
                        employee.setFirstName(textFieldFirstName.getText());
                        employee.setMiddleName(textFieldMiddleName.getText());
                        employee.setAddress(textFieldAddress.getText());
                        employee.setNumberPhone(textFieldNumberPhone.getText());

                        Education newEducation = educationList.get(comboBoxEducation.getSelectedIndex());
                        Position newPosition = positionList.get(comboBoxPosition.getSelectedIndex());

                        employee.setEducation(newEducation);
                        employee.setPosition(newPosition);

                        employee.setStartWork(parseDate(textFieldStartWork.getText()));

                        employee.setSalary(Double.parseDouble(textFieldSolary.getText()));


                        employeeDao.add(employee);

                        initialState();
                    } else {
                        JOptionPane.showMessageDialog(null, "Неправильный формат времени!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Заполните поле!",
                                "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        BtnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmptyTextBox()){
                    if(checkDate(textFieldStartWork.getText())){
                        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(App.statement);
                        Employee employee = employeeList.get(selectedRow);

                        employee.setLastName(textFieldLastName.getText());
                        employee.setFirstName(textFieldFirstName.getText());
                        employee.setMiddleName(textFieldMiddleName.getText());
                        employee.setAddress(textFieldAddress.getText());
                        employee.setNumberPhone(textFieldNumberPhone.getText());

                        Education newEducation = educationList.get(comboBoxEducation.getSelectedIndex());
                        Position newPosition = positionList.get(comboBoxPosition.getSelectedIndex());

                        employee.setEducation(newEducation);
                        employee.setPosition(newPosition);

                        employee.setStartWork(parseDate(textFieldStartWork.getText()));

                        employee.setSalary(Double.parseDouble(textFieldSolary.getText()));


                        employeeDao.edit(employee);

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
        BtnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedRow > -1 ) {
                    EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(App.statement);
                    Employee employee = employeeList.get(selectedRow);

                    employeeDao.remove(employee);

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

    private boolean checkIsEmptyTextBox(){
        String address = textFieldAddress.getText();
        String first = textFieldFirstName.getText();
        String last = textFieldLastName.getText();
        String middle = textFieldMiddleName.getText();
        String number = textFieldNumberPhone.getText();
        String salary = textFieldSolary.getText();
        String start = textFieldStartWork.getText();

        if(address.length() > 0 && first.length() > 0 && last.length() > 0 && middle.length() > 0 && number.length() > 0 && salary.length() > 0 && start.length() > 0 ) {
            return false;
        }
        return true;
    }

    private void initialState() {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(App.statement);
        employeeList = employeeDao.list();

        panelTable.setLayout( new BorderLayout());

        table1.setModel(new employeeTable(employeeList));

        panelTable.add(table1, BorderLayout.CENTER);
        panelTable.add(table1.getTableHeader(), BorderLayout.NORTH);

        if(!App.currentRole.getRole().equals("admin")) {
            panelControl.setVisible(false);

            panelControl.setOpaque(false);
        }

        loadComboboxPosition();
        loadComboboxEducation();
    }

    private void loadComboboxEducation() {
        EducationDaoImpl educationDao = new EducationDaoImpl(App.statement);

        educationList = educationDao.list();

        comboBoxEducation.removeAllItems();

        for(int i = 0; i<educationList.size(); i++) {
            comboBoxEducation.addItem(educationList.get(i).getModeEducation());
        }
    }

    private void loadComboboxPosition() {
        PositionDaoImpl positionDao = new PositionDaoImpl(App.statement);

        positionList = positionDao.list();

        comboBoxPosition.removeAllItems();

        for(int i = 0; i<positionList.size(); i++) {
            comboBoxPosition.addItem(positionList.get(i).getPosition());
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
     //   textField1.setText(educationList.get(sel).getModeEducation());
        textFieldAddress.setText(employeeList.get(sel).getAddress());
        textFieldFirstName.setText(employeeList.get(sel).getFirstName());
        textFieldLastName.setText(employeeList.get(sel).getLastName());
        textFieldMiddleName.setText(employeeList.get(sel).getMiddleName());
        textFieldNumberPhone.setText(employeeList.get(sel).getNumberPhone());
        textFieldSolary.setText(String.valueOf(employeeList.get(sel).getSalary()));
        textFieldStartWork.setText(String.valueOf(employeeList.get(sel).getStartWork()));

        comboBoxPosition.setSelectedIndex(employeeList.get(selectedRow).getPosition().getId()-1);
        comboBoxEducation.setSelectedIndex(employeeList.get(selectedRow).getEducation().getId()-1);
    }

    private JTable table1;
    private JButton BtnAdd;
    private JButton BtnEdit;
    private JButton BtnRemove;
    private JTextField textFieldLastName;
    private JTextField textFieldFirstName;
    private JTextField textFieldMiddleName;
    private JPanel rootPanel;
    private JTextField textFieldAddress;
    private JTextField textFieldNumberPhone;
    private JComboBox comboBoxEducation;
    private JComboBox comboBoxPosition;
    private JTextField textFieldStartWork;
    private JTextField textFieldSolary;
    private JPanel panelTable;
    private JPanel panelControl;

}
