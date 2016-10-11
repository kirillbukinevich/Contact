package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.*;
import com.itechart.bukinevi.logic.entity.*;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

public class EditCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        Employee employee = getEmployeeOnId(request);
        setEmployeeToSession(request, employee);
        fillAllParameters(request);
        startEditContact();
        return getProperty("path.page.edit");
    }

    public Employee getEmployeeOnId(HttpServletRequest request) {
        Employee employee = new Employee();
        String employee_id = request.getParameter("employee_id");
        final int ID;
        if (employee_id == null) {
            String[] selectedEmpl = request.getParameterValues("check_selected");
            ID = Integer.parseInt(selectedEmpl[0]);
        } else {
            ID = Integer.parseInt(employee_id);
        }
        employee.setId(ID);
        EmployeeDAO contactDAO = new EmployeeDAO();
        employee = contactDAO.getEmployeeOnId(employee.getId());
        AttachmentDAO attachmentDAO = new AttachmentDAO();
        employee.setAttachmentList((ArrayList) attachmentDAO.getAttachmentList(employee.getId()));
        PhoneDAO phoneDAO = new PhoneDAO();
        employee.setPhoneList((ArrayList) phoneDAO.getPhoneList(employee.getId()));
        PhotoDAO photoDAO = new PhotoDAO();
        Photo photo = photoDAO.getPhoto(employee.getId());
        employee.setPhoto(photo);

        return employee;
    }

    public void startEditContact() {
        AbstractDAO abstractDAO = new EmployeeDAO();
        abstractDAO.startEditContact();
    }

    public boolean fillAllParameters(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        this.fillEmployeeParameters(request, employee);
        this.fillAddressParameters(request, employee.getAddress());
        this.fillPhoneParameters(request, employee.getPhoneList());
        this.fillAttachmentParameters(request, employee.getAttachmentList());
        this.fillPhotoParameter(request, employee);
        return true;
    }


    public boolean fillEmployeeParameters(HttpServletRequest request, Employee employee) {
        request.setAttribute("first_name", employee.getFirstName());
        request.setAttribute("last_name", employee.getLastName());
        request.setAttribute("patronymic", employee.getPatronymic());
        request.setAttribute("date_of_birth", employee.getDateOfBirth());
        request.setAttribute("gender", employee.getGender());
        request.setAttribute("nationality", employee.getNationality());
        request.setAttribute("family_status", employee.getFamilyStatus());
        request.setAttribute("web_site", employee.getWebSite());
        request.setAttribute("email", employee.getEmail());
        request.setAttribute("work_place", employee.getWorkPlace());

        return true;
    }

    public boolean fillAddressParameters(HttpServletRequest request, Address address) {
        request.setAttribute("country", address.getCountryName());
        request.setAttribute("city", address.getCityName());

        request.setAttribute("street", address.getStreetName());
        request.setAttribute("house", address.getHouseNumber());
        request.setAttribute("flat", address.getFlatNumber());
        request.setAttribute("index", address.getIndex());
        return true;
    }

    public boolean fillPhoneParameters(HttpServletRequest request, List<ContactPhone> phoneList) {
        request.setAttribute("phoneList", phoneList);
        return true;
    }

    public boolean fillAttachmentParameters(HttpServletRequest request, List<Attachment> attachmentList) {
        String filePath = ConfigurationManager.getPathProperty("path.saveFile");
        request.setAttribute("file_path", filePath);
        request.setAttribute("attachList", attachmentList);
        return true;
    }

    public boolean fillPhotoParameter(HttpServletRequest request, Employee employee) {
        Photo photo = employee.getPhoto();
        request.setAttribute("photo", getPhotoForJSP(request, photo));
        return true;
    }

    public String getPhotoForJSP(HttpServletRequest request, Photo photo) {
        String resultFileName;
        byte[] data;
        byte[] encodeBase64;
        FileInputStream fileInputStream = null;
        try {
            if (photo.getBytes() == null) {
                if (!photo.isSaved() || photo.isDeleted()) {
                    resultFileName = ConfigurationManager.getPathProperty("path.defaultPhoto");
                } else {
                    resultFileName = ConfigurationManager.getPathProperty("path.saveFile") +
                            photo.getEmployeeID() + "/photo/" + photo.getPhotoName();
                }
                File file = new File(resultFileName);
                fileInputStream = new FileInputStream(file);
                data = IOUtils.toByteArray(fileInputStream);
                encodeBase64 = Base64.encodeBase64(data);
            } else {
                encodeBase64 = Base64.encodeBase64(photo.getBytes());
            }

            return new String(encodeBase64, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "default";
    }


    public Employee getEmployeeFromSession(HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        return employee;
    }

    public void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }


}
