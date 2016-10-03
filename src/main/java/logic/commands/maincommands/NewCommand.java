//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.database.AbstractDAO;
import logic.database.EmployeeDAO;
import logic.entity.Employee;
import logic.entity.Photo;
import logic.processcommand.ActionCommand;

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
        AbstractDAO contactDAO = new EmployeeDAO();
        contactDAO.startEditContact();
    }
    public void setEmployeeToSession(HttpServletRequest request,Employee employee){
        request.getSession().setAttribute("employee",employee);
    }

    public Employee getNewEmployee(){
        EmployeeDAO contactDAO = new EmployeeDAO();
        final int ID = contactDAO.getNewEmployeeID();
        Employee employee = new Employee();
        employee.setId(ID);
        Photo photo = new Photo();
        photo.setEmployeeID(ID);
        employee.setPhoto(photo);
        return employee;
    }


}
