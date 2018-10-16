package com;

import com.datamodel.Order;
import com.table.orderTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CheckForm extends JFrame  {
    public static List<Order> orderList  = new ArrayList<Order>();
    public static String sum, sale, entry, output;
    public static Order order;

    public CheckForm() {
        super("Чек");

        this.getContentPane().add(rootPanel);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

         initialState(orderList);
    }

    private void initialState(List<Order> neworderList) {
        orderList =  new ArrayList<Order>();
        entryLabelCount.setText(entry);
        outputLabel.setText(output);
        saleLabel.setText(sale);
        neworderList.add(order);
        table1.setModel(new orderTable(neworderList));
    }

    private JPanel rootPanel;
    private JTable table1;
    private JLabel entryLabel;
    private JLabel entryLabelCount;
    private JLabel outputLabel;
    private JLabel saleLabel;
}
