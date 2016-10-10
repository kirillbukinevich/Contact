package logic.commands;

import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static logic.configuration.ConfigurationManager.getProperty;

public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        return getProperty("path.page.edit");
    }
}
