package com.itechart.bukinevi.logic.commands.editcommands;

import com.itechart.bukinevi.logic.commands.maincommands.EditCommand;
import com.itechart.bukinevi.logic.commands.maincommands.UpdateCommand;
import com.itechart.bukinevi.logic.database.impl.EmployeeDAOUtil;
import com.itechart.bukinevi.logic.database.impl.PhotoDAOUtil;
import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.Employee;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 04.10.2016.
 */
public class CancelEdit implements ActionCommand {
    private final UpdateCommand updateCommand = new UpdateCommand();

    @Override
    public String execute(HttpServletRequest request) {
        EmployeeDAOUtil employeeDAO = new EmployeeDAOUtil();
        Employee employee = employeeDAO.getEmployeeOnId(updateCommand.getEmployeeFromSession(request).getId());
        PhotoDAOUtil photoDAO = new PhotoDAOUtil();
        employee.setPhoto(photoDAO.getPhoto(updateCommand.getEmployeeFromSession(request).getId()));
        cancelAttcachments(request, employee);
        updateCommand.setEmployeeToSession(request, employee);
        EditCommand editCommand = new EditCommand();
        editCommand.fillAllParameters(request);
        return getProperty("path.page.edit");
    }

    private void cancelAttcachments(HttpServletRequest request, Employee employee) {
        List<Attachment> attachments = updateCommand.getEmployeeFromSession(request).getAttachmentList();
        if (CollectionUtils.isNotEmpty(attachments)) {
            Iterator<Attachment> attachmentIterator = attachments.listIterator();
            while (attachmentIterator.hasNext()) {
                Attachment attachment = attachmentIterator.next();
                if (attachment.isDeleted()) {
                    attachment.setDeleted(false);
                }
                if (!attachment.isSaveOnDisk()) {
                    attachmentIterator.remove();
                }
            }
        }
        employee.setAttachmentList(attachments);
    }


}
