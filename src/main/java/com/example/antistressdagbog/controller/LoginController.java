package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String performLogin(HttpServletRequest request, @RequestAttribute String username, @RequestAttribute String password) throws ServletException {
        request.login(username, password);
        return "index";
    }

    @GetMapping("/perform_logout")
    public String performLogout(HttpServletRequest servlet) throws ServletException {
        servlet.logout();
        return "index";
    }

}