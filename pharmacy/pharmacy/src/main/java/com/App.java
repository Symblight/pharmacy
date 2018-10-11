package com;

import com.datamodel.Role;

import java.sql.SQLException;

public class App {
    public static DBWorker dbWorker;
    public static java.sql.Statement statement;
    public static String isConnect;
    public static Role currentRole;

    public static void main (String[] args) {

        dbWorker = new DBWorker();
        try {
            statement = dbWorker.getConnection().createStatement();
        } catch (SQLException e) {
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoleForm().setVisible(true);
                try {
                } catch (Exception e) {
                }
            }
        });
    }

}
