//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.processcommand;

import logic.commands.EmptyCommand;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionFactory() {
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        Object current = new EmptyCommand();
        String action = request.getParameter("command");
        if(action==null){
            action = (String)request.getAttribute("command");
        }

        System.out.println("action: " + action);
        if (action != null && !action.isEmpty()) {
            try {
                CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
            } catch (IllegalArgumentException var5) {
                ;
            }

            return (ActionCommand) current;
        } else {
            return (ActionCommand) current;
        }
    }

}
