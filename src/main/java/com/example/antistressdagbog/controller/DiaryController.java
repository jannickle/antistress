package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiaryController {
    @GetMapping("/user/diary")
    public String chart(){
        return "user/diary";
    }
}
