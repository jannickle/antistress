package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.UserCredentialsDto;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(){
        return "register/index";
    }

    @PostMapping("api/register")
    public ResponseEntity<UserCredentials> register(@RequestBody UserCredentialsDto userCredentialsDto){
        UserCredentials user =
        UserCredentials.builder()
                .enabled(true)
                .username(userCredentialsDto.getUsername())
                .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
                .roles(Set.of("USER"))
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}
