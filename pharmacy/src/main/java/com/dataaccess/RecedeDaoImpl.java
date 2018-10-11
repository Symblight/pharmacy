package com.dataaccess;

import com.App;
import com.datamodel.Preparation;
import com.datamodel.Recede;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.App.dbWorker;

public class RecedeDaoImpl extends AbstractDao<Recede> {
    protected java.sql.Statement stmt;

    public RecedeDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Recede entity) {
        String query = "INSERT INTO recede (typeInvoice, dateComing, idPlaceStorageRecede, idSupplierRecede, idPreparationRecede, count, cost)"
                + " VALUES ('"+entity.getTypeInvoice()+"', '"+entity.getDateComing()+"', "+entity.getPlaceStorage().getId()+", "+entity.getSupplier().getId()+", "+entity.getPreparation().getId()+"," +
                ""+entity.getCount()+", "+entity.getCost()+")";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Recede entity) {
        String query = "UPDATE recede set typeInvoice='"+entity.getTypeInvoice()+"', dateComing='"+entity.getDateComing()+"', idPlaceStorageRecede="+entity.getPlaceStorage().getId()+"," +
                "idSupplierRecede="+entity.getSupplier().getId()+", idPreparationRecede="+entity.getPreparation().getId()+", count="+entity.getCount()+", cost="+entity.getCost()+" " +
                "where idRecede="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Recede entity) {
        String sql = "DELETE FROM  recede where idRecede="+entity.getId();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCountPreparation(Recede recede, String type) {
        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);

        int countPrep = preparationDao.countPreparations(recede.getPreparation());

        if(type.equals("sum")) {
            countPrep = countPrep + recede.getCount();
        } else if(type.equals("edit")){
            countPrep= recede.getCount();
        } else {
            countPrep = countPrep - recede.getCount();
        }

        String sql = "UPDATE preparation set countPreparation="+countPrep+" where idPreparation="+recede.getPreparation().getId()+"";

        try {
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recede> get(int idEntity) {
        String query = "SELECT * FROM recede where idRecede="+idEntity;
        List<Recede> recedes = new ArrayList<Recede>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            recedes = this.getList(resultSet, recedes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recedes;
    }

    public List<Recede> list() {
        String query = "SELECT * FROM recede;";
        List<Recede> recedes = new ArrayList<Recede>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            recedes = this.getList(resultSet, recedes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recedes;
    }

    private List<Recede> getList(ResultSet resultSet, List<Recede> list) {
        Statement stmtPreparation = null;

        try {
            stmtPreparation = dbWorker.getConnection().createStatement();
        } catch (SQLException e) {
        }

        SupplierDaoImpl supplierDao = new SupplierDaoImpl(stmtPreparation);
        PlaceStorageDaoImpl placeStorageDao = new PlaceStorageDaoImpl(stmtPreparation);
        PreparationDaoImpl preparationDao = new PreparationDaoImpl(stmtPreparation);

        try {
            while (resultSet.next()) {
                Recede recede = new Recede();
                recede.setId(resultSet.getInt(1));
                recede.setTypeInvoice(resultSet.getString(2));
                recede.setDateComing(resultSet.getDate(3));
                recede.setPlaceStorage(placeStorageDao.get(resultSet.getInt(4)).get(0));
                recede.setSupplier(supplierDao.get(resultSet.getInt(5)).get(0));
                recede.setPreparation(preparationDao.get(resultSet.getInt(6)).get(0));
                recede.setCount(resultSet.getInt(7));
                recede.setCost(resultSet.getDouble(8));

                list.add(recede);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
