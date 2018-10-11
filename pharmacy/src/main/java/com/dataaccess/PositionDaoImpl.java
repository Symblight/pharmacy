package com.dataaccess;

import com.datamodel.Position;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoImpl extends AbstractDao<Position> {
    protected java.sql.Statement stmt;

    public PositionDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
    }

    public void add(Position entity) {
        String query = "INSERT INTO position (position)"
                + " VALUES ('"+entity.getPosition()+"')";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Position entity) {
        String query = "UPDATE position set position='"+entity.getPosition()+"' where idPosition="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Position entity) {
        String sql = "DELETE FROM position WHERE idPosition="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Position> get(int idEntity) {
        String query = "select * from position where idPosition="+idEntity;

        List<Position> positions = new ArrayList<Position>();

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
            positions = this.getList(resultSet, positions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public List<Position> list() {
        String query = "SELECT * FROM position;";
        List<Position> positions = new ArrayList<Position>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            positions = this.getList(resultSet, positions);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positions;
}

    private List<Position> getList(ResultSet resultSet, List<Position> list) {
        try {
            while (resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getInt(1));
                position.setPosition(resultSet.getString(2));
                list.add(position);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
