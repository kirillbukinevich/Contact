package com.itechart.bukinevi.logic.commands.editcommands;

import com.itechart.bukinevi.logic.domain.Attachment;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditAttachmentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        updateFile(request);
        return "";
    }

    private void updateFile(HttpServletRequest request) {
        List<Attachment> attachments = SessionUtils.getEmployeeFromSession(request).getAttachmentList();
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
        attachment.setId(Integer.parseInt((String) request.getAttribute("id")));
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(String.valueOf(LocalDateTime.now()));
        attachment.setSaveOnDisk(false);
        processAttachmentFile(request, attachment);
        return attachment;
    }

    private void processAttachmentFile(HttpServletRequest request, Attachment attachment) {
        @SuppressWarnings("unchecked")
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");

        StringBuilder filePath = new StringBuilder(request.getAttribute("file_path").toString());
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                filePath.append(attachment.getEmployeeID() + "/");
                File uploadDir = new File(filePath.toString());
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                if (StringUtils.isNotEmpty(fileName)) {
                    attachment.setFileName(fileName);
                    attachment.setBytes(fi.get());
                }
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
    }
}