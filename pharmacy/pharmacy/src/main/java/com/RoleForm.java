package com;

import com.dataaccess.RoleDaoImpl;
import com.datamodel.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RoleForm extends JFrame {
    public static BuyForm buyForm;
    private List<Role> roleList;

    public RoleForm() {
        super("Доступ к программе");

        this.getContentPane().add(rootPanel);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        initialState();

        signInBtn.addActionListener(new BuyMenuListener());

        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private class BuyMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            if (checkRole()) {
                App.currentRole = roleList.get(comboBoxRole.getSelectedIndex());
                if (buyForm == null || !buyForm.isVisible()) {
                    dispose();
                    buyForm = null;
                    buyForm = new BuyForm();
                    buyForm.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Неправильный пароль!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean checkRole(){
        boolean auth = false;

        char[] s1 = passwordField.getPassword();
        String textPassword = new String(s1);

        if(textPassword.length() == 0) {
            textPassword = "";
        }

        for(int i=0; i<roleList.size(); i++) {
            String title = roleList.get(i).getTitle();
            String password = roleList.get(i).getPassword();

            if (title.equals(comboBoxRole.getSelectedItem())) {
                if(password == null || password.equals(textPassword)) {
                    auth = true;
                }
            }
        }
        return auth;
    }

    private void initialState() {
        RoleDaoImpl roleDao = new RoleDaoImpl(App.statement);

        roleList = roleDao.list();
        loadComboboxRoles();
    }

    private void loadComboboxRoles() {
        comboBoxRole.removeAllItems();

        for(int i = 0; i<roleList.size(); i++) {
            comboBoxRole.addItem(roleList.get(i).getTitle());
        }
    }

    private JPanel rootPanel;
    private JComboBox comboBoxRole;
    private JPasswordField passwordField;
    private JButton signInBtn;
    private JButton cancelBtn;
}
