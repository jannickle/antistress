package com.example.antistressdagbog.controller.security;

import com.example.antistressdagbog.dto.UserCredentialsDto;
import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RegistrationResource {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationResource(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@RequestBody UserCredentialsDto userCredentialsDto) {
        UserCredentials user =
                UserCredentials.builder()
                        .enabled(true)
                        .username(userCredentialsDto.getUsername())
                        .password(passwordEncoder.encode(userCredentialsDto.getPassword()))
                        .roles(Set.of("USER"))
                        .build();
        userRepository.save(user);
    }

}
