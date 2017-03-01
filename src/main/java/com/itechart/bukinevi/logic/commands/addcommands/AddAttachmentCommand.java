package com.itechart.bukinevi.logic.commands.addcommands;

import com.itechart.bukinevi.logic.domain.Attachment;
import com.itechart.bukinevi.logic.domain.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.logic.utils.SessionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

public class AddAttachmentCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        Employee employee = SessionUtils.getEmployeeFromSession(request);
        addFileToList(request, employee);
        return "";
    }

    private void addFileToList(HttpServletRequest request, Employee employee) {
        employee.getAttachmentList().add(getFile(request, employee));
    }

    private Attachment getFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = new Attachment();
        final int EMPLOYEE_ID = employee.getId();
        attachment.setEmployeeID(EMPLOYEE_ID);
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(String.valueOf(LocalDateTime.now()));
        attachment.setId(Integer.parseInt((String) request.getAttribute("id")));
        attachment.setSaveOnDisk(false);
        processAttachmentFile(request, employee, attachment);
        return attachment;
    }

    private void processAttachmentFile(HttpServletRequest request, Employee employee, Attachment attachment) {
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

                fileName = getSaveName(employee, fileName);
                attachment.setFileName(fileName);
                attachment.setBytes(fi.get());
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
