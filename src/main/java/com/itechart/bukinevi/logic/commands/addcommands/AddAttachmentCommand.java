package com.itechart.bukinevi.logic.commands.addcommands;

import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

public class AddAttachmentCommand implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();

    public String execute(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        addFile(request, employee);
        updateCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }

    private void addFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = getFile(request, employee);

        employee.getAttachmentList().add(attachment);
    }

    private Attachment getFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = new Attachment();
        final int EMPLOYEEID = employee.getId();
        attachment.setEmployeeID(EMPLOYEEID);
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(LocalDateTime.now());
        attachment.setId(attachment.hashCode());
        processAttachmentFile(request, employee, attachment);
        return attachment;
    }

    private void processAttachmentFile(HttpServletRequest request, Employee employee, Attachment attachment) {
        @SuppressWarnings("unchecked")
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");

        String filePath  = request.getAttribute("file_path").toString();
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                filePath += attachment.getEmployeeID() + "/";
                File uploadDir = new File(filePath);
                if(!uploadDir.exists()){
                    uploadDir.mkdirs();
                }

                fileName = getSaveName(employee, fileName);
                attachment.setFileName(fileName);
                attachment.setAttachment(fi.get());
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
    }

    private String getSaveName(Employee employee, String originalFileName){
        String extension =  FilenameUtils.getExtension(originalFileName);
        String fileNameOutExtnsn = FilenameUtils.removeExtension(originalFileName);
        String fileName = originalFileName;
        int count = 0;
        for (Attachment attachment : employee.getAttachmentList()) {
            if (attachment.getFileName().equals(fileName)) {
                fileName = fileNameOutExtnsn + "(" + ++count + ")." + extension;
            }
        }
        return fileName;
    }
}
