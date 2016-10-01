package logic.commands.editcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.PhoneDAO;
import logic.entity.ContactPhone;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditPhoneCommand extends UpdateCommand{
    public String execute(HttpServletRequest request) {
        updatePhone(request);
        super.fillAllParameters(request);

        String page = "/web/jsp/addedit.jsp";
        return page;
    }


    public void updatePhone(HttpServletRequest request) {
        ContactPhone editPhone = checkEditPhone(request);

        ArrayList<ContactPhone> phoneList =  getEmployeeFromSession(request).getPhoneList();
        phoneList.remove(editPhone);

        ContactPhone phone = getPhoneFromJSP(request, editPhone);

        PhoneDAO phoneDAO = new PhoneDAO();
        phoneDAO.updatePhone(phone);

        phoneList.add(phone);

    }
    public ContactPhone checkEditPhone(HttpServletRequest request){
        ContactPhone editPhone =  (ContactPhone)request.getSession().getAttribute("edit_phone");
        request.getSession().setAttribute("edit_phone",null);
        return editPhone;
    }


    public ContactPhone getPhoneFromJSP(HttpServletRequest request, ContactPhone phone) {
        phone.setCodeCountry(Integer.valueOf(request.getParameter("code_country")));
        phone.setCodeOperator(Integer.valueOf(request.getParameter("code_operator")));
        phone.setNumber(Integer.parseInt(request.getParameter("phone_number")));
        phone.setType(request.getParameter("phone_type"));
        phone.setComment(request.getParameter("comment"));
        return phone;
    }
}
