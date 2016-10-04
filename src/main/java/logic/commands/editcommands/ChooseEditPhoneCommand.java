package logic.commands.editcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by aefrd on 17.09.2016.
 */
public class ChooseEditPhoneCommand extends UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
//        String page = super.execute(request);
        super.chooseDialog(request);
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        System.out.println(selectedPhone);
        if(selectedPhone==null) {
            this.editPhone(request, Integer.parseInt(request.getParameter("phone_id")));
        }else {
            for (String aSelectedPhone : selectedPhone) {
                this.editPhone(request, Integer.parseInt(aSelectedPhone));
            }
        }
        super.fillAllParameters(request);
        return "/web/jsp/addedit.jsp";
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
        request.setAttribute("type_operation","Edit_phone");
        return true;
    }

}
