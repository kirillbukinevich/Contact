package logic.commands.editcommands;

import logic.commands.maincommands.EditCommand;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 04.10.2016.
 */
public class CancelEdit implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }
}
