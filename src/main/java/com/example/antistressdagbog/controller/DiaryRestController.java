package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DiaryRestController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/api/getDiaryEntries")
    public ResponseEntity<Account> getDiaryEntries(HttpServletRequest servlet){
        System.out.println("/user/api/getDiaryEntries called");
        UserCredentials user = userRepository.findById(servlet.getRemoteUser()).get();
        Account account = user.getAccount();
        return ResponseEntity.ok(account);
    }

}
