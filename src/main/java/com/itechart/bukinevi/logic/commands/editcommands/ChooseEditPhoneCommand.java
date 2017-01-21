package com.itechart.bukinevi.logic.commands.editcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.ContactPhone;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by aefrd on 17.09.2016.
 */
public class ChooseEditPhoneCommand implements ActionCommand{
    private final UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        String page = updateCommand.execute(request);
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        if(selectedPhone==null) {
            this.editPhone(request, Integer.parseInt(request.getParameter("phone_id")));
        }else {
            for (String aSelectedPhone : selectedPhone) {
                this.editPhone(request, Integer.parseInt(aSelectedPhone));
            }
        }
        updateCommand.fillAllParameters(request);
        return page;
    }

    private void editPhone(HttpServletRequest request, final int PHONEID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        ArrayList<ContactPhone> phoneList = employee.getPhoneList();
        ContactPhone editPhone = null;
        for(ContactPhone phone : phoneList){
            if(phone.getId()==PHONEID){
                if(!phone.isUpdated() || !phone.isSaved()) {
                    editPhone = phone;
                    break;
                }
            }
        }
        request.getSession().setAttribute("edit_phone",editPhone);
        assert editPhone != null;
        request.setAttribute("code_country",editPhone.getCodeCountry());
        request.setAttribute("code_operator",editPhone.getCodeOperator());
        request.setAttribute("phone_number",editPhone.getNumber());
        request.setAttribute("phone_type",editPhone.getType());
        request.setAttribute("comment_phone",editPhone.getComment());
        request.setAttribute("type_operation","Edit_phone");
    }
}
