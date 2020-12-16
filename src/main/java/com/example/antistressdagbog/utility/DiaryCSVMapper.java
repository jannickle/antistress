package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.dto.DiaryEntryCSVDto;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DiaryCSVMapper {

    public List<DiaryEntryCSVDto> toDiaryEntryCSVDtos(String username, List<DiaryEntry> diaryEntries){
        List<DiaryEntryCSVDto> diaryEntryCSVDtos = new ArrayList<>();
        for(DiaryEntry entry : diaryEntries){
            diaryEntryCSVDtos.add(
                    DiaryEntryCSVDto
                            .builder()
                            .user(username)
                            .date(entry.getDate().toString())
                            .week(getEmptyStringIfNull(entry.getWeek()))
                            .dayOfWeek(getEmptyStringIfNull(entry.getDayOfWeek()))
                            .morning(getEmptyStringIfNull(entry.getMorning()))
                            .afternoon(getEmptyStringIfNull(entry.getAfternoon()))
                            .evening(getEmptyStringIfNull(entry.getEvening()))
                            .sleep(getEmptyStringIfNull(entry.getSleep()))
                            .note1(entry.getNote1())
                            .note2(entry.getNote2())
                            .note3(entry.getNote3())
                            .noteSleep(entry.getNoteSleep())
                            .build());
        }
        return diaryEntryCSVDtos;
    }

    private String getEmptyStringIfNull(Integer integer){
        if(integer == null){
            return "";
        } else {
            return integer.toString();
        }
    }

}
