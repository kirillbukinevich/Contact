package web;

import logic.processcommand.ConfigurationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private long maxFileSize = 50000 * 1024;
    private int maxMemSize = 14 * 1024;
    private File file;

    public void init() {
            filePath = ConfigurationManager.getProperty("path.saveFile");
        System.out.println(filePath + "IIIIIIIII");
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, java.io.IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        request.setAttribute("file_path",filePath);
        if (!isMultipart) {
            response.sendRedirect("jsp/error.jsp");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(maxFileSize);
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            request.setAttribute("file_item",fileItems);
            System.out.println(fileItems);

            for (FileItem fi : fileItems) {
                if (fi.isFormField()){
                    if(fi.getFieldName().equals("command")){
                        request.setAttribute("command",fi.getString());
                        Controller controller = new Controller();
                        controller.processRequest(request,response);
                    }
                    System.out.println(fi.getFieldName() + ":" + fi.getString());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
