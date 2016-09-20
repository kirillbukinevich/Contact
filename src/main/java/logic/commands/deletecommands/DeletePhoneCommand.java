package logic.commands.deletecommands;

import logic.processcommand.ActionCommand;
import logic.commands.maincommands.EditCommand;
import logic.database.EmployeeDAO;
import logic.entity.ContactPhone;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by aefrd on 10.09.2016.
 */
public class DeletePhoneCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        for (String aSelectedPhone : selectedPhone) {
            this.deletePhone(request, Integer.parseInt(aSelectedPhone));
        }
        fillAllParameters(request);

        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public void fillAllParameters(HttpServletRequest request){
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);

    }

    public boolean deletePhone(HttpServletRequest request,final int PHONEID) {
        Employee employee = getEmployeeFromSession(request);
        List<ContactPhone> phoneList = employee.getPhoneList();
        ContactPhone removePhone = null;
        for(ContactPhone phone : phoneList){
            if(phone.getId()==PHONEID){
                removePhone = phone;
            }
        }
        employee.getPhoneList().remove(removePhone);
        deletePhoneFromDB(PHONEID);
        return true;
    }


    public boolean deletePhoneFromDB(final int PHONEID){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deletePhone(PHONEID);
        return true;
    }


    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }

}
