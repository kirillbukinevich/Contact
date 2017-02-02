package com.itechart.bukinevi.logic.commands.editcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditAttachmentCommand implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();

    @Override
    public String execute(HttpServletRequest request) {
        updateFile(request);
        return "";
    }

    private void updateFile(HttpServletRequest request) {
        List<Attachment> attachments = updateCommand.getEmployeeFromSession(request).getAttachmentList();
        Attachment updateAttachment = getUpdateAttachment(request);
        for (Attachment attachment : attachments) {
            if (updateAttachment.getId() == attachment.getId() &&
                    attachment.isSaveOnDisk()) {
                attachment.setDeleted(true);
            }
        }
        attachments.add(updateAttachment);
    }


    private Attachment getUpdateAttachment(HttpServletRequest request) {
        Attachment attachment = new Attachment();
        attachment.setId(Integer.parseInt((String)request.getAttribute("id")));
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(String.valueOf(LocalDateTime.now()));
        attachment.setSaveOnDisk(false);
        processAttachmentFile(request, attachment);
        System.out.println("UPDATE");
        System.out.println(attachment);
        System.out.println("UPDATE");
        return attachment;
    }

    private void processAttachmentFile(HttpServletRequest request, Attachment attachment) {
        @SuppressWarnings("unchecked")
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
                if (StringUtils.isNotEmpty(fileName)) {
                    attachment.setFileName(fileName);
                    attachment.setAttachment(fi.get());
                }
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
    }
}