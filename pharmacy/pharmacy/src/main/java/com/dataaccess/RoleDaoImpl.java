package com.dataaccess;

import com.datamodel.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao<Role> {
    protected java.sql.Statement stmt;

    public RoleDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Role entity) {
        String query = "INSERT INTO role (role, title, password)"
                + " VALUES ('"+entity.getRole()+"', '"+entity.getPassword()+", '"+entity.getTitle()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Role entity) {
        String query = "UPDATE role set role='"+entity.getRole()+"', password='"+entity.getPassword()+"', title='"+entity.getTitle()+"' where idrole="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Role entity) {
        String sql = "DELETE FROM role WHERE idrole="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Role> get(int idEntity) {
        String query = "select * from role where idrole="+idEntity;

        List<Role> roles = new ArrayList<Role>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            roles = this.getList(resultSet, roles);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public List<Role> list() {
        String query = "SELECT * FROM role;";
        List<Role> roles = new ArrayList<Role>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            roles = this.getList(resultSet, roles);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    private List<Role> getList(ResultSet resultSet, List<Role> list) {
        try {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setTitle(resultSet.getString(2));
                role.setRole(resultSet.getString(3));
                role.setPassword(resultSet.getString(4));

                list.add(role);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
