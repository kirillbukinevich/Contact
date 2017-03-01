//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.dao.AbstractDAO;
import com.itechart.bukinevi.logic.dao.EmployeeDAO;
import com.itechart.bukinevi.logic.dao.MySqlFactory;
import com.itechart.bukinevi.logic.dao.mysqlImpl.EmployeeDAOImpl;
import com.itechart.bukinevi.logic.domain.Employee;
import com.itechart.bukinevi.logic.domain.Photo;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

public class NewCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(EditCommand.class.getName());
    private MySqlFactory mySqlFactory = new MySqlFactory();

    @Override
    public String execute(HttpServletRequest request) {
        startCreateContact();
        Employee employee = getNewEmployee();
        SessionUtils.setEmployeeToSession(request, employee);
        request.setAttribute("employee_id",employee.getId());
        try {
            request.setAttribute("default_photo", getPhotoForJSP());
        } catch (IOException e) {
            LOGGER.error(e);
        }
        request.setAttribute("show_default_photo","inline-block");
        request.setAttribute("show_photo","none");

        return getProperty("path.page.edit");
    }

    private void startCreateContact() {
        AbstractDAO contactDAO = new EmployeeDAOImpl();
        contactDAO.startEditContact();
    }

    private Employee getNewEmployee() {
        EmployeeDAO contactDAO = mySqlFactory.getEmployeeDAO();
        final int ID = contactDAO.getNewEmployeeID();
        Employee employee = new Employee();
        employee.setId(ID);
        Photo photo = new Photo();
        photo.setEmployeeID(ID);
        employee.setPhoto(photo);

        return employee;
    }

    private String getPhotoForJSP() throws IOException{

        String resultFileName = ConfigurationManager.getPathProperty("path.defaultPhoto");

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(resultFileName))){
            byte[]  data = IOUtils.toByteArray(bufferedInputStream);
            byte[] encodeBase64 = Base64.encodeBase64(data);
            return new String(encodeBase64, "UTF-8");
        }
    }

}
