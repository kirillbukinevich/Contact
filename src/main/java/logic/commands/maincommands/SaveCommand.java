//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.configuration.ConfigurationManager;
import logic.database.AttachmentDAO;
import logic.database.EmployeeDAO;
import logic.database.PhoneDAO;
import logic.database.PhotoDAO;
import logic.entity.*;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static logic.configuration.LogConfiguration.LOGGER;

public class SaveCommand implements ActionCommand {
    public SaveCommand() {
    }

    public String execute(HttpServletRequest request) {
        this.saveContact(request);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }


    public boolean saveContact(HttpServletRequest request) {
        EmployeeDAO contactDAO = new EmployeeDAO();
        Employee employee = getEmployeeFromSession(request);
        updateEmployee(request, employee);

        savePhones(employee.getPhoneList(),employee.getId());
        saveAttchment(employee);
        if (!employee.getPhoto().isExistInDB() && employee.getPhoto().getPhotoName() != null) {
            savePhoto(employee.getPhoto());
        }
        if (employee.getPhoto().isDeleted()) {
            deletePhotoFromDisk(employee.getPhoto());
        }


        contactDAO.editEmployee(employee);

        contactDAO.saveContact();

        return true;
    }

    public boolean savePhones(ArrayList<ContactPhone> phones,final int EMPLOYEEID){
        Iterator<ContactPhone> phoneIterator = phones.iterator();
        PhoneDAO phoneDAO = new PhoneDAO();
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
        AttachmentDAO attachmentDAO = new AttachmentDAO();
        ArrayList<Attachment> attachments = employee.getAttachmentList();
        String filePath = ConfigurationManager.getPathProperty("path.saveFile") + employee.getId() + "/";
        for (Attachment attachment : attachments) {
            if (!(attachment.isSaved() || attachment.isDeleted() || attachment.isUpdated())) {
                attachmentDAO.addAttachment(attachment);
                String resultFileName = filePath +
                        attachment.getId();
                System.out.println("RESULTFileName: " + resultFileName);
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
                System.out.println("RESULTFileName: " + resultFileName);
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
        PhotoDAO photoDAO = new PhotoDAO();
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
        PhotoDAO photoDAO = new PhotoDAO();
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

    public Employee updateEmployee(HttpServletRequest request, Employee employee) {


        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("last_name"));
        employee.setPatronymic(request.getParameter("patronymic"));

        String[] date = request.getParameter("date_of_birth").split("\\-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        employee.setDateOfBirth(localDate);

        employee.setGender(request.getParameter("gender"));
        employee.setNationality(request.getParameter("nationality"));
        employee.setFamilyStatus(request.getParameter("family_status"));
        employee.setWebSite(request.getParameter("web_site"));
        employee.setEmail(request.getParameter("email"));
        employee.setWorkPlace(request.getParameter("work_place"));
        employee.setAddress(getAddress(request));

        return employee;
    }

    public Address getAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setCountryName(request.getParameter("country").isEmpty() ? null : (request.getParameter("country")));
        address.setCityName(request.getParameter("city").isEmpty() ? null : request.getParameter("city"));
        address.setStreetName(request.getParameter("street").isEmpty() ? null : request.getParameter("street"));
            address.setHouseNumber(request.getParameter("house").isEmpty() ?
                     null : (request.getParameter("house")));
            address.setFlatNumber(request.getParameter("flat").isEmpty() ?
                    0 : Integer.valueOf((request.getParameter("flat"))));
            address.setIndex(request.getParameter("house").isEmpty() ?
                    0 : Integer.valueOf((request.getParameter("index"))));
        return address;
    }

    public Employee getEmployeeFromSession(HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        return employee;
    }


}
