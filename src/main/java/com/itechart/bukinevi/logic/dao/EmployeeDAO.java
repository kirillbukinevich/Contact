package com.itechart.bukinevi.logic.dao;

import com.itechart.bukinevi.logic.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    void editEmployee(Employee employee);

    List<String> getBirthdayList();

    List<Employee> getEmployeesList(int offset, int recordsPerPage, String criteria, Map<String, String> searchCriteriasMap);

    String getEmail(final int ID);


    Employee getEmployeeOnId(int ID);

    int getNewEmployeeID();


    void deleteEmployee(int ID);

    int getNoOfRecords();


}
