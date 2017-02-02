package com.itechart.bukinevi.web;

import com.itechart.bukinevi.logic.commands.addcommands.AddAttachmentCommand;
import com.itechart.bukinevi.logic.commands.addcommands.AddPhotoCommand;
import com.itechart.bukinevi.logic.commands.editcommands.EditAttachmentCommand;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;
import com.itechart.bukinevi.web.controller.Controller;
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
import java.util.Arrays;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private String filePath;
    private final long MAX_FILE_SIZE = 50000 * 1024;
    private final int MAX_MEMORY_SIZE = 14 * 1024;
    private File file;

    public void init() {
            filePath = ConfigurationManager.getPathProperty("path.saveFile");
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, java.io.IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        request.setAttribute("file_path",filePath);
        if (!isMultipart) {
            response.sendRedirect("jsp/error.jsp");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(MAX_FILE_SIZE);
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> fileItems = upload.parseRequest(request);

            request.setAttribute("file_item",fileItems);
            ActionCommand command = null;
            fileItems.stream().filter(FileItem::isFormField).forEach(fi -> {
                if (fi.getFieldName().equals("comment_attachment")) {
                    request.setAttribute("comment", fi.getString());
                }
                if (fi.getFieldName().equals("id")) {
                    request.setAttribute("id", fi.getString());
                }
                if (fi.getFieldName().equals("command")){
                    request.setAttribute("command",fi.getString());
                   }
                if (fi.getFieldName().equals("photo_name")) {
                    request.setAttribute("photo_name",fi.getString());
                }

            });
            if(request.getAttribute("command").equals("NEW_FILE")){
                new AddAttachmentCommand().execute(request);
            }else if (request.getAttribute("command").equals("EDIT_FILE")) {
                new EditAttachmentCommand().execute(request);
            }else if(request.getAttribute("command").equals("UPDATE_PHOTO")){
                new AddPhotoCommand().execute(request);
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
