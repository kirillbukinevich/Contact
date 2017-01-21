package com.itechart.bukinevi.logic.commands.editcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by aefrd on 21.09.2016.
 */
public class ChooseEditAttachmentCommand implements ActionCommand{
    private final UpdateCommand updateCommand = new UpdateCommand();
    @Override
    public String execute(HttpServletRequest request) {
        String page = updateCommand.execute(request);
        String[] selectedAttachment = request.getParameterValues("check_selected_file");
        for (String aSelectedPhone : selectedAttachment) {
            this.editAttachment(request, Integer.parseInt(aSelectedPhone));
        }
        return page;
    }
    private void editAttachment(HttpServletRequest request, final int ATTACHMENTID) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        ArrayList<Attachment> attachmentList = employee.getAttachmentList();
        Attachment editAttachment = null;
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENTID){
                if(!attachment.isUpdated() || !attachment.isSaved()) {
                    editAttachment = attachment;
                    break;
                }
            }
        }
        request.getSession().setAttribute("edit_attachment",editAttachment);
        assert editAttachment != null;
        request.setAttribute("file_name", editAttachment.getFileName());
        request.setAttribute("comment_file",editAttachment.getComment());
        request.setAttribute("type_operation","Edit_file");
    }

}
