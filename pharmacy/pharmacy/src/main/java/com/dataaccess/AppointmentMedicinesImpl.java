package com.dataaccess;

import com.datamodel.AppointmentMedicines;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMedicinesImpl extends AbstractDao<AppointmentMedicines> {
    protected java.sql.Statement stmt;

    public AppointmentMedicinesImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(AppointmentMedicines entity) {
        String query = "INSERT INTO appointmentmedicines (`group`, `description`)"
                + " VALUES ('"+entity.getGroup()+"', '"+entity.getDescription()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(AppointmentMedicines entity) {
        String query = "UPDATE appointmentmedicines set `group`='"+entity.getGroup()+"', `description`='"+entity.getDescription()+"' where idAppointmentMedicines="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(AppointmentMedicines entity) {
        String sql = "DELETE FROM appointmentmedicines WHERE idAppointmentMedicines="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AppointmentMedicines> get(int idEntity) {

        String query = "select * from appointmentmedicines where idAppointmentMedicines="+idEntity;

        List<AppointmentMedicines> appointmentMedicinesList = new ArrayList<AppointmentMedicines>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            appointmentMedicinesList = this.getList(resultSet, appointmentMedicinesList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentMedicinesList;
    }

    public List<AppointmentMedicines> list() {
        String query = "SELECT * FROM appointmentmedicines;";
        List<AppointmentMedicines> appointmentMedicinesList = new ArrayList<AppointmentMedicines>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            appointmentMedicinesList = this.getList(resultSet, appointmentMedicinesList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentMedicinesList;
    }

    private List<AppointmentMedicines> getList(ResultSet resultSet, List<AppointmentMedicines> list) {
        try {
            while (resultSet.next()) {
                AppointmentMedicines appointmentMedicines = new AppointmentMedicines();
                appointmentMedicines.setId(resultSet.getInt(1));
                appointmentMedicines.setGroup(resultSet.getString(2));
                appointmentMedicines.setDescription(resultSet.getString(3));
                list.add(appointmentMedicines);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
