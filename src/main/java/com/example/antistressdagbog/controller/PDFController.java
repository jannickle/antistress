package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.utility.FileHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class PDFController {

    @GetMapping("/guide/view")
    public String viewGuide(){
        return "guide/view";
    }

    @GetMapping("/guide/about")
    public String viewAbout(){
        return "guide/about";
    }

    @RequestMapping("/howto")
    public void getGuide(HttpServletResponse response) throws IOException {
        FileHandler fileHandler = new FileHandler();
        fileHandler.readfile("sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf","src/main/resources/static/files/",response);
//        String fileName = "sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf";
//        String path = "src/main/resources/static/files/" + fileName;
    }

    @RequestMapping("/about")
    public void getAbout(HttpServletResponse response) throws IOException {
        FileHandler fileHandler = new FileHandler();
        fileHandler.readfile("hvad-er-antistress-dagbogenthauer-stresscenter.pdf","src/main/resources/static/files/",response);
//        String fileName = "sdan-udfylder-du-antistress-dagbogenthauer-stresscenter.pdf";
//        String path = "src/main/resources/static/files/" + fileName;
    }

}
