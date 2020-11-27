package com.example.antistressdagbog.service;

import com.example.antistressdagbog.model.UserCredentials;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserCredentials findByUsername(String username) throws EntityNotFoundException {
        return userRepository.findById(username).orElseThrow(() -> new EntityNotFoundException(username));
    }

    public UserCredentials findById(String username){
        Optional<UserCredentials> userOpt = userRepository.findById(username);
        return userOpt.orElse(null);
    }

    public void saveUser(UserCredentials userCredentials){
        userRepository.save(userCredentials);
    }

}
