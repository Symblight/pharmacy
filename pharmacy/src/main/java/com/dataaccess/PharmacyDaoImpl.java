package com.dataaccess;

import com.datamodel.Pharmacy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDaoImpl extends AbstractDao<Pharmacy> {
    protected java.sql.Statement stmt;

    public PharmacyDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Pharmacy entity) {
        String query = "INSERT INTO pharmacy (title, address, licenses, numberPhone)"
                + " VALUES ('"+entity.getTitle()+"', '"+entity.getAddress()+"', '"+entity.getLicense()+"', '"+entity.getNumberPhone()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Pharmacy entity) {
        String query = "UPDATE pharmacy set title='"+entity.getTitle()+"', address='"+entity.getAddress()+"', licenses='"+entity.getLicense()+"', numberPhone='"+entity.getNumberPhone()+"'  where idPharmacy="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Pharmacy entity) {
        String sql = "DELETE FROM pharmacy WHERE idPharmacy="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pharmacy> get(int idEntity) {

        String query = "SELECT * FROM pharmacy where idPharmacy="+idEntity+"";
        List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            pharmacyList = this.getList(resultSet, pharmacyList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pharmacyList;
    }

    public List<Pharmacy> list()
    {
        String query = "SELECT * FROM pharmacy;";
        List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            pharmacyList = this.getList(resultSet, pharmacyList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pharmacyList;
    }

    private List<Pharmacy> getList(ResultSet resultSet, List<Pharmacy> list) {
        try {
            while (resultSet.next()) {
                Pharmacy pharmacy = new Pharmacy();
                pharmacy.setId(resultSet.getInt(1));
                pharmacy.setTitle(resultSet.getString(2));
                pharmacy.setAddress(resultSet.getString(3));
                pharmacy.setNumberPhone(resultSet.getString(4));
                pharmacy.setLicense(resultSet.getString(5));

                list.add(pharmacy);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
