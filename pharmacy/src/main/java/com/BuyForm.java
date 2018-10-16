package com;

import com.dataaccess.EmployeeDaoImpl;
import com.dataaccess.PreparationDaoImpl;
import com.dataaccess.UnitDaoImpl;
import com.datamodel.Employee;
import com.datamodel.Order;
import com.datamodel.Preparation;
import com.table.orderTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyForm extends JFrame {

    public static MenuDirectory menuDirectory;
    public static StaticsForm staticsForm;
    private List<Employee> employeeList;
    private List<com.datamodel.Unit> unitList;
    private List<Preparation> preparationList;
    protected List<Order> orderList  = new ArrayList<Order>();
    private double result;
    private double resultSale;

    public BuyForm() {
        super("Продажи");

        this.getContentPane().add(rootPanel);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        initialState();

        ReferencesBtn.addActionListener(new MenuListener());
        addToTableBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addToTable();
            }
        });
        FinishBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField1.setText(null);
                textField2.setText(null);
                textField3.setText(null);
                textField4.setText(null);
                textField5.setText(null);
                textField6.setText(null);
                checkBox1.setSelected(false);
                result = 0;

                PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);

                for (int i=0; i<orderList.size(); i++) {
                    preparationDao.updateCount(orderList.get(i).getPreparation());
                }

                orderList.removeAll(orderList);

                table1.setModel(new orderTable(orderList));
            }
        });
        checkBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField3.setEnabled(true);
            }
        });
        textField5.addInputMethodListener(new InputMethodListener() {
            public void inputMethodTextChanged(InputMethodEvent event) {
                if(textField5.getText() != null){
                    System.out.println(textField5.getText());
                }
            }

            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        textField6.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if(textField5.getText() != null){
                    double cash = Double.parseDouble(textField5.getText());

                    textField6.setText(String.format("%.2f", cash-(checkBox1.isSelected() ? resultSale : result)));
                }
            }
        });
        textField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if(textField2.getText().length()>0 && checkBox1.isSelected()){

                    double sale = Double.parseDouble(textField3.getText());
                    double saleResult = result-((sale*result)/100);
                    resultSale = saleResult;

                    textField4.setText(String.format("%(.2f", saleResult));
                }
            }
        });
        btnStatics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(staticsForm == null || !staticsForm.isVisible()) {
                    staticsForm = null;
                    staticsForm = new StaticsForm();
                    staticsForm.setVisible(true);
                }
            }
        });
    }
    private class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if(menuDirectory == null || !menuDirectory.isVisible()) {
                menuDirectory = null;
                menuDirectory = new MenuDirectory();
                menuDirectory.setVisible(true);
            }
        }
    }

    private void addToTable(){
        Order newOrder = new Order();

        newOrder.setCount(Integer.parseInt(textFieldCount.getText()));
        newOrder.setPreparation(preparationList.get(comboBox2.getSelectedIndex()));
        newOrder.setCost(Integer.parseInt(textFieldCount.getText()) * preparationList.get(comboBox2.getSelectedIndex()).getCost());
        newOrder.setUnit(preparationList.get(comboBox2.getSelectedIndex()).getUnit().getTitle());

        if(CheckCount(newOrder.getCount())) {

            orderList.add(newOrder);
            table1.setModel(new orderTable(orderList));
            result += newOrder.getCost();
            textField2.setText(String.valueOf(result));

            int currentCount =  preparationList.get(comboBox2.getSelectedIndex()).getCount();

            preparationList.get(comboBox2.getSelectedIndex()).setCount(currentCount - newOrder.getCount());
        } else {
            JOptionPane.showMessageDialog(null, "Не хватает количества препаратов!",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckCount(int countGived){
       int count = preparationList.get(comboBox2.getSelectedIndex()).getCount();

       if((count - countGived) >=0) {
           return true;
       }

       return false;
    }

    private void initialState() {
        isConnectLabel.setText(App.isConnect);

        textField1.setText(String.valueOf(new Date()));

        ContentPanel.setLayout( new BorderLayout());

        table1.setModel(new orderTable(orderList));

        ContentPanel.add(table1, BorderLayout.CENTER);
        ContentPanel.add(table1.getTableHeader(), BorderLayout.NORTH);

        if(App.isConnect.length() > 0) {
            isConnectLabel.setForeground(Color.green);
        } else {
            isConnectLabel.setForeground(Color.red);
        }

        loadComboboxEmployee();
        loadComboboxUnits();
        loadComboboxPreparation();
    }

    private void loadComboboxUnits() {
        UnitDaoImpl unitDao = new UnitDaoImpl(App.statement);
        unitList = unitDao.list();

        comboBox3.removeAllItems();

        for(int i = 0; i < unitList.size(); i++) {
            comboBox3.addItem(unitList.get(i).getTitle());
        }
    }

    private void loadComboboxEmployee() {
        EmployeeDaoImpl employeeDao = new EmployeeDaoImpl(App.statement);

        employeeList = employeeDao.list();

        comboBox1.removeAllItems();

        for(int i = 0; i<employeeList.size(); i++) {
            comboBox1.addItem(employeeList.get(i).getLastName());
        }
    }

    private void loadComboboxPreparation() {
        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);

        preparationList = preparationDao.list();

        comboBox2.removeAllItems();

        for(int i = 0; i<preparationList.size(); i++) {
            comboBox2.addItem(preparationList.get(i).getTitle());
        }
    }

    private boolean checkDate(String date) {
        Pattern p = Pattern.compile("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
        Matcher m = p.matcher(date);
        return m.matches();
    }

    private JPanel HeaderPanel;
    private JPanel ContentPanel;
    private JPanel FooterPanel;
    private JTable table1;
    private JPanel DateBuy;
    private JPanel Employee;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JPanel rootPanel;
    private JButton ReferencesBtn;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox checkBox1;
    private JTextField textField5;
    private JTextField textField6;
    private JButton FinishBtn;
    private JLabel isConnectLabel;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JButton addToTableBtn;
    private JTextField textFieldCount;
    private JButton btnStatics;
}
