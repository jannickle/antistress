package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
