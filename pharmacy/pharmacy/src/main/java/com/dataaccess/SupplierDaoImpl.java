package com.dataaccess;

import com.datamodel.Supplier;
import com.datamodel.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl extends AbstractDao<Supplier> {
    protected java.sql.Statement stmt;

    public SupplierDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Supplier entity) {
        String query = "INSERT INTO supplier (title, agent, position, address, numberPhone, email)"
                + " VALUES ('"+entity.getTitle()+"','"+entity.getAgent()+"', '"+entity.getPosition()+"', '"+entity.getAddress()+"', '"+entity.getNumberPhone()+"', '"+entity.getEmail()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Supplier entity) {
        String query = "UPDATE supplier set title='"+entity.getTitle()+"', agent='"+entity.getAgent()+"', position='"+entity.getPosition()+"'," +
                " address='"+entity.getAddress()+"', numberPhone='"+entity.getNumberPhone()+"', email='"+entity.getEmail()+"' where idSupplier="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Supplier entity) {
        String sql = "DELETE FROM supplier WHERE idSupplier="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> get(int idEntity) {

        String query = "select * from supplier where idSupplier="+idEntity;

        List<Supplier> suppliers = new ArrayList<Supplier>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            suppliers = this.getList(resultSet, suppliers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public List<Supplier> list()
    {
        String query = "SELECT * FROM supplier;";
        List<Supplier> suppliers = new ArrayList<Supplier>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            suppliers = this.getList(resultSet, suppliers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    private List<Supplier> getList(ResultSet resultSet, List<Supplier> list) {
        try {
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt(1));
                supplier.setTitle(resultSet.getString(2));
                supplier.setAgent(resultSet.getString(3));
                supplier.setPosition(resultSet.getString(4));
                supplier.setAddress(resultSet.getString(5));
                supplier.setNumberPhone(resultSet.getString(6));
                supplier.setEmail(resultSet.getString(7));

                list.add(supplier);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
