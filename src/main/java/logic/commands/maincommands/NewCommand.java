//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.configuration.ConfigurationManager;
import logic.database.AbstractDAO;
import logic.database.EmployeeDAO;
import logic.entity.Employee;
import logic.entity.Photo;
import logic.processcommand.ActionCommand;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NewCommand implements ActionCommand {
    public NewCommand() {
    }

    public String execute(HttpServletRequest request) {
        startCreateContact();
        Employee employee = getNewEmployee();
        setEmployeeToSession(request, employee);
        request.setAttribute("photo", getPhotoForJSP(request, employee.getPhoto()));

        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public void startCreateContact() {
        AbstractDAO contactDAO = new EmployeeDAO();
        contactDAO.startEditContact();
    }

    public void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }

    public Employee getNewEmployee() {
        EmployeeDAO contactDAO = new EmployeeDAO();
        final int ID = contactDAO.getNewEmployeeID();
        Employee employee = new Employee();
        employee.setId(ID);
        Photo photo = new Photo();
        photo.setEmployeeID(ID);
        employee.setPhoto(photo);

        return employee;
    }

    public String getPhotoForJSP(HttpServletRequest request, Photo photo) {
        String resultFileName;
        byte[] data;
        byte[] encodeBase64;
        FileInputStream fileInputStream = null;
        try {
            resultFileName = ConfigurationManager.getProperty("path.defaultPhoto");
            File file = new File(resultFileName);
            fileInputStream = new FileInputStream(file);
            data = IOUtils.toByteArray(fileInputStream);
            encodeBase64 = Base64.encodeBase64(data);
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

}
