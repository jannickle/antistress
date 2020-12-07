package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class DiaryController {

    @GetMapping("/")
    public String index(){
        return "user/diary";
    }

    @GetMapping("/user/diary")
    public String chart(Model model){
        return "user/diary";
    }

    @PostMapping("/user/diary")
    public String undoChanges(){
        return "redirect:/user/diary";
    }


}
