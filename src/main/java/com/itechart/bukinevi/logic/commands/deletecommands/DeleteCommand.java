
package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.ContactCommand;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

public class DeleteCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String[] selectedEmpl = request.getParameterValues("check_selected");

        for (String aSelectedEmpl : selectedEmpl) {
            this.deleteEmployee(Integer.parseInt(aSelectedEmpl));
        }
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }

    public boolean deleteEmployee(final int ID) {
        EmployeeDAOUtil employeeDAO = new EmployeeDAOUtil();
        employeeDAO.deleteEmployee(ID);
        deleteAttachmentDirectory(ID);
        return true;
    }

    public boolean deleteAttachmentDirectory(final int ID) {
        String path = ConfigurationManager.getProperty("path.saveFile") + ID;
        try {
            FileUtils.deleteDirectory(new File(path));
            LOGGER.info("deleted directory from server");
        } catch (IOException e) {
            LOGGER.error("can't delete directory from server " + e);
        }
        return true;
    }
}
