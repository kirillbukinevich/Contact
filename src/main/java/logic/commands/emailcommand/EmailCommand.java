package logic.commands.emailcommand;

import logic.commands.maincommands.ContactCommand;
import logic.database.EmployeeDAO;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aefrd on 16.09.2016.
 */
public class EmailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String[] selectedEmployee = request.getParameterValues("check_selected");
        request.setAttribute("lst_mail", getEmails(selectedEmployee));
        String page = "/web/jsp/email/email.jsp";
        return page;
    }
    public StringBuffer getEmails(String[] selectedEmployee){
        StringBuffer emails = new StringBuffer();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        for (int i = 0; i < selectedEmployee.length; i++) {
            emails.append(employeeDAO.getEmail(Integer.parseInt(selectedEmployee[i])) + " ");
        }
        return emails;
    }

}
