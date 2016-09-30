package logic.commands.maincommands;

import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aefrd on 13.09.2016.
 */
public class SearchCommand implements ActionCommand{
    StringBuffer criteria = new StringBuffer("WHERE");
    public String execute(HttpServletRequest request) {
        search(request);
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }
    public void search(HttpServletRequest request){
        defineSearchParameters(request);

    }
  public boolean defineSearchParameters(HttpServletRequest request){
      ArrayList<String> parameterList = Collections.list(request.getParameterNames());
      parameterList.remove("command");
      criteria = processDateCriteria(request,parameterList,criteria);
      for(String parameterName : parameterList){
        if(isCriteria(request.getParameter(parameterName))){
            criteria.append(" " + parameterName.substring(5) + "=" + "'" + request.getParameter(parameterName) + "' ");
        }
      }
      System.out.println(criteria);
      if(criteria.toString().equals("WHERE")){
          request.getSession().setAttribute("search_criteria"," ");
      }else {
          request.getSession().setAttribute("search_criteria",criteria);
          criteria = new StringBuffer("WHERE");
      }
      return true;
  }
    public boolean isCriteria(String tempCriteria){
        return !tempCriteria.isEmpty();
    }
    public StringBuffer processDateCriteria(HttpServletRequest request, ArrayList<String> paramList, StringBuffer criteria){
        String date = request.getParameter("find_date_of_birth");
        if(!date.isEmpty()){
            String direct = request.getParameter("find_date_direction");
            if(direct.equals("since")){
                criteria.append(" date_of_birth >" + "'" + date + "' ");
            }
            if(direct.equals("until")){
                criteria.append(" date_of_birth <" + "'" + date + "' ");
            }
            if(direct.isEmpty()){
                criteria.append(" date_of_birth =" + "'" + date + "' ");
            }
            paramList.remove("find_date_of_birth");
        }
        paramList.remove("find_date_direction");
        return criteria;
    }


}
