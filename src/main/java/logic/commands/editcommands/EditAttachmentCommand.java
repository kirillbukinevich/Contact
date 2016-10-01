package logic.commands.editcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.AttachmentDAO;
import logic.entity.Attachment;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aefrd on 01.10.2016.
 */
public class EditAttachmentCommand extends UpdateCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        updateFile(request, employee);
        super.fillAllParameters(request);
        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public void updateFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = getFile(request, employee);
        updateAttachmentToBD(attachment, employee, request);
    }


    public Attachment getFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = new Attachment();
        final int EMPLOYEEID = employee.getId();
        attachment.setEmployeeID(EMPLOYEEID);
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(LocalDateTime.now());
        attachment.setSaved(false);
        processAttachmentFile(request, employee, attachment);
        return attachment;
    }

    public synchronized boolean processAttachmentFile(HttpServletRequest request, Employee employee, Attachment attachment) {
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");
        String filePath = request.getAttribute("file_path").toString();
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
//                    attachment.setFileByte(fi.get());
                String fileName = fi.getName();
                filePath += attachment.getEmployeeID() + "/";
                File uploadDir = new File(filePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                fileName = getSaveName(employee, fileName);
                attachment.setFileName(fileName);
                attachment.setAttachment(fi.get());
            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
        return true;
    }

    public boolean updateAttachmentToBD(Attachment attachment, Employee employee, HttpServletRequest request) {
        Attachment oldAttachment = checkEditAttachment(request);
        attachment.setId(oldAttachment.getId());
        oldAttachment.setDeleted(true);
        oldAttachment.setId(0);

        AttachmentDAO attachmentDAO = new AttachmentDAO();
        attachmentDAO.updateAttachment(attachment);

        employee.getAttachmentList().add(attachment);
        return true;
    }

    public Attachment checkEditAttachment(HttpServletRequest request) {
        Attachment editAttachment = (Attachment) request.getSession().getAttribute("edit_attachment");
        request.getSession().setAttribute("edit_attachment", null);
        return editAttachment;
    }

    public String getSaveName(Employee employee, String originalFileName) {
        String extension = FilenameUtils.getExtension(originalFileName);
        String fileNameOutExtnsn = FilenameUtils.removeExtension(originalFileName);
        String fileName = originalFileName;
        int count = 0;
        Iterator<Attachment> attachmentIterator = employee.getAttachmentList().iterator();
        while (attachmentIterator.hasNext()) {
            Attachment attachment = attachmentIterator.next();
            if (attachment.getFileName().equals(fileName)) {
                fileName = fileNameOutExtnsn + "(" + ++count + ")." + extension;
            }
        }
        return fileName;
    }
}
