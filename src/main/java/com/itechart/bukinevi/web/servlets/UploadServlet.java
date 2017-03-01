package com.itechart.bukinevi.web.servlets;

import com.itechart.bukinevi.logic.commands.addcommands.AddAttachmentCommand;
import com.itechart.bukinevi.logic.commands.addcommands.AddPhotoCommand;
import com.itechart.bukinevi.logic.commands.editcommands.EditAttachmentCommand;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(UploadServlet.class);
    private static String filePath;

    @Override
    public void init() {
        filePath = ConfigurationManager.getPathProperty("path.saveFile");
    }


    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        request.setAttribute("file_path", filePath);
        if (!isMultipart) {
            try {
                response.sendRedirect("jsp/error.jsp");
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        final int MAX_MEMORY_SIZE = 1_000_000;
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        ServletFileUpload upload = new ServletFileUpload(factory);
        final long MAX_FILE_SIZE = 1_000_000;
        upload.setSizeMax(MAX_FILE_SIZE);
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> fileItems = upload.parseRequest(request);

            request.setAttribute("file_item", fileItems);
            String commandParameter = "command";
            fileItems.stream().filter(FileItem::isFormField).forEach(fi -> {
                if ("comment_attachment".equals(fi.getFieldName())) {
                    request.setAttribute("comment", fi.getString());
                }
                if ("id".equals(fi.getFieldName())) {
                    request.setAttribute("id", fi.getString());
                }
                if (fi.getFieldName().equals(commandParameter)) {
                    request.setAttribute(commandParameter, fi.getString());
                }
                if ("photo_name".equals(fi.getFieldName())) {
                    request.setAttribute("photo_name", fi.getString());
                }

            });
            if ("NEW_FILE".equals(request.getAttribute(commandParameter))) {
                new AddAttachmentCommand().execute(request);
            } else if ("EDIT_FILE".equals(request.getAttribute(commandParameter))) {
                new EditAttachmentCommand().execute(request);
            } else if ("UPDATE_PHOTO".equals(request.getAttribute(commandParameter))) {
                new AddPhotoCommand().execute(request);
            }

            LOGGER.info("File load");
        } catch (FileUploadException e) {
            LOGGER.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
