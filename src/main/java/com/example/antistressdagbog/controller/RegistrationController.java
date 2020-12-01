package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.UserCredentialsDto;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class RegistrationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/admin/register")
    public String register(){
        return "admin/register";
    }

    @PostMapping("admin/api/register")
    public ResponseEntity<UserCredentials> register(@RequestBody UserCredentialsDto userCredentialsDto){
        UserCredentials user = new UserCredentials();
        user.setUsername(userCredentialsDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of("USER"));
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}
