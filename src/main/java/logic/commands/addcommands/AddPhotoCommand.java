package logic.commands.addcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.processcommand.ActionCommand;
import logic.database.EmployeeDAO;
import logic.entity.Employee;
import logic.entity.Photo;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by aefrd on 13.09.2016.
 */
public class AddPhotoCommand extends UpdateCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        savePhoto(request,employee);
        super.fillAllParameters(request);
        String page = "/web/jsp/addedit.jsp";
        return page;
    }
    public boolean savePhoto(HttpServletRequest request,Employee employee) {
        Photo photo = getPhoto(request,employee);
        addPhotoToBD(photo);
        employee.setPhoto(photo);
        return true;
    }

    public Photo getPhoto(HttpServletRequest request,Employee employee) {
        Photo photo = new Photo();
        processPhoto(request, photo);
        final int EMPLOYEEID =  employee.getId();
        photo.setEmployeeID(EMPLOYEEID);
        return photo;
    }

    public Photo processPhoto(HttpServletRequest request, Photo photo) {
        List<FileItem> fileItems = (List<FileItem>)request.getAttribute("file_item");
        for (FileItem fi : fileItems) {
            if (!fi.isFormField()) {
                String fileName = fi.getName();
                photo.setPhotoName(fileName);
                photo.setBytes(fi.get());
                photo.setDeleted(false);
                photo.setExistInDB(false);
//                String filePath = (String)request.getAttribute("file_path");
//                String resultFileName = filePath +
//                        fileName.substring(fileName.lastIndexOf("\\") + 1);
//                File file = new File(resultFileName);
//                try {
//                    fi.write(file);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
        return photo;
    }

    public boolean addPhotoToBD(Photo photo){
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.updatePhoto(photo);
        return true;
    }
    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }



}
