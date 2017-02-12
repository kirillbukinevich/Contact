package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by aefrd on 13.09.2016.
 */
public class SearchCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        search(request);
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }

    private void search(HttpServletRequest request) {
        defineSearchParameters(request);

    }

    private void defineSearchParameters(HttpServletRequest request) {
        List<String> parameterList = Collections.list(request.getParameterNames());
        Map<String, String> searchCriteriasMap = new HashMap<>();
        parameterList.remove("command");

        StringBuilder criteriaDate = processDateCriteria(request, parameterList);
        StringBuilder criteriaInfo = new StringBuilder();
        criteriaInfo.append(criteriaDate);

        parameterList.stream().filter(parameterName -> isCriteria(request.getParameter(parameterName))).forEach(parameterName -> {
            searchCriteriasMap.put(parameterName.substring(5), request.getParameter(parameterName));
            criteriaInfo.append(parameterName.substring(5)).append(": ").append(request.getParameter(parameterName)).append(" ");
        });
        request.getSession().setAttribute("search_info", "search result: " + criteriaInfo);
        request.getSession().setAttribute("search_criteria", searchCriteriasMap);
        request.getSession().setAttribute("search_date_criteria", criteriaDate.toString());
    }

    private boolean isCriteria(String tempCriteria) {
        return !tempCriteria.isEmpty();
    }

    private StringBuilder processDateCriteria(HttpServletRequest request, List<String> paramList) {
        String date = request.getParameter("find_date_of_birth");
        StringBuilder criteria = new StringBuilder();
        if (!date.isEmpty()) {
            String direct = request.getParameter("find_date_direction");
            if ("since".equals(direct)) {
                criteria.append(" date_of_birth >" + "'").append(date).append("' ");
            }
            if ("until".equals(direct)) {
                criteria.append(" date_of_birth <" + "'").append(date).append("' ");
            }
            if (direct.isEmpty()) {
                criteria.append(" date_of_birth =" + "'").append(date).append("' ");
            }
            paramList.remove("find_date_of_birth");
        }
        paramList.remove("find_date_direction");
        return criteria;
    }


}
