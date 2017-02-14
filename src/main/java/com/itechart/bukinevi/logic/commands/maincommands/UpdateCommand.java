package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.entity.Address;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 14.09.2016.
 */
public class UpdateCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        update(request);
        chooseDialog(request);
        return getProperty("path.page.edit");
    }

    private void chooseDialog(HttpServletRequest request) {
        String command = request.getParameter("command");
        String dialogName = "popDialog";
        switch (command) {
            case "update_phone":
            case "update_edit_phone":
                request.setAttribute(dialogName, "phoneModal");
                request.setAttribute("type_operation", "New_Phone");
                break;
            case "update_attachment":
            case "update_edit_attachment":
                request.setAttribute(dialogName, "attachModal");
                request.setAttribute("type_operation", "New_File");
                break;
            case "update_photo":
                request.setAttribute(dialogName, "photoModal");
                break;
            case "save":
                request.setAttribute(dialogName, "saveModal");
                break;
        }
    }

    private void update(HttpServletRequest request) {
        Employee employee = SessionUtils.getEmployeeFromSession(request);
        updateEmployee(request, employee);
        fillAllParameters(request);
        SessionUtils.setEmployeeToSession(request, employee);
    }

    private void updateEmployee(HttpServletRequest request, Employee employee) {

        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("last_name"));
        employee.setPatronymic(request.getParameter("patronymic"));
        String[] date;
        if (request.getParameter("date_of_birth") != null) {
            date = request.getParameter("date_of_birth").split("\\-");
            LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            employee.setDateOfBirth(String.valueOf(localDate));
        }

        employee.setGender(request.getParameter("gender"));
        employee.setNationality(request.getParameter("nationality"));
        employee.setFamilyStatus(request.getParameter("family_status"));
        employee.setWebSite(request.getParameter("web_site"));
        employee.setEmail(request.getParameter("email"));
        employee.setWorkPlace(request.getParameter("work_place"));
        employee.setAddress(updateAddress(request));
    }

    private Address updateAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setCountryName(StringUtils.isEmpty(request.getParameter("country"))
                ? null : (request.getParameter("country")));
        address.setCityName(StringUtils.isEmpty(request.getParameter("city"))
                ? null : request.getParameter("city"));
        address.setStreetName(StringUtils.isEmpty(request.getParameter("street"))
                ? null : request.getParameter("street"));
        address.setHouseNumber(StringUtils.isEmpty(request.getParameter("house"))
                ? null : (request.getParameter("house")));
        address.setFlatNumber(StringUtils.isEmpty(request.getParameter("flat"))
                ? 0 : Integer.valueOf((request.getParameter("flat"))));
        address.setIndex(StringUtils.isEmpty(request.getParameter("index"))
                ? 0 : Integer.valueOf((request.getParameter("index"))));
        return address;
    }

    public void fillAllParameters(HttpServletRequest request) {
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);

    }
}
