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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class DiaryRestController {

    private final UserRepository userRepository;

    private final DiaryEntryRepository diaryEntryRepository;

    private final AccountRepository accountRepository;

    private final DtoMapper dtoMapper;

    @GetMapping("/user/api/getDiaryEntries")
    public ResponseEntity<List<DiaryEntryDto>> getDiaryEntries(HttpServletRequest servlet){
        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();
        List<DiaryEntry> diaryEntries = diaryEntryRepository.findAllByAccountAndWeek(account, 49);
        List<DiaryEntryDto> diaryEntryDtos= new ArrayList<>();
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
