package com.itechart.bukinevi.logic.commands.deletecommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.domain.Attachment;
import com.itechart.bukinevi.logic.domain.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 12.09.2016.
 */
public class DeleteAttachmentCommand implements ActionCommand{
    private final UpdateCommand updateCommand = new UpdateCommand();

    @Override
    public String execute(HttpServletRequest request) {
        String[] selectedFile = request.getParameterValues("check_selected_file");
        for (String aSelectedFile : selectedFile) {
            this.deleteAttachment(request, Integer.parseInt(aSelectedFile));
        }
        updateCommand.fillAllParameters(request);

        return getProperty("path.page.edit");
    }


    private void deleteAttachment(HttpServletRequest request, final int ATTACHMENT_ID) {
        Employee employee = SessionUtils.getEmployeeFromSession(request);
        List<Attachment> attachmentList = employee.getAttachmentList();
        Attachment removeFile = new Attachment();
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENT_ID){
                removeFile = attachment;
            }
        }
        removeFile.setDeleted(true);
    }
}
