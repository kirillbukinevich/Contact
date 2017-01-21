package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.impl.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ContactCommand implements ActionCommand {
    public ContactCommand() {
    }

    public String execute(HttpServletRequest request) {
        int page = 1;
        if (StringUtils.equals(request.getParameter("search_info"),"false")) {
            request.getSession().setAttribute("search_criteria", null);
            request.getSession().setAttribute("search_info",null);
        } else if(StringUtils.isNotEmpty((String) request.getSession().getAttribute("search_info"))){
            request.setAttribute("search_bar", "show");

        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String searchCriteria = getSearchCriteria(request);
        EmployeeDAOUtil dao = new EmployeeDAOUtil();
        dao.rollBack();

        final byte RECORDSPERPAGE = 10;
        List employeesList = dao.getEmployeesList((page - 1) * RECORDSPERPAGE, RECORDSPERPAGE,
                searchCriteria, (HashMap) request.getSession().getAttribute("search_criteria"));
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil((double) noOfRecords * 1.0D / (double) RECORDSPERPAGE);


        request.setAttribute("employeeList", employeesList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        return ConfigurationManager.getProperty("path.page.main");
    }

    private String getSearchCriteria(HttpServletRequest request) {
        HashMap<String, String> searchCriteria = (HashMap) request.getSession().getAttribute("search_criteria");
        StringBuilder criteria = new StringBuilder("WHERE ");
        String searchDateCriteria = (String) request.getSession().getAttribute("search_date_criteria");
        if (StringUtils.isNotEmpty(searchDateCriteria)) {
            criteria.append(searchDateCriteria);
            if (MapUtils.isNotEmpty(searchCriteria)) {
                criteria.append(" AND ");
            }
        }
        if (MapUtils.isNotEmpty(searchCriteria)) {
            Iterator<String> iterator = searchCriteria.keySet().iterator();
            while (iterator.hasNext()) {
                criteria.append(iterator.next()).append("=? ");
                if (iterator.hasNext()) {
                    criteria.append("AND ");
                }
            }
        }
        if (StringUtils.equals(criteria.toString(), "WHERE ")) {
            return "";
        } else {
            return criteria.toString();
        }
    }


}
