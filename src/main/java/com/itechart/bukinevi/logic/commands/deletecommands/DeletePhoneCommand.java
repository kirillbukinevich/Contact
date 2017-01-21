package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.ContactPhone;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 10.09.2016.
 */
public class DeletePhoneCommand implements ActionCommand{
    private final UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        for (String aSelectedPhone : selectedPhone) {
            this.deletePhone(request, Integer.parseInt(aSelectedPhone));
        }
        updateCommand.fillAllParameters(request);

        return getProperty("path.page.edit");
    }

    private void deletePhone(HttpServletRequest request, final int PHONEID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        List<ContactPhone> phoneList = employee.getPhoneList();
        phoneList.stream().filter(phone -> phone.getId() == PHONEID).forEach(phone -> {
            phone.setIsDeleted(true);

        });
    }
}
