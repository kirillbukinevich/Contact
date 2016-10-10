//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package web.controller;

import logic.configuration.LogConfiguration;
import logic.processcommand.ActionCommand;
import logic.processcommand.ActionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public Controller() {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        response.setContentType("text/html; charset=UTF-8");
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);
        LogConfiguration.LOGGER.info("finish : " + request.getParameter("command") + " command");

        if (page != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);

        } else {
            page = "/web/jsp/welcome.jsp";
            response.sendRedirect(request.getContextPath() + page);
        }

    }
}
