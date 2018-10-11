package com.dataaccess;

import com.datamodel.Education;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationDaoImpl extends AbstractDao<Education> {
    protected java.sql.Statement stmt;

    public EducationDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Education entity) {
        String query = "INSERT INTO education (modeEducation)"
                + " VALUES ('"+entity.getModeEducation()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Education entity) {
        String query = "UPDATE education set modeEducation='"+entity.getModeEducation()+"' where idEducation="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Education entity) {
        String sql = "DELETE FROM education WHERE idEducation="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Education> get(int idEntity) {

        String query = "select * from education where idEducation="+idEntity;

        List<Education> educationList = new ArrayList<Education>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            educationList = this.getList(resultSet, educationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return educationList;
    }

    public List<Education> list()
    {
        String query = "SELECT * FROM education;";
        List<Education> educationList = new ArrayList<Education>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            educationList = this.getList(resultSet, educationList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return educationList;
    }

    private List<Education> getList(ResultSet resultSet, List<Education> list) {
        try {
            while (resultSet.next()) {
                Education education = new Education();
                education.setId(resultSet.getInt(1));
                education.setModeEducation(resultSet.getString(2));
                list.add(education);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
