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
import java.util.Enumeration;
import java.util.List;

public class AddAttachmentCommand implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();

    @Override
    public String execute(HttpServletRequest request) {
        Employee employee = updateCommand.getEmployeeFromSession(request);
        addFileToList(request, employee);
        return "";
    }

    private void addFileToList(HttpServletRequest request, Employee employee) {
        employee.getAttachmentList().add(getFile(request, employee));
    }

    private Attachment getFile(HttpServletRequest request, Employee employee) {
        try {
            Attachment attachment = new Attachment();
            final int EMPLOYEEID = employee.getId();
            attachment.setEmployeeID(EMPLOYEEID);
            attachment.setComment((String) request.getAttribute("comment"));
            attachment.setLoadDate(String.valueOf(LocalDateTime.now()));
            System.out.println(request.getAttribute("id"));
            attachment.setId(Integer.parseInt((String) request.getAttribute("id")));
            attachment.setSaveOnDisk(false);
            processAttachmentFile(request, employee, attachment);
            return attachment;
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void processAttachmentFile(HttpServletRequest request, Employee employee, Attachment attachment) {
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

                fileName = getSaveName(employee, fileName);
                attachment.setFileName(fileName);
                attachment.setAttachment(fi.get());
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
    }

    private String getSaveName(Employee employee, String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
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
