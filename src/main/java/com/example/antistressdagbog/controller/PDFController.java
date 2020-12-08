package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.utility.FileHandler;
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
    @GetMapping("/navbar")
    public String viewnav(){
        return "navbar";
    }
    @RequestMapping("/howto")
    public void getFile(HttpServletResponse response) throws IOException {
        FileHandler fileHandler = new FileHandler();
        fileHandler.readfile("sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf","src/main/resources/static/files/",response);
//        String fileName = "sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf";
//        String path = "src/main/resources/static/files/" + fileName;


    }

}
