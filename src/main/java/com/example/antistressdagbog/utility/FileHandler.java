package com.example.antistressdagbog.utility;

import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileHandler {
    public void readfile(String fileName, String path, HttpServletResponse response) throws IOException {
//        String fileName = "sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf";
       String pathen = path + fileName;

        File file = new File(pathen);
        FileInputStream inputStream = new FileInputStream(file);

        response.setContentType("application/pdf");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
