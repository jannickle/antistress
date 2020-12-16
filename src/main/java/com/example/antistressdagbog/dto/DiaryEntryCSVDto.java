package com.example.antistressdagbog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntryCSVDto {

    String user;

    String date;

    String dayOfWeek;

    String week;

    String morning;

    String afternoon;

    String evening;

    String sleep;

    String note1;

    String note2;

    String note3;

    String noteSleep;

//    public void setDayOfWeek(Integer dayOfWeek) {
//        if(dayOfWeek == null){
//            this.dayOfWeek = "";
//        } else {
//            this.dayOfWeek = dayOfWeek.toString();
//        }
//    }
//
//    public void setWeek(Integer week) {
//        if(week == null){
//            this.week = "";
//        } else {
//            this.week = week.toString();
//        }
//    }
//
//    public void setMorning(Integer morning) {
//        if(morning == null){
//            this.morning = "";
//        } else {
//            this.morning = morning.toString();
//        }
//    }
//
//    public void setAfternoon(Integer afternoon) {
//        if(afternoon == null){
//            this.afternoon = "";
//        } else {
//            this.afternoon = afternoon.toString();
//        }
//    }
//
//    public void setEvening(Integer evening) {
//        if(afternoon == null){
//            this.afternoon = "";
//        } else {
//            this.afternoon = afternoon.toString();
//        }
//    }
//
//    public String getSleep() {
//        return sleep;
//    }
//
//    public void setSleep(String sleep) {
//        this.sleep = sleep;
//    }
}
