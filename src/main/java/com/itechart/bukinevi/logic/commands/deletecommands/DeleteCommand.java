
package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.ContactCommand;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.impl.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class DeleteCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteCommand.class);


    @Override
    public String execute(HttpServletRequest request) {
        String[] selectedEmpl = request.getParameterValues("check_selected");

        for (String aSelectedEmpl : selectedEmpl) {
            this.deleteEmployee(Integer.parseInt(aSelectedEmpl));
        }
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }

    private void deleteEmployee(final int ID) {
        EmployeeDAOUtil employeeDAO = new EmployeeDAOUtil();
        employeeDAO.deleteEmployee(ID);
        deleteAttachmentDirectory(ID);
    }

    private void deleteAttachmentDirectory(final int ID) {
        String path = ConfigurationManager.getProperty("path.saveFile") + ID;
        try {
            FileUtils.deleteDirectory(new File(path));
            LOGGER.info("deleted directory from server");
        } catch (IOException e) {
            LOGGER.error(String.format("can't delete directory from server %s", e));
        }
    }
}
