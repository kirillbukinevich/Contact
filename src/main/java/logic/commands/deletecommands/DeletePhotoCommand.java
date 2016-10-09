package logic.commands.deletecommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.PhotoDAO;
import logic.entity.Employee;
import logic.entity.Photo;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static logic.configuration.ConfigurationManager.getProperty;

public class DeletePhotoCommand extends UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {

        deletePhoto(request);
        super.fillAllParameters(request);
        return getProperty("path.page.edit");
    }

    public boolean deletePhoto(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        Photo photo = employee.getPhoto();
        photo.setDeleted(true);
        return true;
    }
}
