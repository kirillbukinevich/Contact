package logic.commands.deletecommands;

import logic.commands.UpdateCommand;
import logic.processcommand.ActionCommand;
import logic.commands.maincommands.EditCommand;
import logic.database.EmployeeDAO;
import logic.entity.Attachment;
import logic.entity.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by aefrd on 12.09.2016.
 */
public class DeleteAttachmentCommand extends UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        String[] selectedFile = request.getParameterValues("check_selected_file");
        for (String aSelectedFile : selectedFile) {
            this.deleteAttachment(request, Integer.parseInt(aSelectedFile));
        }
        super.fillAllParameters(request);

        String page = "/web/jsp/addedit.jsp";
        return page;
    }


    public boolean deleteAttachment(HttpServletRequest request, final int ATTACHMENTID) {
        Employee employee = getEmployeeFromSession(request);
        List<Attachment> attachmentList = employee.getAttachmentList();
        Attachment removeFile = null;
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENTID){
                removeFile = attachment;
            }
        }
        employee.getAttachmentList().remove(removeFile);
        deleteAttachmentFromDB(ATTACHMENTID);
        return true;
    }


    public boolean deleteAttachmentFromDB(final int ATTACHMENTID){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deleteAttachment(ATTACHMENTID);
        return true;
    }
    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }

}
