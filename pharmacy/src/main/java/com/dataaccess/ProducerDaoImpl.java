package com.dataaccess;

import com.datamodel.Producer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerDaoImpl extends AbstractDao<Producer> {
    protected java.sql.Statement stmt;

    public ProducerDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Producer entity) {
        String query = "INSERT INTO producer (title, address, dateCreate, numberPhone, email)"
                + " VALUES ('"+entity.getTitle()+"','"+entity.getAddress()+"','"+entity.getDateCreate()+"', '"+entity.getNumberPhone()+"', '"+entity.getEmail()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Producer entity) {
        String query = "UPDATE producer set title='"+entity.getTitle()+"', address='"+entity.getAddress()+"', dateCreate='"+entity.getDateCreate()+"', numberPhone='"+entity.getNumberPhone()+"'," +
                " email='"+entity.getEmail()+"' where idProducer="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Producer entity) {
        String sql = "DELETE FROM producer WHERE idProducer="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producer> get(int idProducer)
    {
        String query = "select * from producer where idProducer="+idProducer;

        List<Producer> producers = new ArrayList<Producer>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            producers = this.getList(resultSet, producers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;
    }

    public List<Producer> list()
    {
        String query = "SELECT * FROM producer;";
        List<Producer> producers = new ArrayList<Producer>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            producers = this.getList(resultSet, producers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producers;
    }

    private List<Producer> getList(ResultSet resultSet, List<Producer> list) {
        try {
            while (resultSet.next()) {
                Producer producer = new Producer();
                producer.setId(resultSet.getInt(1));
                producer.setTitle(resultSet.getString(2));
                producer.setAddress(resultSet.getString(3));
                producer.setDateCreate(resultSet.getDate(4));
                producer.setNumberPhone(resultSet.getString(5));
                producer.setEmail(resultSet.getString(6));

                list.add(producer);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
