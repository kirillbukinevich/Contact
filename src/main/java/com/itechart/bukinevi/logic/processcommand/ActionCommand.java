package com.itechart.bukinevi.logic.processcommand;

import com.itechart.bukinevi.logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request);

    default void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }

}
