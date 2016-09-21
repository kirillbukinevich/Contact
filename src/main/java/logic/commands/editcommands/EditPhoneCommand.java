package logic.commands.editcommands;

import logic.commands.UpdateCommand;
import logic.commands.maincommands.EditCommand;
import logic.database.EmployeeDAO;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aefrd on 17.09.2016.
 */
public class EditPhoneCommand extends UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        String page = super.execute(request);
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        for (String aSelectedPhone : selectedPhone) {
            this.editPhone(request, Integer.parseInt(aSelectedPhone));
        }

        return page;
    }

    public boolean editPhone(HttpServletRequest request,final int PHONEID) {
        Employee employee = getEmployeeFromSession(request);
        ArrayList<ContactPhone> phoneList = employee.getPhoneList();
        ContactPhone editPhone = null;
        for(ContactPhone phone : phoneList){
            if(phone.getId()==PHONEID){
                editPhone = phone;
                break;
            }
        }
        request.getSession().setAttribute("edit_phone",editPhone);
        request.setAttribute("code_country",editPhone.getCodeCountry());
        request.setAttribute("code_operator",editPhone.getCodeOperator());
        request.setAttribute("phone_number",editPhone.getNumber());
        request.setAttribute("phone_type",editPhone.getType());
        request.setAttribute("comment_phone",editPhone.getComment());
        request.setAttribute("type_operation","Edit phone");
        return true;
    }


    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }
}
