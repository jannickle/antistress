package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller

public class PDFController {
    @GetMapping("/guide/view")
    public String view(){
        return "guide/view";
    }
    @RequestMapping("/howto")
    void getFile(HttpServletResponse response) throws IOException {

        String fileName = "sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf";
        String path = "src/main/resources/static/files/" + fileName;

        File file = new File(path);
        FileInputStream inputStream = new FileInputStream(file);

        response.setContentType("application/pdf");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");

        FileCopyUtils.copy(inputStream, response.getOutputStream());

    }
}
