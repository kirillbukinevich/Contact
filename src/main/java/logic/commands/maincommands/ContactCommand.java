package logic.commands.maincommands;

import logic.database.EmployeeDAO;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ContactCommand implements ActionCommand {
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

        byte RECORDSPERPAGE = 9;
        List employeesList = dao.getEmployeesList((page - 1) * RECORDSPERPAGE, RECORDSPERPAGE,
                searchCriteria,(HashMap)request.getSession().getAttribute("search_criteria"));
        int noOfRecords = dao.getNoOfRecords();
        System.out.println("contactrecords:" + noOfRecords);
        int noOfPages = (int) Math.ceil((double) noOfRecords * 1.0D / (double) RECORDSPERPAGE);


        System.out.println("page " + page + " record " + noOfRecords + " pages " + noOfPages);
        request.setAttribute("employeeList", employeesList);
        request.setAttribute("noOfPages", Integer.valueOf(noOfPages));
        request.setAttribute("currentPage", Integer.valueOf(page));
        String pageForward = "/web/jsp/main.jsp";
        return pageForward;
    }
    public String getSearchCriteria(HttpServletRequest request){
        HashMap<String,String> searchCriteria = (HashMap)request.getSession().getAttribute("search_criteria");
        StringBuilder criteria = new StringBuilder("WHERE ");
        String searchDateCriteria = (String)request.getSession().getAttribute("search_date_criteria");
        if(!(searchDateCriteria==null || searchDateCriteria.isEmpty())){
            criteria.append(searchDateCriteria);
            if(!(searchCriteria==null ||  searchCriteria.isEmpty())){
                criteria.append(" AND ");
            }
        }
        if(!(searchCriteria==null ||  searchCriteria.isEmpty())){
            Iterator<String> iterator = searchCriteria.keySet().iterator();
            while (iterator.hasNext()){
                criteria.append(iterator.next()).append("=? ");
                if(iterator.hasNext()){
                    criteria.append("AND ");
                }
            }
        }
        System.out.println("CRITERIA: " + criteria);
        request.getSession().setAttribute("search_criteria",null);
        request.getSession().setAttribute("search_date_criteria",null);
        if(criteria.toString().equals("WHERE ")){
            return "";
        }else {
            return criteria.toString();
        }
    }
}
