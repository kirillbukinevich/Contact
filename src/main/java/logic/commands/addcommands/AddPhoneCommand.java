package logic.commands.addcommands;

import logic.commands.UpdateCommand;
import logic.processcommand.ActionCommand;
import logic.commands.maincommands.EditCommand;
import logic.database.EmployeeDAO;
import logic.entity.ContactPhone;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aefrd on 10.09.2016.
 */
public class AddPhoneCommand extends UpdateCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        addPhone(request,employee);
        super.fillAllParameters(request);

        String page = "/web/jsp/addedit.jsp";
        return page;
    }


    public void addPhone(HttpServletRequest request,Employee employee) {
        final int EMPLOYEEID =  employee.getId();
        ContactPhone phone = getPhone(request);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.addPhone(phone, EMPLOYEEID);

        employee.getPhoneList().add(phone);

    }


    public ContactPhone getPhone(HttpServletRequest request) {
        ContactPhone phone = new ContactPhone();
        phone.setCodeCountry(request.getParameter("code_country"));
        phone.setCodeOperator(request.getParameter("code_operator"));
        phone.setNumber(Integer.parseInt(request.getParameter("phone_number")));
        phone.setType(request.getParameter("phone_type"));
        phone.setComment(request.getParameter("comment"));

        return phone;
    }
    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }

}
