package logic.commands.emailcommand;

import logic.database.EmployeeDAO;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aefrd on 16.09.2016.
 */
public class EmailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String[] selectedEmployee = request.getParameterValues("check_selected");
        request.setAttribute("lst_mail", getEmailsFromBD(selectedEmployee));
        String page = "/web/jsp/email.jsp";
        return page;
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
