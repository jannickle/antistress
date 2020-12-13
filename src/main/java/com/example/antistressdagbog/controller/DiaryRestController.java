package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.AccountRepository;
import com.example.antistressdagbog.repository.DiaryEntryRepository;
import com.example.antistressdagbog.repository.UserRepository;
import com.example.antistressdagbog.utility.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class DiaryRestController {

    private final UserRepository userRepository;

    private final DiaryEntryRepository diaryEntryRepository;

    private final AccountRepository accountRepository;

    private final DtoMapper dtoMapper;

    @GetMapping("/user/api/getCurrentWeek")
    public ResponseEntity<String> getCurrentWeek(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        int thisWeek = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        return ResponseEntity.ok(Integer.toString(thisWeek));
    }

    @GetMapping("/user/api/getDiaryEntries/{curr_year}/{week_num}")
    public ResponseEntity<List<DiaryEntryDto>> getDiaryEntries(HttpServletRequest servlet, @PathVariable String curr_year, @PathVariable String week_num){
        int year = Integer.parseInt(curr_year);
        int week = Integer.parseInt(week_num);
        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();
        List<DiaryEntry> diaryEntries = diaryEntryRepository.findAllByAccountAndWeek(account, week);
        List<DiaryEntryDto> diaryEntryDtos= new ArrayList<>();
        if(diaryEntries.size() != 0){
            for(DiaryEntry entry : diaryEntries){
                diaryEntryDtos.add(
                        dtoMapper.toDiaryEntryDto(entry)
                );
            }
            if(diaryEntries.size() < 7){
                LocalDate nearestDate = diaryEntries.get(diaryEntries.size() - 1).getDate();
                for(int i = 1; i <= 7 - diaryEntryDtos.size() + 1; i++){
                    diaryEntryDtos.add(
                            dtoMapper.toEmptyDiaryDto(nearestDate.plusDays(i), account, diaryEntryDtos.size() + 1)
                    );
                }
            }
        } else {
            String date = year + "-01-10T10:15:30+01:00[Europe/Paris]";
            ZonedDateTime now = ZonedDateTime.parse(date)
                    .with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, week)
                    .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate date2 = now.toLocalDate();
            for(int i = 0; i < 7; i++){
                diaryEntryDtos.add(
                        dtoMapper.toEmptyDiaryDto(date2.plusDays(i), account, i + 1)
                );
            }
        }
        return ResponseEntity.ok(diaryEntryDtos);
    }

    @PostMapping("/user/api/saveDiary")
    public void saveDiary(@RequestBody List<DiaryEntryDto> diaryEntryDtos){
        Account account = accountRepository.findById(diaryEntryDtos.get(0).getAccount()).get();
        List<DiaryEntry> diaryEntries = new ArrayList<>();
        for(DiaryEntryDto dto : diaryEntryDtos){
            diaryEntries.add(
                    dtoMapper.toDiaryEntry(dto, account)
            );
        }
        for(DiaryEntry entry : diaryEntries){
            System.out.println(entry.toString());
        }
//        diaryEntryRepository.saveAll(diaryEntries);
    }

}
