package logic.commands;

import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String page = "/web/jsp/welcome.jsp";
        return page;
    }
}
