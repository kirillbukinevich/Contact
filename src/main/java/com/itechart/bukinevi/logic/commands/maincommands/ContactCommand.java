package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.dao.mysqlImpl.EmployeeDAOImpl;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContactCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        int page = 1;
        String searchParameter = "search_info";
        if (StringUtils.equals(request.getParameter(searchParameter), "false")) {
            request.getSession().setAttribute("search_criteria", null);
            request.getSession().setAttribute(searchParameter, null);
        } else if (StringUtils.isNotEmpty((String) request.getSession().getAttribute(searchParameter))) {
            request.setAttribute("search_bar", "show");

        }
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String searchCriteria = getSearchCriteria(request);
        EmployeeDAOImpl dao = new EmployeeDAOImpl();
        dao.rollBack();

        final int RECORDS_PER_PAGE = 10;

        List employeesList = dao.getEmployeesList((page - 1) * RECORDS_PER_PAGE, RECORDS_PER_PAGE,
                searchCriteria, getMapSearchCriteria(request));
        request.setAttribute("employeeList", employeesList);

        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil((double) noOfRecords * 1.0D / (double) RECORDS_PER_PAGE);
        request.setAttribute("noOfPages", noOfPages);

        request.setAttribute("currentPage", page);
        return ConfigurationManager.getProperty("path.page.main");
    }


    private String getSearchCriteria(HttpServletRequest request) {
        Map<String, String> searchCriteria = getMapSearchCriteria(request);
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
                criteria.append(iterator.next()).append(" LIKE ? ");
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

    @SuppressWarnings("unchecked")
    private Map<String, String> getMapSearchCriteria(HttpServletRequest request) {
        return (Map)request.getSession().getAttribute("search_criteria");
    }
}
