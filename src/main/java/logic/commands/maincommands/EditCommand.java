
package logic.commands.maincommands;

import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;
import logic.entity.*;
import logic.processcommand.ConfigurationManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class EditCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {

        Employee employee = getEmployeeOnId(request);
        setEmployeeToSession(request,employee);
        String page = "";
        if (this.fillAllParameters(request)) {
            startEditContact();
            page = "/web/jsp/addedit.jsp";
        }

        return page;
    }
    public Employee getEmployeeOnId(HttpServletRequest request){
        Employee employee = new Employee();
        String employee_id = request.getParameter("employee_id");
        final int ID;
        if(employee_id==null){
            String[] selectedEmpl = request.getParameterValues("check_selected");
            ID = Integer.parseInt(selectedEmpl[0]);
        }else {
            ID = Integer.parseInt(employee_id);
        }
        employee.setId(ID);
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employee = employeeDAO.getEmployeeOnId(employee.getId());
        return employee;
    }

//    public String[] getAllImageNames(ArrayList<Attachment> attachments) {
//        for (Attachment attachment : attachments) {
//            File file = new File(attachment.getFilePath());
//
//        }
//        File[] files = file.listFiles();
//        String[] nameOfPictures = new String[files.length];
//        for (int i = 0; i < nameOfPictures.length; i++) {
//            nameOfPictures[i] = files[i].getName();
//        }
//        return nameOfPictures;
//    }

    public void startEditContact() {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.startEditContact();
    }

    public boolean fillAllParameters(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        this.fillEmployeeParameters(request, employee);
        this.fillAddressParameters(request, employee.getAddress());
        this.fillPhoneParameters(request,employee.getPhoneList());
        this.fillAttachmentParameters(request,employee.getAttachmentList());
        this.fillPhotoParameter(request,employee);
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
        System.out.println("attachmentList: " + attachmentList );
        request.setAttribute("attachList", attachmentList);
        return true;
    }
    public boolean fillPhotoParameter(HttpServletRequest request, Employee employee){
        String photo = employee.getPhoto();
        if(photo==null){
            photo = ConfigurationManager.getProperty("path.defaultPhoto");
        }else {
            photo = ConfigurationManager.getProperty("path.saveFile") + photo;
        }
        request.setAttribute("photo",getPhotoForJSP(request,photo));
        return true;
    }

    public String getPhotoForJSP(HttpServletRequest request,String photo){
        String resultFileName = photo;

        try {
            File file = new File(resultFileName);
            System.out.println("ABSOL: " + file.getAbsolutePath());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = IOUtils.toByteArray(fileInputStream);
            byte[] encodeBase64 = Base64.encodeBase64(data);
            return new String(encodeBase64, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFileName;
    }


    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }
    public void setEmployeeToSession(HttpServletRequest request,Employee employee){
        request.getSession().setAttribute("employee",employee);
    }



}
