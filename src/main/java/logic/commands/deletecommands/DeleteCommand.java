//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.deletecommands;

import logic.commands.maincommands.ContactCommand;
import logic.configuration.ConfigurationManager;
import logic.database.AttachmentDAO;
import logic.database.EmployeeDAO;
import logic.database.PhoneDAO;
import logic.database.PhotoDAO;
import logic.processcommand.ActionCommand;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static logic.configuration.LogConfiguration.LOGGER;

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

    public boolean deleteEmployee(final int ID) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deleteEmployee(ID);
        deleteAttachmentDirectory(ID);
        return true;
    }

    public boolean deleteAttachmentDirectory(final int ID) {
        String path = ConfigurationManager.getProperty("path.saveFile") + ID;
        try {
            FileUtils.deleteDirectory(new File(path));

        } catch (IOException e) {
            LOGGER.error("can't delete directory from server " + e);
        }
        return true;
    }
}
