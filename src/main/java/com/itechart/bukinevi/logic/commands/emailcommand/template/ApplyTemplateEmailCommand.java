package com.itechart.bukinevi.logic.commands.emailcommand.template;

import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 23.09.2016.
 */
public class ApplyTemplateEmailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        request.setAttribute("lst_mail", request.getParameter("list_mail"));
        fillTemplates(request);
        return getProperty("path.page.email");
    }

    private void fillTemplates(HttpServletRequest request) {
        String templateType = request.getParameter("template_type");
        if(templateType.isEmpty()){
            request.setAttribute("editable_area","show");

        }else {
            request.setAttribute("editable_area","hide");
        }
        request.setAttribute("template_type",templateType);
        GenerateTemplates generateTemplates = new GenerateTemplates();
        generateTemplates.chooseTemplate(templateType);
        request.setAttribute("template", generateTemplates);
        request.setAttribute("subject", generateTemplates.getSubject());
        request.setAttribute("text",generateTemplates.getText());
        request.setAttribute("includePage",generateTemplates.getIncludePage());
        request.getSession().setAttribute("template",generateTemplates);
    }

}
