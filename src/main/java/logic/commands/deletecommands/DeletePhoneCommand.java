package logic.commands.deletecommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 10.09.2016.
 */
public class DeletePhoneCommand implements ActionCommand{
    private UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        String[] selectedPhone = request.getParameterValues("check_selected_phone");
        for (String aSelectedPhone : selectedPhone) {
            this.deletePhone(request, Integer.parseInt(aSelectedPhone));
        }
        updateCommand.fillAllParameters(request);

        return getProperty("path.page.edit");
    }

    public boolean deletePhone(HttpServletRequest request,final int PHONEID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        List<ContactPhone> phoneList = employee.getPhoneList();
        for(ContactPhone phone : phoneList){
            if(phone.getId()==PHONEID){
                phone.setIsDeleted(true);

            }
        }
        return true;
    }
}
