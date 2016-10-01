package logic.commands.addcommands;

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

public class AddAttachmentCommand extends UpdateCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        addFile(request, employee);
        super.fillAllParameters(request);
        String page = "/web/jsp/addedit.jsp";
        return page;
    }

    public void addFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = getFile(request, employee);

        addAttachmentToBD(attachment);
        employee.getAttachmentList().add(attachment);
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

    public synchronized boolean processAttachmentFile(HttpServletRequest request, Employee employee,Attachment attachment) {
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");
        String filePath  = request.getAttribute("file_path").toString();
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                filePath += attachment.getEmployeeID() + "/";
                File uploadDir = new File(filePath);
                if(!uploadDir.exists()){
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

    public boolean addAttachmentToBD(Attachment attachment) {
        AttachmentDAO attachmentDAO = new AttachmentDAO();
        attachmentDAO.addAttachment(attachment);
        return true;
    }
    public String getSaveName(Employee employee, String originalFileName){
        String extension =  FilenameUtils.getExtension(originalFileName);
        String fileNameOutExtnsn = FilenameUtils.removeExtension(originalFileName);
        String fileName = originalFileName;
        int count = 0;
        Iterator<Attachment> attachmentIterator = employee.getAttachmentList().iterator();
        while (attachmentIterator.hasNext()){
            Attachment attachment = attachmentIterator.next();
            if(attachment.getFileName().equals(fileName)){
                fileName = fileNameOutExtnsn + "(" + ++count + ")." + extension;
            }
        }
        return fileName;
    }
}
