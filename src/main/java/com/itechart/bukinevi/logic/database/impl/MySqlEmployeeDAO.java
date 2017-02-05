package com.itechart.bukinevi.logic.database.impl;

import com.itechart.bukinevi.logic.database.AbstractDAO;
import com.itechart.bukinevi.logic.database.EmployeeDAO;
import com.itechart.bukinevi.logic.entity.Address;
import com.itechart.bukinevi.logic.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlEmployeeDAO extends AbstractDAO implements EmployeeDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySqlEmployeeDAO.class);

    private int noOfRecords;

    @Override
    public void editEmployee(Employee employee) {
        this.updatePrepareStatement("UPDATE main_info SET first_name=?,last_name=?,patronymic=?," +
                "date_of_birth=?,gender=?,nationality=?,family_status=?,web_site=?,email=?,work_place=?," +
                "country=?,city=?,street=?,house=?,flat=?,index_address=? WHERE id=?");
        try {
            this.preparedStatement.setString(1, employee.getFirstName());
            this.preparedStatement.setString(2, employee.getLastName());
            this.preparedStatement.setString(3, employee.getPatronymic());
            this.preparedStatement.setDate(4, Date.valueOf(employee.getDateOfBirth()));
            this.preparedStatement.setString(5, employee.getGender());
            this.preparedStatement.setString(6, employee.getNationality());
            this.preparedStatement.setString(7, employee.getFamilyStatus());
            this.preparedStatement.setString(8, employee.getWebSite());
            this.preparedStatement.setString(9, employee.getEmail());
            this.preparedStatement.setString(10, employee.getWorkPlace());
            Address address = employee.getAddress();
            this.preparedStatement.setString(11, address.getCountryName());
            this.preparedStatement.setString(12, address.getCityName());
            this.preparedStatement.setString(13, address.getStreetName());
            this.preparedStatement.setObject(14, address.getHouseNumber());
            this.preparedStatement.setObject(15, address.getFlatNumber());
            this.preparedStatement.setObject(16, address.getIndex());
            this.preparedStatement.setObject(17, employee.getId());
            this.preparedStatement.executeUpdate();

            LOGGER.info("update employee to BD id: {}", employee.getId());
        } catch (SQLException e) {
            LOGGER.error("can't update employee to BD ", e);
        } finally {
            this.closePreparedStatement("editEmplloyee");
        }
    }

    @Override
    public List<String> getBirthdayList() {
        List<String> birthdayList = new LinkedList<>();
        try {
            String query = "SELECT first_name,patronymic,last_name FROM main_info " +
                    "WHERE MONTH(date_of_birth) = MONTH(NOW()) AND DAY(date_of_birth) = DAY(NOW())";
            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                birthdayList.add(resultSet.getString(1) + " " +
                        resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("can't get birthdayList: ", e);
        } finally {
            this.closeStatement("getBirthdayList");

        }
        return birthdayList;
    }

    @Override
    public List<Employee> getEmployeesList(int offset, int recordsPerPage, String criteria, Map<String, String> searchCriteriasMap) {
        String query = "select SQL_CALC_FOUND_ROWS * from main_info "
                + criteria + " limit " + offset + ", " + recordsPerPage;
        List<Employee> list = new ArrayList<>();

        try {
            updatePrepareStatement(query);
            if (searchCriteriasMap != null) {
                Iterator<String> iterator = searchCriteriasMap.values().iterator();
                for (int i = 0; i < searchCriteriasMap.size(); i++) {
                    String s = iterator.next();
                    preparedStatement.setString(i + 1, "%" + s + "%");

                }
            }
            ResultSet rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }

            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPatronymic(rs.getString("patronymic"));
                employee.setDateOfBirth(String.valueOf(rs.getDate("date_of_birth").toLocalDate()));
                employee.setGender(rs.getString("gender"));
                employee.setNationality(rs.getString("nationality"));
                employee.setFamilyStatus(rs.getString("family_status"));
                employee.setWebSite(rs.getString("web_site"));
                employee.setEmail(rs.getString("email"));
                employee.setWorkPlace(rs.getString("work_place"));
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setCountryName(rs.getString("country"));
                address.setCityName(rs.getString("city"));
                address.setStreetName(rs.getString("street"));
                address.setHouseNumber(rs.getString("house"));
                address.setFlatNumber(rs.getInt("flat"));
                address.setIndex(rs.getInt("index_address"));
                employee.setAddress(address);
                list.add(employee);
            }


        } catch (SQLException e) {
            LOGGER.error("can't get employee list ", e);
        }finally {
            this.closePreparedStatement("getEmployeesList");
        }

        return list;
    }

    @Override
    public String getEmail(final int ID) {
        String query = "SELECT email FROM main_info WHERE id = ?";
        String email = new String();
        try {
            updatePrepareStatement(query);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            email = resultSet.getString(1);
        } catch (SQLException e) {
            LOGGER.error("can't get email ", e);
        }finally {
            this.closePreparedStatement("getEmail");
        }
        return email;
    }


    @Override
    public Employee getEmployeeOnId(int ID) {
        String query = "select * from main_info WHERE main_info.id=?";
        updatePrepareStatement(query);
        Employee employee = null;
        try {
            preparedStatement.setInt(1, ID);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                employee = new Employee();
                employee.setId(e.getInt("id"));
                employee.setFirstName(e.getString("first_name"));
                employee.setLastName(e.getString("last_name"));
                employee.setPatronymic(e.getString("patronymic"));
                employee.setDateOfBirth(String.valueOf(e.getDate("date_of_birth").toLocalDate()));
                employee.setGender(e.getString("gender"));
                employee.setNationality(e.getString("nationality"));
                employee.setFamilyStatus(e.getString("family_status"));
                employee.setWebSite(e.getString("web_site"));
                employee.setEmail(e.getString("email"));
                employee.setWorkPlace(e.getString("work_place"));
                Address address = new Address();
                address.setId(e.getInt("id"));
                address.setCountryName(e.getString("country"));
                address.setCityName(e.getString("city"));
                address.setStreetName(e.getString("street"));
                address.setHouseNumber(e.getString("house"));
                address.setFlatNumber(e.getInt("flat"));
                address.setIndex(e.getInt("index_address"));
                employee.setAddress(address);
            }
        } catch (SQLException e) {
            LOGGER.error("can't get employee on id ", e);
        }finally {
            this.closePreparedStatement("getEmployeeOnId");
        }

        return employee;
    }

    @Override
    public int getNewEmployeeID() {
        try {
            this.updatePrepareStatement("INSERT INTO main_info (id) VALUES (NULL)");
            this.preparedStatement.executeUpdate();
            final int EMPLOYEEID = retriveId(preparedStatement);
            this.updatePrepareStatement("INSERT INTO photo (employee_id) VALUES (?)");
            this.preparedStatement.setInt(1, EMPLOYEEID);
            this.preparedStatement.executeUpdate();
            return EMPLOYEEID;
        } catch (SQLException e) {
            LOGGER.error("can't get new employee id ", e);
        } finally {
            this.closePreparedStatement("getNewEmployeeID");
        }
        return -1;
    }


    @Override
    public void deleteEmployee(int ID) {
        String deleteSQL = "DELETE main_info FROM main_info " +
                "WHERE main_info.id = ?";

        try {
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("can't delete employee ", e);
        } finally {
            this.closePreparedStatement("deleteEmployee");
        }
    }

    @Override
    public int getNoOfRecords() {
        return this.noOfRecords;
    }


}
