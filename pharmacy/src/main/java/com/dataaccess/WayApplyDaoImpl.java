package com.dataaccess;

import com.datamodel.WayApply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WayApplyDaoImpl extends AbstractDao<WayApply> {
    protected java.sql.Statement stmt;

    public WayApplyDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(WayApply entity) {
        String query = "INSERT INTO WayApply (title)"
                + " VALUES ('"+entity.getTitle()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(WayApply entity) {
        String query = "UPDATE WayApply set title='"+entity.getTitle()+"' where idWayApply="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(WayApply entity) {
        String sql = "DELETE FROM WayApply WHERE idWayApply="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<WayApply> get(int idEntity) {
        String query = "select * from WayApply where idWayApply="+idEntity;

        List<WayApply> wayApplies = new ArrayList<WayApply>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            wayApplies = this.getList(resultSet, wayApplies);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return wayApplies;
    }

    public List<WayApply> list() {
        String query = "SELECT * FROM WayApply;";
        List<WayApply> wayApplyList = new ArrayList<WayApply>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            wayApplyList = this.getList(resultSet, wayApplyList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wayApplyList;
    }

    private List<WayApply> getList(ResultSet resultSet, List<WayApply> list) {
        try {
            while (resultSet.next()) {
                WayApply wayApply = new WayApply();
                wayApply.setId(resultSet.getInt(1));
                wayApply.setTitle(resultSet.getString(2));
                list.add(wayApply);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
