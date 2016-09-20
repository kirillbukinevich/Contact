//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.commands.maincommands;

import logic.entity.Address;
import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Arrays;

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


    public boolean saveContact(HttpServletRequest request) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = getEmployeeFromSession(request);
//        if(isNewEmployee(request)){
            updateEmployee(request,employee);
//            employeeDAO.addEmployee(employee);
//        }else {
            employeeDAO.editEmployee(employee);
//        }
        employeeDAO.saveContact();

        return true;
    }

    public Employee updateEmployee(HttpServletRequest request, Employee employee) {


        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("last_name"));
        employee.setPatronymic(request.getParameter("patronymic"));

        String[] date = request.getParameter("date_of_birth").split("\\-");
        System.out.println(Arrays.toString(date));
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
    public boolean isNewEmployee(HttpServletRequest request){
        Employee employee = getEmployeeFromSession(request);
        if(employee ==null){
            return true;
        }else {

            return false;
        }
    }
    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }


}
