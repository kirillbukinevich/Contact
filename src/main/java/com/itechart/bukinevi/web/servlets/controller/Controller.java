//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.web.servlets.controller;

import com.itechart.bukinevi.logic.exceptions.DaoException;
import com.itechart.bukinevi.logic.exceptions.ExecutingCommandsException;
import com.itechart.bukinevi.logic.exceptions.IncorrectDataException;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.processcommand.ActionFactory;
import com.itechart.bukinevi.logic.utils.RequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        ActionFactory client = new ActionFactory();
        response.setContentType("text/html; charset=UTF-8");
        ActionCommand command = client.defineCommand(request);
        String page = null;
        try {
            page = command.execute(request);
        } catch (DaoException e) {
            addErrorMessage(request, e, "Ошибка обращения к базе данных.");
        } catch (IncorrectDataException e) {
            addErrorMessage(request, e, "Неверный формат данных");
        } catch (ExecutingCommandsException e) {
            addErrorMessage(request, e, "Ошибка выполнения команды");
        }
        String commandParameter = "command";
        if (StringUtils.isEmpty(request.getParameter(commandParameter))) {
            LOGGER.info("finish : {} command", request.getAttribute(commandParameter));
        } else {
            LOGGER.info("finish : {} command", request.getParameter(commandParameter));
        }
        if (StringUtils.isEmpty(page)) {
            page = getProperty("path.page.start");
        }
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
        try {
            dispatcher.forward(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.error(e);
        }
    }

    private void addErrorMessage(HttpServletRequest request, Throwable e, String message) {
        LOGGER.error(message, e);
        RequestUtils.addErrorMessage(request, message);
        RequestUtils.addErrorMessage(request, e.getMessage());
    }
}
