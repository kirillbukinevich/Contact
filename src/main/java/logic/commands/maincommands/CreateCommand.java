package logic.commands.maincommands;

import logic.processcommand.ActionCommand;
import logic.entity.Address;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class CreateCommand implements ActionCommand {
    public CreateCommand() {
    }

    public String execute(HttpServletRequest request) {
        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public Employee createEmployee(HttpServletRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("second_name"));
        employee.setPatronymic(request.getParameter("patronymic"));
        String[] date = request.getParameter("date_of_birth").split(".");
        LocalDate localDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        employee.setDateOfBirth(localDate);
        employee.setGender(request.getParameter("gender"));
        employee.setNationality(request.getParameter("nationality"));
        employee.setFamilyStatus(request.getParameter("family_status"));
        employee.setWebSite(request.getParameter("web_site"));
        employee.setEmail(request.getParameter("email"));
        employee.setWorkPlace(request.getParameter("work_place"));
        Address address = new Address();
        address.setCountryName(request.getParameter("country"));
        address.setCityName(request.getParameter("city"));
        address.setStreetName(request.getParameter("street"));
        address.setHouseNumber(Integer.parseInt(request.getParameter("house")));
        address.setFlatNumber(Integer.parseInt(request.getParameter("flat")));
        address.setIndex(Integer.parseInt(request.getParameter("index")));
        employee.setAddress(address);
        return employee;
    }
}
