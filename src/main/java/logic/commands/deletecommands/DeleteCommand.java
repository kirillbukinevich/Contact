//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.deletecommands;

import logic.processcommand.ActionCommand;
import logic.commands.maincommands.ContactCommand;
import logic.database.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;

public class DeleteCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String[] selectedEmpl = request.getParameterValues("check_selected");

        for (String aSelectedEmpl : selectedEmpl) {
            this.deleteEmployee(Integer.parseInt(aSelectedEmpl));
        }
        ContactCommand contactCommand = new ContactCommand();
        String page = contactCommand.execute(request);
        return page;
    }

    public void deleteEmployee(int ID) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deleteEmployee(ID);
    }
}
