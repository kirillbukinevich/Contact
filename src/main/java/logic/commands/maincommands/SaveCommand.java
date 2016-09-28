//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.configuration.ConfigurationManager;
import logic.database.EmployeeDAO;
import logic.entity.Address;
import logic.entity.Attachment;
import logic.entity.Employee;
import logic.entity.Photo;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import static logic.configuration.LogConfiguration.LOGGER;

public class SaveCommand implements ActionCommand {
    public SaveCommand() {
    }

    public String execute(HttpServletRequest request) {
        this.saveContact(request);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ContactCommand contactCommand = new ContactCommand();
        String page = contactCommand.execute(request);
        return page;
    }


    public synchronized boolean saveContact(HttpServletRequest request) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = getEmployeeFromSession(request);
        updateEmployee(request, employee);
        saveAttchment(employee);
        if(!employee.getPhoto().isExistInDB()) {
            savePhoto(employee.getPhoto());
        }
        if(employee.getPhoto().isDeleted()){
            deletePhotoFromDisk(employee.getPhoto());
        }


        employeeDAO.editEmployee(employee);

        employeeDAO.saveContact();

        return true;
    }

    public boolean saveAttchment(Employee employee) {
        ArrayList<Attachment> attachments = employee.getAttachmentList();
        String filePath = ConfigurationManager.getProperty("path.saveFile") + employee.getId() + "/";
        for (Attachment attachment : attachments) {
            if (attachment.isDeleted()) {
                String resultFileName = filePath +
                        attachment.getFileName();
                Path path = Paths.get(resultFileName);
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    LOGGER.error("can't delete file from server " + e);
                }
            }
            if (!attachment.isSaved()) {
                String resultFileName = filePath +
                        attachment.getFileName();
                System.out.println("RESULTFileName: " + resultFileName);
                try {
                    Path path = Paths.get(resultFileName);
                    System.out.println("Path: " + path.toString());
                    Files.write(path, attachment.getAttachment());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public boolean savePhoto(Photo photo) {
        String resultFileName = ConfigurationManager.getProperty("path.saveFile") + photo.getEmployeeID() + "/photo/";
        File uploadDir = new File(resultFileName);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        resultFileName += photo.getPhotoName();
        try {
            Path path = Paths.get(resultFileName);
            System.out.println("Path: " + path.toString());
            Files.write(path, photo.getBytes());
        } catch (IOException e) {
            LOGGER.error("can't write photo on disk: " + e);
        }


        return true;
    }
    public boolean deletePhotoFromDisk(Photo photo){
        String resultFileName = ConfigurationManager.getProperty("path.saveFile") +
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
        address.setCountryName(request.getParameter("country"));
        address.setCityName(request.getParameter("city"));
        address.setStreetName(request.getParameter("street"));
        address.setHouseNumber(Integer.parseInt(request.getParameter("house")));
        address.setFlatNumber(Integer.parseInt(request.getParameter("flat")));
        address.setIndex(Integer.parseInt(request.getParameter("index")));

        return address;
    }

    public boolean isNewEmployee(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        if (employee == null) {
            return true;
        } else {

            return false;
        }
    }

    public Employee getEmployeeFromSession(HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        return employee;
    }


}
