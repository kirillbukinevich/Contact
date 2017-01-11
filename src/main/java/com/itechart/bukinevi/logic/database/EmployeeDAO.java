package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Address;
import com.itechart.bukinevi.logic.entity.Employee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

 interface EmployeeDAO{
     boolean editEmployee(Employee employee);

     List<String> getBirthdayList();

     List<Employee> getEmployeesList(int offset, int recordsPerPage, String criteria, HashMap<String, String> searchCriteriasMap);

     String getEmail(final int ID);


     Employee getEmployeeOnId(int ID);

     int getNewEmployeeID();


     boolean deleteEmployee(int ID);

     int getNoOfRecords();


}
