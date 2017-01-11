//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.AttachmentDAOUtil;
import com.itechart.bukinevi.logic.database.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.database.PhoneDAOUtil;
import com.itechart.bukinevi.logic.database.PhotoDAOUtil;
import com.itechart.bukinevi.logic.entity.*;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

public class SaveCommand implements ActionCommand {
    private UpdateCommand updateCommand = new UpdateCommand();
    public SaveCommand() {
    }

    public String execute(HttpServletRequest request) {
        this.saveContact(request);
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }


    public boolean saveContact(HttpServletRequest request) {
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

        return true;
    }

    public boolean savePhones(ArrayList<ContactPhone> phones,final int EMPLOYEEID){
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
        return true;
    }

    public boolean saveAttchment(Employee employee) {
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
                    LOGGER.error("can't delete file from server " + e);
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
        return true;
    }

    public boolean savePhoto(Photo photo) {
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
            LOGGER.error("can't write photo on disk: " + e);
        }


        return true;
    }

    public boolean deletePhotoFromDisk(Photo photo) {
        PhotoDAOUtil photoDAO = new PhotoDAOUtil();
        photoDAO.updatePhoto(photo);
        String resultFileName = ConfigurationManager.getPathProperty("path.saveFile") +
                photo.getEmployeeID() + "/photo/" + photo.getPhotoName();
        Path path = Paths.get(resultFileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.error("can't delete photo from server " + e);
        }
        return true;
    }
}
