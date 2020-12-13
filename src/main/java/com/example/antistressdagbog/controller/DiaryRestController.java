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
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/user/api/getDiaryEntries")
    public ResponseEntity<List<DiaryEntryDto>> getDiaryEntries(@RequestParam(name = "week") String week, HttpServletRequest servlet){

        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();

        List<DiaryEntry> diaryEntries = diaryEntryRepository.findAllByAccountAndWeek(account, Integer.parseInt(week));
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
                            dtoMapper.toDiaryEntryDtoWithDateAndWeek(nearestDate.plusDays(i), account)
                    );
                }
            }
        } else {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(df.format(c.getTime()));
            for(int i = 0; i < 7; i++){
                diaryEntryDtos.add(
                        dtoMapper.toDiaryEntryDtoWithDateAndWeek(date.plusDays(i), account)
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
