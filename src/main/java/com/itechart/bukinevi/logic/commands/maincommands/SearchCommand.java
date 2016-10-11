package com.itechart.bukinevi.logic.commands.maincommands;

import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by aefrd on 13.09.2016.
 */
public class SearchCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        search(request);
        ContactCommand contactCommand = new ContactCommand();
        request.setAttribute("search_bar","show");
        return contactCommand.execute(request);
    }
    public void search(HttpServletRequest request){
        defineSearchParameters(request);

    }
  public boolean defineSearchParameters(HttpServletRequest request){
      ArrayList<String> parameterList = Collections.list(request.getParameterNames());
      HashMap<String,String> searchCriteriasMap = new HashMap<>();
      parameterList.remove("command");

      StringBuilder criteriaDate = processDateCriteria(request,parameterList);
      StringBuilder criteriaInfo = new StringBuilder();
      criteriaInfo.append(criteriaDate);

      for(String parameterName : parameterList){
        if(isCriteria(request.getParameter(parameterName))){
            searchCriteriasMap.put(parameterName.substring(5),request.getParameter(parameterName));
            criteriaInfo.append(parameterName.substring(5)).append(": ").append(request.getParameter(parameterName));
        }
      }
      request.setAttribute("search_info","search result: " + criteriaInfo);
      request.getSession().setAttribute("search_criteria",searchCriteriasMap);
      request.getSession().setAttribute("search_date_criteria",criteriaDate.toString());
      return true;
  }
    public boolean isCriteria(String tempCriteria){
        return !tempCriteria.isEmpty();
    }
    public StringBuilder processDateCriteria(HttpServletRequest request, ArrayList<String> paramList){
        String date = request.getParameter("find_date_of_birth");
        StringBuilder criteria = new StringBuilder();
        if(!date.isEmpty()){
            String direct = request.getParameter("find_date_direction");
            if(direct.equals("с")){
                criteria.append(" date_of_birth >" + "'").append(date).append("' ");
            }
            if(direct.equals("до")){
                criteria.append(" date_of_birth <" + "'").append(date).append("' ");
            }
            if(direct.isEmpty()){
                criteria.append(" date_of_birth =" + "'").append(date).append("' ");
            }
            paramList.remove("find_date_of_birth");
        }
        paramList.remove("find_date_direction");
        return criteria;
    }


}