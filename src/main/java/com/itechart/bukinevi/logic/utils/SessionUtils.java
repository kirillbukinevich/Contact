package com.itechart.bukinevi.logic.utils;

import com.itechart.bukinevi.logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public class SessionUtils {
    public void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }

    public Employee getEmployeeFromSession(HttpServletRequest request) {
        return (Employee) request.getSession().getAttribute("employee");
    }
}
