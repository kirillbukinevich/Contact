package com.itechart.bukinevi.logic.commands.emailcommand;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.EmployeeDAO;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aefrd on 16.09.2016.
 */
public class EmailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String[] selectedEmployee = request.getParameterValues("check_selected");
        request.setAttribute("lst_mail", getEmailsFromBD(selectedEmployee));
        return ConfigurationManager.getProperty("path.page.email");
    }
    public StringBuilder getEmailsFromBD(String[] selectedEmployee){
        StringBuilder emails = new StringBuilder();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        for (int i = 0; i < selectedEmployee.length; i++) {
            emails.append(employeeDAO.getEmail(Integer.parseInt(selectedEmployee[i])) + " ");
        }
        return emails;
    }

}
