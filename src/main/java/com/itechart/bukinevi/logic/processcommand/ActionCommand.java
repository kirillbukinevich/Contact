package com.itechart.bukinevi.logic.processcommand;

import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
