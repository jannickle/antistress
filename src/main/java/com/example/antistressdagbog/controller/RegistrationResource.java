package com.example.antistressdagbog.controller;

import com.example.antistressdagbog.dto.UserCredentialsDto;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RegistrationResource {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationResource(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("api/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UserCredentials> register(@RequestBody UserCredentialsDto userCredentialsDto) {
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
