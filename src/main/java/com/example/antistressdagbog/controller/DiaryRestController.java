package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.AccountDto;
import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.AccountRepository;
import com.example.antistressdagbog.repository.DiaryEntryRepository;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryEntryRepository diaryEntryRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/user/api/getDiaryEntries")
    public ResponseEntity<AccountDto> getDiaryEntries(HttpServletRequest servlet){
        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();
        List<DiaryEntryDto> diaryEntryDtos= new ArrayList<>();
        System.out.println(account.getId());

        for(DiaryEntry entry : account.getDiaryEntries()){
            diaryEntryDtos.add(
                    new DiaryEntryDto(
                            entry.getId(),
                            entry.getDate().toString(),
                            entry.getWeek(),
                            entry.getDayOfWeek(),
                            entry.getMorning(),
                            entry.getAfternoon(),
                            entry.getEvening(),
                            account.getId()
                    ));
        }
        AccountDto accountDto = new AccountDto(account.getId(), account.getTherapist(), diaryEntryDtos);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/user/api/saveDiary")
    public ResponseEntity<AccountDto> saveDiary(@RequestBody List<DiaryEntryDto> diaryEntryDtos){
        System.out.println(diaryEntryDtos.get(0).getAccount());
        Account account = accountRepository.findById(diaryEntryDtos.get(0).getAccount()).get();
        List<DiaryEntry> diaryEntries = diaryEntryRepository.findAllByAccountAndWeek(account, diaryEntryDtos.get(0).getWeek());
        for(int i = 0; i < diaryEntryDtos.size(); i++){
            diaryEntries.get(i).setMorning(diaryEntryDtos.get(i).getMorning());
            diaryEntries.get(i).setAfternoon(diaryEntryDtos.get(i).getAfternoon());
            diaryEntries.get(i).setEvening(diaryEntryDtos.get(i).getEvening());
        }
        diaryEntryRepository.saveAll(diaryEntries);
        return ResponseEntity.ok(new AccountDto());
    }

}
