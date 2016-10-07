package logic.commands.maincommands;

import logic.commands.maincommands.EditCommand;
import logic.entity.Address;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * Created by aefrd on 14.09.2016.
 */
public class UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        update(request);
        chooseDialog(request);
        return "/web/jsp/addedit.jsp";
    }
    public boolean chooseDialog(HttpServletRequest request){
        String command = request.getParameter("command");
        if(command.equals("update_phone") || command.equals("update_edit_phone")) {
            request.setAttribute("popDialog", "phoneModal");
            request.setAttribute("type_operation","New_Phone");
        }else if(command.equals("update_attachment") || command.equals("update_edit_attachment")){
            request.setAttribute("popDialog", "attachModal");
            request.setAttribute("type_operation","New_File");
        }else if(command.equals("update_photo")){
            request.setAttribute("popDialog","photoModal");
        }
        return true;

    }

    public void update(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        updateEmployee(request, employee);
        fillAllParameters(request);
        setEmployeeToSession(request, employee);
    }

    public Employee updateEmployee(HttpServletRequest request, Employee employee) {


        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("last_name"));
        employee.setPatronymic(request.getParameter("patronymic"));
        String[] date;
        if (!(request.getParameter("date_of_birth") == null)) {
            date = request.getParameter("date_of_birth").split("\\-");
            LocalDate localDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            employee.setDateOfBirth(localDate);
        }

        employee.setGender(request.getParameter("gender"));
        employee.setNationality(request.getParameter("nationality"));
        employee.setFamilyStatus(request.getParameter("family_status"));
        employee.setWebSite(request.getParameter("web_site"));
        employee.setEmail(request.getParameter("email"));
        employee.setWorkPlace(request.getParameter("work_place"));
        employee.setAddress(updateAddress(request));

        return employee;
    }

    public Address updateAddress(HttpServletRequest request) {
        Address address = new Address();
        address.setCountryName(request.getParameter("country")==null || request.getParameter("country").isEmpty()
                ? null : (request.getParameter("country")));
        address.setCityName(request.getParameter("city")==null || request.getParameter("city").isEmpty()
                ? null : request.getParameter("city"));
        address.setStreetName(request.getParameter("city")==null || request.getParameter("street").isEmpty()
                ? null : request.getParameter("street"));
        address.setHouseNumber(request.getParameter("house")==null || request.getParameter("house").isEmpty()
                ? null : (request.getParameter("house")));
        address.setFlatNumber(request.getParameter("flat")==null || request.getParameter("flat").isEmpty()
                ? 0 : Integer.valueOf((request.getParameter("flat"))));
        address.setIndex(request.getParameter("house")==null || request.getParameter("house").isEmpty()
                ? 0 : Integer.valueOf((request.getParameter("index"))));
        return address;
    }


    public Employee getEmployeeFromSession(HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        return employee;
    }

    public void setEmployeeToSession(HttpServletRequest request, Employee employee) {
        request.getSession().setAttribute("employee", employee);
    }

    public void fillAllParameters(HttpServletRequest request) {
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);

    }
}
