package com.dataaccess;

import com.App;
import com.datamodel.Education;
import com.datamodel.Employee;
import com.datamodel.Position;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.App.dbWorker;

public class EmployeeDaoImpl extends AbstractDao<Employee> {
    protected java.sql.Statement stmt;
    public Statement stmtEducation;
    public Statement stmtPosition;

    public EmployeeDaoImpl(java.sql.Statement stmt) {
        this.stmt = stmt;
        this.stmtEducation = stmt;
        this.stmtPosition = stmt;
    }

    public EmployeeDaoImpl() {

    }

    public void add(Employee entity) {
        String query = "INSERT INTO employee (lastName, firstName, middleName, address, numberPhone, idEducationEmployee, idPositionEmployee, startWork, salary)"
                + " VALUES ('"+entity.getLastName()+"', '"+entity.getFirstName()+"', '"+entity.getMiddleName()+"', '"+entity.getAddress()+"', '"+entity.getNumberPhone()+"', "+entity.getEducation().getId()+", " +
                ""+entity.getPosition().getId()+", '"+entity.getStartWork()+"', "+entity.getSalary()+")";
        try {
            stmt.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit(Employee entity) {
        String query = "UPDATE employee set lastName='"+entity.getLastName()+"', firstName='"+entity.getFirstName()+"', middleName='"+entity.getMiddleName()+"'," +
                "address='"+entity.getAddress()+"', numberPhone='"+entity.getNumberPhone()+"', idEducationEmployee="+entity.getEducation().getId()+"," +
                "idPositionEmployee="+entity.getPosition().getId()+", startWork='"+entity.getStartWork()+"', salary="+entity.getSalary()+" where idEmployee="+entity.getId()+"";
        try {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Employee entity) {
        String sql = "DELETE FROM employee WHERE idEmployee="+entity.getId()+"";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> get(int idEntity) {
        String query = "SELECT * FROM employee where idEmployee="+idEntity+"";
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            employeeList = this.getList(resultSet, employeeList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public List<Employee> list() {
        String query = "SELECT * FROM employee;";
        List<Employee> employeeList = new ArrayList<Employee>();

        try {
            ResultSet resultSet = stmt.executeQuery(query);
            employeeList = this.getList(resultSet, employeeList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    private List<Employee> getList(ResultSet resultSet, List<Employee> list) {
        try {
            stmtEducation = dbWorker.getConnection().createStatement();
            stmtPosition = dbWorker.getConnection().createStatement();
        } catch (SQLException e) {
        }

        EducationDaoImpl educationDao = new EducationDaoImpl(stmtEducation);
        PositionDaoImpl positionDao = new PositionDaoImpl(stmtPosition);

        try {
            while (resultSet.next()) {

                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setLastName(resultSet.getString(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setMiddleName(resultSet.getString(4));
                employee.setAddress(resultSet.getString(5));
                employee.setNumberPhone(resultSet.getString(6));
                employee.setEducation(educationDao.get(resultSet.getInt(7)).get(0));
                employee.setPosition(positionDao.get(resultSet.getInt(8)).get(0));
                employee.setStartWork(resultSet.getDate(9));
                employee.setSalary(Double.parseDouble(resultSet.getString(10)));

                list.add(employee);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
