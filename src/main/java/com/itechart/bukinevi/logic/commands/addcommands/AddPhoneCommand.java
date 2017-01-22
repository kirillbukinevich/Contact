package com.itechart.bukinevi.logic.commands.addcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.ContactPhone;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 10.09.2016.
 */
public class AddPhoneCommand implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();

    public String execute(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        addPhone(request, employee);
        updateCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }


    private void addPhone(HttpServletRequest request, Employee employee) {
        final int EMPLOYEEID = employee.getId();
        ContactPhone phone = getPhoneFromJSP(request);
        phone.setEmployeeID(EMPLOYEEID);
        employee.getPhoneList().add(phone);

    }

    private ContactPhone getPhoneFromJSP(HttpServletRequest request) {
        ContactPhone phone = new ContactPhone();
        phone.setCodeCountry(Integer.valueOf(request.getParameter("code_country")));
        phone.setCodeOperator(Integer.valueOf(request.getParameter("code_operator")));
        phone.setNumber(Integer.parseInt(request.getParameter("phone_number")));
        phone.setType(request.getParameter("phone_type"));
        phone.setComment(request.getParameter("comment"));
        phone.setId(phone.hashCode());

        return phone;
    }
}
