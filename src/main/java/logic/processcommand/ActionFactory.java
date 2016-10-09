package logic.processcommand;

import logic.commands.EmptyCommand;
import logic.configuration.LogConfiguration;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionFactory() {
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        Object current = new EmptyCommand();
        String action = request.getParameter("command");
        if(StringUtils.isEmpty(action)){
            action = request.getAttribute("command").toString();
        }
        System.out.println("command: " + action);
        if (StringUtils.isNotEmpty(action)) {
            try {
                CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException var5) {
                LogConfiguration.LOGGER.error("wrong command: " + var5);
            }
        }
        return (ActionCommand) current;

    }

}
