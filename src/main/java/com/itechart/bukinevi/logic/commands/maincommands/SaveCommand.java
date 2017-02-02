//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.commands.maincommands;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.PhoneDAO;
import com.itechart.bukinevi.logic.database.impl.AttachmentDAOUtil;
import com.itechart.bukinevi.logic.database.impl.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.database.impl.PhoneDAOUtil;
import com.itechart.bukinevi.logic.database.impl.PhotoDAOUtil;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.ContactPhone;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.entity.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SaveCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(SaveCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        this.saveContact(request);
        return "";
    }

    private Employee getEmployeeFromJSON(HttpServletRequest request) {
        System.out.println("!!!!!!!!!!!");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("!" + sb.toString() + "!");
        String jsonEmployee = new String(sb);
        jsonEmployee = jsonEmployee.replaceAll("_", "");
        System.out.println(jsonEmployee);
        Employee employee = null;
        try {
            if (StringUtils.isNotEmpty(sb.toString())) {
                ObjectMapper mapper = new ObjectMapper();
                employee = mapper.readValue(jsonEmployee, Employee.class);
                System.out.println(employee);
            }
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("!!!!!!!!!!!");
        return employee;
    }

    private void saveContact(HttpServletRequest request) {
        Employee employee = getEmployeeFromJSON(request);
        savePhoto(employee.getPhotoName(), request);
        savePhones(employee.getPhoneList(), employee.getId());
        saveAttachment(employee.getAttachmentList(), employee.getId(), request);
        EmployeeDAOUtil contactDAO = new EmployeeDAOUtil();
        System.out.println("HERE");
        contactDAO.editEmployee(employee);
        System.out.println("HERE");
        contactDAO.saveContact();
        System.out.println("HERE");

    }

    private void savePhones(List<ContactPhone> phones, final int EMPLOYEEID) {
        PhoneDAOUtil phoneDAO = new PhoneDAOUtil();
        phoneDAO.deletePhones(getDeletePhoneList(phones, phoneDAO, EMPLOYEEID));
        phoneDAO.insertOrUpdatePhone(phones, EMPLOYEEID);
    }

    @SuppressWarnings("unchecked")
    private List<ContactPhone> getDeletePhoneList(List<ContactPhone> phones, PhoneDAO phoneDAO, final int EMPLOYEEID) {
        List<ContactPhone> oldPhoneList = phoneDAO.getPhoneList(EMPLOYEEID);
        List<ContactPhone> deleteList = new ArrayList<>();
        oldPhoneList.forEach(oldPhone -> {
            boolean isDeletedElement = true;
            for (ContactPhone phone : phones) {
                if (Objects.equals(phone.getId(), oldPhone.getId())) {
                    isDeletedElement = false;
                }
            }
            if (isDeletedElement) {
                deleteList.add(oldPhone);
            }
        });
        return deleteList;

    }

    private void saveAttachment(List<Attachment> attachments, final int EMPLOYEEID, HttpServletRequest request) {
        AttachmentDAOUtil attachmentDAO = new AttachmentDAOUtil();
        attachmentDAO.deleteAttachments(getDeleteAttachmentsList(attachments, request));
        attachmentDAO.insertOrUpdatePhone(getAttachmentListFromSession(request), EMPLOYEEID);

        String filePath = ConfigurationManager.getPathProperty("path.saveFile") + EMPLOYEEID + "/";
        for (Attachment attachment : getAttachmentListFromSession(request)) {
            String resultFileName = filePath +
                    attachment.getId();
            if (attachment.isDeleted()) {
                deleteAttachmentFromDisk(resultFileName);
            }
            if (!(attachment.isSaveOnDisk() || attachment.isDeleted())) {
                try {
                    Path path = Paths.get(resultFileName);
                    Files.write(path, attachment.getAttachment());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Attachment> getAttachmentListFromSession(HttpServletRequest request) {
        return this.getEmployeeFromSession(request).getAttachmentList();
    }

    private List<Attachment> getDeleteAttachmentsList(List<Attachment> attachments, HttpServletRequest
            request) {
        List<Attachment> oldAttachmentList = getAttachmentListFromSession(request);
        List<Attachment> deleteList = new ArrayList<>();
        oldAttachmentList.forEach(oldAttachment -> {
            boolean isDeletedElement = true;
            for (Attachment attachment : attachments) {
                if (Objects.equals(attachment.getId(), oldAttachment.getId())) {
                    isDeletedElement = false;
                }
            }
            if (isDeletedElement) {
                oldAttachment.setDeleted(true);
                deleteList.add(oldAttachment);
            }
        });
        return deleteList;
    }

    private void deleteAttachmentFromDisk(String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            LOGGER.error("can't delete file from server ", e);
        }

    }


    private void savePhoto(String photoName, HttpServletRequest request) {
        Photo photo = this.getEmployeeFromSession(request).getPhoto();
        PhotoDAOUtil photoDAO = new PhotoDAOUtil();
        if (photoName.equals("delete") && photo.getPhotoName().equals(photoName) && photo.isSaved() || StringUtils.isEmpty(photoName)) {
            return;
        }
        System.out.println(photoName.equals("delete"));
        if (photoName.equals("delete")) {
            System.out.println("???????????????");
            deletePhotoFromDisk(photo);
            photo.setPhotoName(null);
            photoDAO.updatePhoto(photo);
            return;
        }
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
            LOGGER.error("can't write photo on disk: ", e);
        }


    }

    private void deletePhotoFromDisk(Photo photo) {
        String resultFileName = ConfigurationManager.getPathProperty("path.saveFile") +
                photo.getEmployeeID() + "/photo/" + photo.getPhotoName();
        deleteAttachmentFromDisk(resultFileName);
    }


}
