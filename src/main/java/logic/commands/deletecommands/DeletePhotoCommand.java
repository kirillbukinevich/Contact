package logic.commands.deletecommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.Employee;
import logic.entity.Photo;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static logic.configuration.ConfigurationManager.getProperty;

public class DeletePhotoCommand implements ActionCommand{
    private UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {

        deletePhoto(request);
        updateCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }

    public boolean deletePhoto(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        Photo photo = employee.getPhoto();
        photo.setDeleted(true);
        return true;
    }
}
