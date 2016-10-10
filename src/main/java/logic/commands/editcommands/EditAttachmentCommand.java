package logic.commands.editcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.Attachment;
import logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditAttachmentCommand implements ActionCommand {
    private UpdateCommand updateCommand = new UpdateCommand();
    public String execute(HttpServletRequest request) {
        updateFile(request);
        updateCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }

    public void updateFile(HttpServletRequest request) {
        Attachment oldAttachment = getEditAttachment(request);
        Attachment newAttachment = null;
        if (oldAttachment.isSaved()) {
            oldAttachment.setUpdated(true);
            newAttachment = oldAttachment.clone();
            newAttachment.setSaved(false);
            oldAttachment.setDeleted(true);
        } else {
            newAttachment = oldAttachment;
        }

        ArrayList<Attachment> attachments = updateCommand.getEmployeeFromSession(request).getAttachmentList();
        if (oldAttachment != newAttachment) {
            attachments.add(getFile(request, getFile(request,newAttachment)));
        } else {
            attachments.set(attachments.indexOf(oldAttachment), getFile(request,newAttachment));
        }
    }


    public Attachment getFile(HttpServletRequest request, Attachment attachment) {
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(LocalDateTime.now());
        processAttachmentFile(request, attachment);
        return attachment;
    }

    public boolean processAttachmentFile(HttpServletRequest request, Attachment attachment) {
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");
        String filePath = request.getAttribute("file_path").toString();
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                filePath += attachment.getEmployeeID() + "/";
                File uploadDir = new File(filePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                attachment.setFileName(fileName);
                attachment.setAttachment(fi.get());
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
        return true;
    }

    public Attachment getEditAttachment(HttpServletRequest request) {
        Attachment editAttachment = (Attachment) request.getSession().getAttribute("edit_attachment");
        request.getSession().setAttribute("edit_attachment", null);
        return editAttachment;
    }

}