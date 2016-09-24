package logic.commands.addcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.EmployeeDAO;
import logic.entity.Attachment;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aefrd on 12.09.2016.
 */
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
        addAttachmentToBD(attachment,employee,request);
        employee.getAttachmentList().add(attachment);
    }


    public Attachment getFile(HttpServletRequest request, Employee employee) {
        Attachment attachment = new Attachment();
        processAttachmentFile(request, attachment);
        final int EMPLOYEEID = employee.getId();
        attachment.setEmployeeID(EMPLOYEEID);
        attachment.setComment((String) request.getAttribute("comment"));
        attachment.setLoadDate(LocalDateTime.now());
        return attachment;
    }

    public boolean processAttachmentFile(HttpServletRequest request, Attachment attachment) {
        List<FileItem> fileItems = (List<FileItem>) request.getAttribute("file_item");
        String filePath  = request.getAttribute("file_path").toString();
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
//                    attachment.setFileByte(fi.get());
                attachment.setFileName(fi.getName());
                String fileName = fi.getName();
                String resultFileName = filePath +
                        fileName.substring(fileName.lastIndexOf("\\") + 1);
                File file = new File(resultFileName);
                System.out.println("resultFileName: " + resultFileName);
                try {
                    fi.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                request.setAttribute(fi.getFieldName(), fi.getString());
            }
        }
        return true;
    }

    public boolean addAttachmentToBD(Attachment attachment,Employee employee,HttpServletRequest request) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        ArrayList<Attachment> attachmentList = employee.getAttachmentList();
        Attachment editAttachment = checkEditAttachment(request);
        if(editAttachment!=null) {
            attachmentList.remove(editAttachment);
            employeeDAO.deleteAttachment(editAttachment.getId());
        }
        employeeDAO.addAttachment(attachment);
        return true;
    }
    public Attachment checkEditAttachment(HttpServletRequest request){
        Attachment editAttachment =  (Attachment)request.getSession().getAttribute("edit_attachment");
        request.getSession().setAttribute("edit_attachment",null);
        return editAttachment;
    }
    public Employee getEmployeeFromSession(HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        return employee;
    }


}
