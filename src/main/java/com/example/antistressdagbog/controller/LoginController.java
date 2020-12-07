package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

//    @GetMapping("/perform_logout")
//    public String performLogout(HttpServletRequest servlet) throws ServletException {
//        servlet.logout();
//        return "redirect:/";
//    }

    @GetMapping("/perform_logout")
    public String performLogout(HttpServletRequest servlet) throws ServletException {
        servlet.logout();
        return "redirect:/login";
    }

}
