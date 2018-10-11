package com.dataaccess;

import com.datamodel.ModeRelease;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModeReleaseDaoImpl extends AbstractDao<ModeRelease> {
    protected java.sql.Statement stmt;

    public ModeReleaseDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(ModeRelease entity) {
        String query = "INSERT INTO moderelease (title)"
                + " VALUES ('"+entity.getTitle()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(ModeRelease entity) {
        String query = "UPDATE moderelease set title='"+entity.getTitle()+"' where idModeRelease="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(ModeRelease entity) {
        String sql = "DELETE FROM moderelease WHERE idModeRelease="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ModeRelease> get(int idEntity) {
        String query = "select * from moderelease where idModeRelease="+idEntity;

        List<ModeRelease> modeReleases = new ArrayList<ModeRelease>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            modeReleases = this.getList(resultSet, modeReleases);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modeReleases;
    }

    public List<ModeRelease> list()
    {
        String query = "SELECT * FROM moderelease;";
        List<ModeRelease> modeReleaseList = new ArrayList<ModeRelease>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            modeReleaseList = this.getList(resultSet, modeReleaseList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeReleaseList;
    }

    private List<ModeRelease> getList(ResultSet resultSet, List<ModeRelease> list) {
        try {
            while (resultSet.next()) {
                ModeRelease modeRelease = new ModeRelease();
                modeRelease.setId(resultSet.getInt(1));
                modeRelease.setTitle(resultSet.getString(2));
                list.add(modeRelease);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
