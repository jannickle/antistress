package com.example.antistressdagbog.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login/login")
    public String login(){
        System.out.println("login page requested");
        return "login/login";
    }

}
