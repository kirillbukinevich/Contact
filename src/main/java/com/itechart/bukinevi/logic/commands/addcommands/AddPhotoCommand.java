package com.itechart.bukinevi.logic.commands.addcommands;

import com.itechart.bukinevi.logic.domain.Employee;
import com.itechart.bukinevi.logic.domain.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by aefrd on 13.09.2016.
 */
public class AddPhotoCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        Employee employee = SessionUtils.getEmployeeFromSession(request);
        savePhoto(request, employee);
        return "";
    }

    private void savePhoto(HttpServletRequest request, Employee employee) {
        Photo photo = getPhoto(request, employee);
        employee.setPhoto(photo);
    }

    private Photo getPhoto(HttpServletRequest request, Employee employee) {
        Photo photo = new Photo();
        processPhoto(request, photo);
        final int EMPLOYEE_ID = employee.getId();
        photo.setEmployeeID(EMPLOYEE_ID);
        photo.setPhotoName((String) request.getAttribute("photo_name"));
        return photo;
    }

    private void processPhoto(HttpServletRequest request, Photo photo) {
        @SuppressWarnings("unchecked")
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");

        fileItems.stream().filter(fi -> !fi.isFormField()).forEach(fi -> {
            if ("photo".equals(fi.getFieldName())) {
                photo.setBytes(fi.get());
                photo.setDeleted(false);
                photo.setSaved(false);
            }
        });

    }
}
