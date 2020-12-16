package com.example.antistressdagbog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

@Controller
public class DiaryController {

    @GetMapping("/user/diary")
    public String chartIndex(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        WeekFields weekFields = WeekFields.ISO;
        int thisWeek = now.get(weekFields.weekOfWeekBasedYear());
        int thisYear = now.getYear();
        return "redirect:/user/diary/" + thisYear + "/" + thisWeek;
    }

    @GetMapping("/user/diary/{curr_year}/{week_num}")
    public String chart(Model model, @PathVariable String week_num, @PathVariable String curr_year){
        model.addAttribute("thisYear", curr_year);
        model.addAttribute("thisWeek", week_num);
        return "user/diary";
    }

    @GetMapping("/user/diary/{curr_year}/{week_num}/next")
    public String next(Model model, @PathVariable String curr_year, @PathVariable String week_num){
        int year = Integer.parseInt(curr_year);
        int week = Integer.parseInt(week_num);
        ZonedDateTime now;
        try{
            String temp = year + "-01-10T10:15:30+01:00[Europe/Paris]";
            if(IsoFields.WEEK_OF_WEEK_BASED_YEAR.rangeRefinedBy(ZonedDateTime.parse(temp)).getMaximum() == 52 && week == 52){
                System.out.println("your logic works");
                week = 0;
                year++;
            }
            String date = year + "-01-10T10:15:30+01:00[Europe/Paris]";
            now = ZonedDateTime.parse(date)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week + 1)
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        } catch (DateTimeException e){
            year++;
            String date = year + "-01-10T10:15:30+01:00[Europe/Paris]";
            now = ZonedDateTime.parse(date)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, IsoFields.WEEK_OF_WEEK_BASED_YEAR.rangeRefinedBy(ZonedDateTime.parse(date)).getMinimum())
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }
        model.addAttribute("thisWeek", now.get(WeekFields.ISO.weekOfWeekBasedYear()));
        model.addAttribute("thisYear", year);
        return "redirect:/user/diary/" + year + "/" + now.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    @GetMapping("/user/diary/{curr_year}/{week_num}/prev")
    public String previous(Model model, @PathVariable String curr_year, @PathVariable String week_num){
        int current_year = Integer.parseInt(curr_year);
        int week = Integer.parseInt(week_num);
        ZonedDateTime now;
        try{
            String date = current_year + "-01-10T10:15:30+01:00[Europe/Paris]";
            System.out.println(date);
            now = ZonedDateTime.parse(date)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week - 1)
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        } catch (DateTimeException e){
            System.out.println(e.getMessage());
            String date = (current_year - 1) + "-01-10T10:15:30+01:00[Europe/Paris]";
            now = ZonedDateTime.parse(date)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, IsoFields.WEEK_OF_WEEK_BASED_YEAR.rangeRefinedBy(ZonedDateTime.parse(date)).getMaximum())
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        }
        model.addAttribute("thisWeek", now.get(WeekFields.ISO.weekOfWeekBasedYear()));
        model.addAttribute("thisYear", now.getYear());
        return "redirect:/user/diary/" + now.getYear() + "/" + now.get(WeekFields.ISO.weekOfWeekBasedYear());
    }

    @PostMapping("/user/diary")
    public String undoChanges(){
        return "redirect:/user/diary";
    }


}
