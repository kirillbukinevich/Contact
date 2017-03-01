package com.itechart.bukinevi.web.servlets;

/**
 * Created by aefrd on 28.09.2016.
 */

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/download")
public class DownloadFileServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DownloadFileServlet.class);

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        // reads input file from an absolute path
        String filePath = ConfigurationManager.getPathProperty("path.saveFile") + request.getParameter("file_path");
        File downloadFile = new File(filePath);
        try (FileInputStream inStream = new FileInputStream(downloadFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inStream);
             OutputStream outStream = response.getOutputStream()) {

            // obtains ServletContext
            ServletContext context = getServletContext();

            // gets MIME type of the file
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }

            // modifies response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // forces download
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", request.getParameter("file_name"));
            response.setHeader(headerKey, headerValue);

            // obtains response's output stream

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}