package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.entity.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

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
