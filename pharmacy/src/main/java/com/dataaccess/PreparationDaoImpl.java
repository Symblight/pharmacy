package com.dataaccess;

import com.datamodel.Preparation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.App.dbWorker;

public class PreparationDaoImpl extends AbstractDao<Preparation> {
    protected java.sql.Statement stmt;

    public PreparationDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Preparation entity) {
        String query = "INSERT INTO preparation (title, idSupplierPreparation, idAppointmentMedicinesPreparation, idUnitPreparation," +
                "idPlaceStoragePreparation, releaseDate, shelfDate, cost, idModeRaleasePreparation, countPreparation, idWayApplyPreparation)"
                + " VALUES ('"+entity.getTitle()+"', "+entity.getSupplier().getId()+", "+entity.getAppointmentMedicines().getId()+", "+entity.getUnit().getId()+"," +
                ""+entity.getPlaceStorage().getId()+", '"+entity.getReleaseDate()+"', '"+entity.getShelfLife()+"', "+entity.getCost()+", "+entity.getModeRelease().getId()+", "+entity.getCount()+", "+entity.getWayApply().getId()+")";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Preparation entity) {
        String query = "UPDATE preparation set title='"+entity.getTitle()+"', idSupplierPreparation="+entity.getSupplier().getId()+", idAppointmentMedicinesPreparation="+entity.getAppointmentMedicines().getId()+"," +
                "idUnitPreparation="+entity.getUnit().getId()+", idPlaceStoragePreparation="+entity.getPlaceStorage().getId()+", releaseDate='"+entity.getReleaseDate()+"', shelfDate='"+entity.getShelfLife()+"'," +
                "cost="+entity.getCost()+", idModeRaleasePreparation="+entity.getModeRelease().getId()+", countPreparation="+entity.getCount()+", idWayApplyPreparation="+entity.getWayApply().getId()+"" +
                " where idPreparation="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Preparation entity) {
        String sql = "DELETE FROM preparation WHERE idPreparation="+entity.getId()+"";


        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCount(Preparation entity) {
        String query = "UPDATE preparation set countPreparation="+entity.getCount()+" where idPreparation="+entity.getId()+"";

        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countPreparations(Preparation entity) {
        String sql = "SELECT * FROM preparation WHERE title='"+entity.getTitle()+"'";
        List<Preparation> preparationList = new ArrayList<Preparation>();
        int count = 0;
        try {

            ResultSet resultSet = stmt.executeQuery(sql);
            preparationList = this.getList(resultSet, preparationList);

            for(int i = 0; i<preparationList.size(); i++){
                count += preparationList.get(i).getCount();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Preparation> get(int idEntity) {
        String query = "SELECT * FROM preparation where idPreparation="+idEntity+"";
        List<Preparation> preparationList = new ArrayList<Preparation>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            preparationList = this.getList(resultSet, preparationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparationList;
    }

    public List<Preparation> list()
    {
        String query = "SELECT * FROM preparation;";
        List<Preparation> preparationList = new ArrayList<Preparation>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            preparationList = this.getList(resultSet, preparationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparationList;
    }

    private List<Preparation> getList(ResultSet resultSet, List<Preparation> list) {
        Statement stmtPreparation = null;

        try {
            stmtPreparation = dbWorker.getConnection().createStatement();
        } catch (SQLException e) {
        }

        SupplierDaoImpl supplierDao = new SupplierDaoImpl(stmtPreparation);
        AppointmentMedicinesImpl appointmentMedicines = new AppointmentMedicinesImpl(stmtPreparation);
        UnitDaoImpl unitDao = new UnitDaoImpl(stmtPreparation);
        PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(stmtPreparation);
        ModeReleaseDaoImpl modeReleaseDao = new ModeReleaseDaoImpl(stmtPreparation);
        WayApplyDaoImpl wayApplyDao = new WayApplyDaoImpl(stmtPreparation);

        try {
            while (resultSet.next()) {
                Preparation preparation = new Preparation();
                preparation.setId(resultSet.getInt(1));
                preparation.setTitle(resultSet.getString(2));
                preparation.setSupplier(supplierDao.get(resultSet.getInt(3)).get(0));
                preparation.setAppointmentMedicines(appointmentMedicines.get(resultSet.getInt(4)).get(0));
                preparation.setUnit(unitDao.get(resultSet.getInt(5)).get(0));
                preparation.setPlaceStorage(placeStorageDao.get(resultSet.getInt(6)).get(0));
                preparation.setReleaseDate(resultSet.getDate(7));
                preparation.setShelfLife(resultSet.getDate(8));
                preparation.setCost(resultSet.getDouble(9));
                preparation.setModeRelease(modeReleaseDao.get(resultSet.getInt(10)).get(0));
                preparation.setCount(resultSet.getInt(11));
                preparation.setWayApply(wayApplyDao.get(resultSet.getInt(12)).get(0));

                list.add(preparation);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
