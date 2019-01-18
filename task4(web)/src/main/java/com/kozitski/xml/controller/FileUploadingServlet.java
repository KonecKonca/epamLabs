package com.kozitski.xml.controller;

import com.kozitski.xml.util.FileNameGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static com.kozitski.xml.command.PathConstant.CONTROLLER;

@WebServlet(urlPatterns = {"/upload/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(FileUploadingServlet.class);

    private static final String DATA_PATH = "data";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // path from tomcat
        String uploadFilePath = request.getServletContext().getRealPath("") + DATA_PATH;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        LOGGER.info("Upload File Directory = " + fileSaveDir.getAbsolutePath());

        String fileName = FileNameGenerator.generateName();
        String fullName = uploadFilePath + File.separator + fileName;

        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                // save into TomCat directory
                part.write(fullName);

                this.getServletContext().getRequestDispatcher(CONTROLLER).forward(request, response);
            }
        }

    }

}