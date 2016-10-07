package logic.commands.editcommands;

import logic.commands.maincommands.EditCommand;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by aefrd on 04.10.2016.
 */
public class CancelEdit implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);
        return "/web/jsp/addedit.jsp";
    }
}
