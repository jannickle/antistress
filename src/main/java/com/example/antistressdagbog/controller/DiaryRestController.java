package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.AccountDto;
import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class DiaryRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/api/getDiaryEntries")
    public ResponseEntity<AccountDto> getDiaryEntries(HttpServletRequest servlet){
        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();
        List<DiaryEntryDto> diaryEntryDtos= new ArrayList<>();

        for(DiaryEntry entry : account.getDiaryEntries()){
            diaryEntryDtos.add(
                    new DiaryEntryDto(
                            entry.getDate().toString(),
                            entry.getDayOfWeek(),
                            entry.getMorning(),
                            entry.getAfternoon(),
                            entry.getEvening()));
        }
        AccountDto accountDto = new AccountDto(account.getId(), account.getTherapist(), diaryEntryDtos);
        return ResponseEntity.ok(accountDto);
    }

}
