package logic.commands.deletecommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.Attachment;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 12.09.2016.
 */
public class DeleteAttachmentCommand implements ActionCommand{
    private UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        String[] selectedFile = request.getParameterValues("check_selected_file");
        for (String aSelectedFile : selectedFile) {
            this.deleteAttachment(request, Integer.parseInt(aSelectedFile));
        }
        updateCommand.fillAllParameters(request);

        return getProperty("path.page.edit");
    }


    public boolean deleteAttachment(HttpServletRequest request, final int ATTACHMENTID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        List<Attachment> attachmentList = employee.getAttachmentList();
        Attachment removeFile = null;
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENTID){
                removeFile = attachment;
            }
        }
        removeFile.setDeleted(true);
        return true;
    }
}
