package logic.commands.emailcommand.template;

import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aefrd on 23.09.2016.
 */
public class ApplyTemplateEmailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        request.setAttribute("lst_mail", request.getParameter("list_mail"));
        fillTemplates(request);
        String page = "/web/jsp/email.jsp";
        return page;
    }

    public boolean fillTemplates(HttpServletRequest request) {
        String templateType = request.getParameter("template_type");
        System.out.println(templateType);
        if(templateType.isEmpty()){
            request.setAttribute("editable_area","show");

        }else {
            request.setAttribute("editable_area","hide");
        }
        request.setAttribute("template_type",templateType);
        System.out.println(templateType);
        GenerateTemplates generateTemplates = new GenerateTemplates();
        generateTemplates.chooseTemplate(templateType);
        request.setAttribute("template", generateTemplates);
        request.getSession().setAttribute("template",generateTemplates);
        return true;
    }

}
