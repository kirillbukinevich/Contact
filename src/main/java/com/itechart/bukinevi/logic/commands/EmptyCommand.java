package com.itechart.bukinevi.logic.commands;

import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

public class EmptyCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        return getProperty("path.page.edit");
    }
}
