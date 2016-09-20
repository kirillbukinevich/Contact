package logic.commands.maincommands;

import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ContactCommand implements ActionCommand {
    public ContactCommand() {
    }

    public String execute(HttpServletRequest request) {
        int page = 1;
        byte recordsPerPage = 2;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        EmployeeDAO dao = new EmployeeDAO();
        dao.rollBack();
        Object searchCriteria = request.getSession().getAttribute("search_criteria");
        if(searchCriteria==null){
            searchCriteria = new String(" ");
        }
        List list = dao.getEmployeesList((page - 1) * recordsPerPage, recordsPerPage,searchCriteria.toString());
        int noOfRecords = dao.getNoOfRecords();
        System.out.println("contactrecords:" + noOfRecords);
        int noOfPages = (int) Math.ceil((double) noOfRecords * 1.0D / (double) recordsPerPage);
        System.out.println("page " + page + " record " + noOfRecords + " pages " + noOfPages);
        request.setAttribute("employeeList", list);
        request.setAttribute("noOfPages", Integer.valueOf(noOfPages));
        request.setAttribute("currentPage", Integer.valueOf(page));
        String pageForward = "/web/jsp/main.jsp";
        return pageForward;
    }
}
