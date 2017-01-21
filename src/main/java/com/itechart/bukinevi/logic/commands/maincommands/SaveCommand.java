//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.impl.AttachmentDAOUtil;
import com.itechart.bukinevi.logic.database.impl.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.database.impl.PhoneDAOUtil;
import com.itechart.bukinevi.logic.database.impl.PhotoDAOUtil;
import com.itechart.bukinevi.logic.entity.*;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;


public class SaveCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(SaveCommand.class);

    private final UpdateCommand updateCommand = new UpdateCommand();

    public SaveCommand() {
    }

    public String execute(HttpServletRequest request) {
        this.saveContact(request);
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }


    private void saveContact(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        updateCommand.updateEmployee(request, employee);
        savePhones(employee.getPhoneList(),employee.getId());
        saveAttchment(employee);
        if (!employee.getPhoto().isSaved() && employee.getPhoto().getPhotoName() != null) {
            savePhoto(employee.getPhoto());
        }
        if (employee.getPhoto().isDeleted()) {
            deletePhotoFromDisk(employee.getPhoto());
        }

        EmployeeDAOUtil contactDAO = new EmployeeDAOUtil();
        contactDAO.editEmployee(employee);
        contactDAO.saveContact();

    }

    private void savePhones(ArrayList<ContactPhone> phones, final int EMPLOYEEID){
        Iterator<ContactPhone> phoneIterator = phones.iterator();
        PhoneDAOUtil phoneDAO = new PhoneDAOUtil();
        while (phoneIterator.hasNext()){
            ContactPhone phone = phoneIterator.next();
            if(!(phone.isSaved() || phone.isDeleted() || phone.isUpdated())){
                phoneDAO.addPhone(phone,EMPLOYEEID);
            }
            if(!phone.isSaved() && phone.isUpdated()){
                phoneDAO.updatePhone(phone);
            }
            if(phone.isDeleted() && !phone.isUpdated()){
                phoneDAO.deletePhone(phone.getId());
            }
        }
    }

    private void saveAttchment(Employee employee) {
        AttachmentDAOUtil attachmentDAO = new AttachmentDAOUtil();
        ArrayList<Attachment> attachments = employee.getAttachmentList();
        String filePath = ConfigurationManager.getPathProperty("path.saveFile") + employee.getId() + "/";
        for (Attachment attachment : attachments) {
            if (!(attachment.isSaved() || attachment.isDeleted() || attachment.isUpdated())) {
                attachmentDAO.addAttachment(attachment);
                String resultFileName = filePath +
                        attachment.getId();
                try {
                    Path path = Paths.get(resultFileName);
                    Files.write(path, attachment.getAttachment());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (attachment.isDeleted() && !attachment.isUpdated()) {
                attachmentDAO.deleteAttachment(attachment.getId());
                String resultFileName = filePath +
                        attachment.getId();
                Path path = Paths.get(resultFileName);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    LOGGER.error(String.format("can't delete file from server %s", e));
                }
            }
            if(!attachment.isSaved() && attachment.isUpdated()){
                attachmentDAO.updateAttachment(attachment);
                String resultFileName = filePath +
                        attachment.getId();
                try {
                    Path path = Paths.get(resultFileName);
                    Files.write(path, attachment.getAttachment());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void savePhoto(Photo photo) {
        PhotoDAOUtil photoDAO = new PhotoDAOUtil();
        photoDAO.updatePhoto(photo);
        String resultFileName = ConfigurationManager.getPathProperty("path.saveFile") + photo.getEmployeeID() + "/photo/";
        File uploadDir = new File(resultFileName);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        resultFileName += photo.getPhotoName();
        try {
            Path path = Paths.get(resultFileName);
            Files.write(path, photo.getBytes());
        } catch (IOException e) {
            LOGGER.error(String.format("can't write photo on disk: %s", e));
        }


    }

    private void deletePhotoFromDisk(Photo photo) {
        PhotoDAOUtil photoDAO = new PhotoDAOUtil();
        photoDAO.updatePhoto(photo);
        String resultFileName = ConfigurationManager.getPathProperty("path.saveFile") +
                photo.getEmployeeID() + "/photo/" + photo.getPhotoName();
        Path path = Paths.get(resultFileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.error(String.format("can't delete photo from server %s", e));
        }
    }
}
