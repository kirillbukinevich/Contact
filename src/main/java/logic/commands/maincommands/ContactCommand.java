package logic.commands.maincommands;

import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ContactCommand implements ActionCommand {
    private final byte RECORDSPERPAGE = 2;
    public ContactCommand() {
    }

    public String execute(HttpServletRequest request) {
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String searchCriteria = getSearchCriteria(request);
        EmployeeDAO dao = new EmployeeDAO();
        dao.rollBack();

        List employeesList = dao.getEmployeesList((page - 1) * RECORDSPERPAGE, RECORDSPERPAGE,searchCriteria);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil((double) noOfRecords * 1.0D / (double) RECORDSPERPAGE);

        System.out.println("contactrecords:" + noOfRecords);
        System.out.println("page " + page + " record " + noOfRecords + " pages " + noOfPages);
        request.setAttribute("employeeList", employeesList);
        request.setAttribute("noOfPages", Integer.valueOf(noOfPages));
        request.setAttribute("currentPage", Integer.valueOf(page));
        String pageForward = "/web/jsp/main.jsp";
        return pageForward;
    }
    public String getSearchCriteria(HttpServletRequest request){
        Object searchCriteria = request.getSession().getAttribute("search_criteria");
//        request.getSession().setAttribute("search_criteria",null);
        if(searchCriteria==null){
            searchCriteria = new String(" ");
        }
        return searchCriteria.toString();
    }
}
