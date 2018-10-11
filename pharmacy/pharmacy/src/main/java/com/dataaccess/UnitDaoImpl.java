package com.dataaccess;

import com.datamodel.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDaoImpl extends AbstractDao<Unit> {
    protected java.sql.Statement stmt;

    public UnitDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Unit entity) {
        String query = "INSERT INTO unit (title)"
                + " VALUES ('"+entity.getTitle()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Unit entity) {
        String query = "UPDATE unit set title='"+entity.getTitle()+"' where idUnit="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Unit entity) {
        String sql = "DELETE FROM unit WHERE idUnit="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Unit> get(int idEntity) {
        String query = "select * from unit where idUnit="+idEntity;

        List<Unit> units = new ArrayList<Unit>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            units = this.getList(resultSet, units);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    public List<Unit> list()
    {
        String query = "SELECT * FROM unit;";
        List<Unit> units = new ArrayList<Unit>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            units = this.getList(resultSet, units);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    private List<Unit> getList(ResultSet resultSet, List<Unit> list) {
        try {
            while (resultSet.next()) {
                Unit unit = new Unit();
                unit.setId(resultSet.getInt(1));
                unit.setTitle(resultSet.getString(2));
                list.add(unit);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
