//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.entity.Employee;
import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;

public class NewCommand implements ActionCommand {
    public NewCommand() {
    }

    public String execute(HttpServletRequest request) {
        startCreateContact();
        Employee employee = getNewEmployee();
        setEmployeeToSession(request, employee);

        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public void startCreateContact() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.startEditContact();
    }
    public void setEmployeeToSession(HttpServletRequest request,Employee employee){
        request.getSession().setAttribute("employee",employee);
    }

    public Employee getNewEmployee(){
        EmployeeDAO employeeDAO= new EmployeeDAO();
        final int ID = employeeDAO.getNewEmployeeID();
        Employee employee = new Employee();
        employee.setId(ID);
        return employee;
    }


}
