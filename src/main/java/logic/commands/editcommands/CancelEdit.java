package logic.commands.editcommands;

import logic.commands.maincommands.EditCommand;
import logic.commands.maincommands.UpdateCommand;
import logic.database.EmployeeDAO;
import logic.database.PhotoDAO;
import logic.entity.Attachment;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 04.10.2016.
 */
public class CancelEdit extends UpdateCommand implements ActionCommand{
    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getEmployeeOnId(getEmployeeFromSession(request).getId());
        PhotoDAO photoDAO = new PhotoDAO();
        employee.setPhoto(photoDAO.getPhoto(getEmployeeFromSession(request).getId()));
        cancelAttcachments(request,employee);
        cancelContactPhone(request,employee);
        setEmployeeToSession(request,employee);
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }
    public boolean cancelAttcachments(HttpServletRequest request,Employee employee){
        List<Attachment> attachments = getEmployeeFromSession(request).getAttachmentList();
        if(CollectionUtils.isNotEmpty(attachments)) {
            Iterator<Attachment> attachmentIterator = attachments.listIterator();
            while (attachmentIterator.hasNext()) {
                Attachment attachment = attachmentIterator.next();
                if(attachment.isSaved() && (attachment.isUpdated() || attachment.isDeleted())){
                    attachment.setUpdated(false);
                    attachment.setDeleted(false);
                }
                if (!attachment.isSaved()) {
                    attachmentIterator.remove();
                }
            }
        }
        employee.setAttachmentList((ArrayList<Attachment>) attachments);
        return true;
    }
    public boolean cancelContactPhone(HttpServletRequest request,Employee employee){
        List<ContactPhone> phoneList = getEmployeeFromSession(request).getPhoneList();
        Iterator<ContactPhone> phoneIterator = phoneList.listIterator();
        while (phoneIterator.hasNext()){
            ContactPhone phone = phoneIterator.next();
            if(phone.isSaved() && (phone.isUpdated() || phone.isDeleted())){
                phone.setIsDeleted(false);
                phone.setIsUpdated(false);
            }
            if(!phone.isSaved()){
                phoneIterator.remove();
            }
        }
        employee.setPhoneList((ArrayList<ContactPhone>) phoneList);
        return true;
    }


}
