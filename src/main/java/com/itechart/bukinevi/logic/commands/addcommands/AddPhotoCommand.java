package com.itechart.bukinevi.logic.commands.addcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.entity.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 13.09.2016.
 */
public class AddPhotoCommand implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        savePhoto(request,employee);
        updateCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }
    private void savePhoto(HttpServletRequest request, Employee employee) {
        Photo photo = getPhoto(request,employee);
        employee.setPhoto(photo);
    }

    private Photo getPhoto(HttpServletRequest request, Employee employee) {
        Photo photo = new Photo();
        processPhoto(request, photo);
        final int EMPLOYEEID =  employee.getId();
        photo.setEmployeeID(EMPLOYEEID);
        return photo;
    }

    private void processPhoto(HttpServletRequest request, Photo photo) {
        List<FileItem> fileItems = (List<FileItem>)request.getAttribute("file_item");
        fileItems.stream().filter(fi -> !fi.isFormField()).forEach(fi -> {
            String fileName = fi.getName();
            photo.setPhotoName(fileName);
            photo.setBytes(fi.get());
            photo.setDeleted(false);
            photo.setSaved(false);
        });
    }
}
