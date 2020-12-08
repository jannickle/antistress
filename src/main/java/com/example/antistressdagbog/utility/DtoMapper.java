package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@Component
public class DtoMapper {

    public DiaryEntryDto toDiaryEntryDto(DiaryEntry entry){
        return DiaryEntryDto.builder()
                .id(entry.getId())
                .date(entry.getDate().toString())
                .week(entry.getWeek())
                .morning(entry.getMorning())
                .afternoon(entry.getAfternoon())
                .evening(entry.getEvening())
                .sleep(entry.getSleep())
                .note1(entry.getNote1())
                .note2(entry.getNote2())
                .note3(entry.getNote3())
                .account(entry.getAccount().getId())
                .build();
    }

    public DiaryEntryDto toDiaryEntryDtoWithDateAndWeek(LocalDate date, Account account){
        return DiaryEntryDto.builder()
                .id(null)
                .date(date.toString())
                .week(date.get(ChronoField.ALIGNED_WEEK_OF_YEAR))
                .morning(null)
                .afternoon(null)
                .evening(null)
                .sleep(null)
                .note1("")
                .note2("")
                .note1("")
                .account(account.getId())
                .build();
    }

    public DiaryEntry toDiaryEntry(DiaryEntryDto dto, Account account){
        return DiaryEntry.builder()
                .id(dto.getId())
                .account(account)
                .date(LocalDate.parse(dto.getDate()))
                .week(dto.getWeek())
                .morning(dto.getMorning())
                .afternoon(dto.getAfternoon())
                .evening(dto.getEvening())
                .sleep(dto.getSleep())
                .note1(dto.getNote1())
                .note2(dto.getNote2())
                .note3(dto.getNote3())
                .build();
    }

}
