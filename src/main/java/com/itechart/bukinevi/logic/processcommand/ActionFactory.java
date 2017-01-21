package com.itechart.bukinevi.logic.processcommand;

import com.itechart.bukinevi.logic.commands.EmptyCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    public ActionFactory() {
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        Object current = new EmptyCommand();
        String action = request.getParameter("command");
        if (StringUtils.isEmpty(action)) {
            action = request.getAttribute("command").toString();
        }
        if (StringUtils.isNotEmpty(action)) {
            try {
                CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
                LOGGER.info(String.format("start : %s command", action));
            } catch (IllegalArgumentException e) {
                LOGGER.error(String.format("wrong command: %s",e));
            }
        }
        return (ActionCommand) current;

    }
}
