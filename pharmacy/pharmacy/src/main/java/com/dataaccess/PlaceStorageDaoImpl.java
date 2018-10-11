package com.dataaccess;

import com.datamodel.PlaceStorage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceStorageDaoImpl extends AbstractDao<PlaceStorage> {
    protected java.sql.Statement stmt;

    public PlaceStorageDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(PlaceStorage entity) {
        String query = "INSERT INTO placestorage (placeStorage)"
                + " VALUES ('"+entity.getPlaceStorage()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(PlaceStorage entity) {
        String query = "UPDATE placestorage set placeStorage='"+entity.getPlaceStorage()+"' where idPlaceStorage="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(PlaceStorage entity) {
        String sql = "DELETE FROM placestorage WHERE idPlaceStorage="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PlaceStorage> get(int idPlaceStorage)
    {
        String query = "select * from placestorage where idPlaceStorage="+idPlaceStorage;

        List<PlaceStorage> placeStorages = new ArrayList<PlaceStorage>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            placeStorages = this.getList(resultSet, placeStorages);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placeStorages;
    }

    public List<PlaceStorage> list()
    {
        String query = "SELECT * FROM placestorage;";
        List<PlaceStorage> placeStorages = new ArrayList<PlaceStorage>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            placeStorages = this.getList(resultSet, placeStorages);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return placeStorages;
    }

    private List<PlaceStorage> getList(ResultSet resultSet, List<PlaceStorage> list) {
        try {
            while (resultSet.next()) {
                PlaceStorage placeStorage = new PlaceStorage();
                placeStorage.setId(resultSet.getInt(1));
                placeStorage.setPlaceStorage(resultSet.getString(2));
                list.add(placeStorage);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
