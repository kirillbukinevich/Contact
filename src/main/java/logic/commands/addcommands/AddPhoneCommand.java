package logic.commands.addcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.PhoneDAO;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

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

        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.addPhone(phone, EMPLOYEEID);

        ArrayList<ContactPhone> phoneList =  employee.getPhoneList();
        ContactPhone editPhone = checkEditPhone(request);
        if(editPhone!=null) {
            phoneList.remove(editPhone);
            phoneDAO = new PhoneDAO();
            phoneDAO.deletePhone(editPhone.getId());
        }
        employee.getPhoneList().add(phone);

    }
    public ContactPhone checkEditPhone(HttpServletRequest request){
        ContactPhone editPhone =  (ContactPhone)request.getSession().getAttribute("edit_phone");
        request.getSession().setAttribute("edit_phone",null);
        return editPhone;
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
