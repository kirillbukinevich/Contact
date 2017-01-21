package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 12.09.2016.
 */
public class DeleteAttachmentCommand implements ActionCommand{
    private final UpdateCommand updateCommand = new UpdateCommand();

    public String execute(HttpServletRequest request) {
        String[] selectedFile = request.getParameterValues("check_selected_file");
        for (String aSelectedFile : selectedFile) {
            this.deleteAttachment(request, Integer.parseInt(aSelectedFile));
        }
        updateCommand.fillAllParameters(request);

        return getProperty("path.page.edit");
    }


    private void deleteAttachment(HttpServletRequest request, final int ATTACHMENTID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        List<Attachment> attachmentList = employee.getAttachmentList();
        Attachment removeFile = null;
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENTID){
                removeFile = attachment;
            }
        }
        assert removeFile != null;
        removeFile.setDeleted(true);
    }
}
