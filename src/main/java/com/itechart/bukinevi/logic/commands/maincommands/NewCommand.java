//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.AbstractDAO;
import com.itechart.bukinevi.logic.database.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.entity.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

public class NewCommand implements ActionCommand {
    public NewCommand() {
    }

    public String execute(HttpServletRequest request) {
        startCreateContact();
        Employee employee = getNewEmployee();
        setEmployeeToSession(request, employee);
        request.setAttribute("photo", getPhotoForJSP());

        return getProperty("path.page.edit");
    }

    public void startCreateContact() {
        AbstractDAO contactDAO = new EmployeeDAOUtil();
        contactDAO.startEditContact();
    }

    public void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }

    public Employee getNewEmployee() {
        EmployeeDAOUtil contactDAO = new EmployeeDAOUtil();
        final int ID = contactDAO.getNewEmployeeID();
        Employee employee = new Employee();
        employee.setId(ID);
        Photo photo = new Photo();
        photo.setEmployeeID(ID);
        employee.setPhoto(photo);

        return employee;
    }

    public String getPhotoForJSP() {
        String resultFileName;
        byte[] data;
        byte[] encodeBase64;
        FileInputStream fileInputStream = null;
        try {
            resultFileName = ConfigurationManager.getPathProperty("path.defaultPhoto");
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
