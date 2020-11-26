package com.example.antistressdagbog.service;

import com.example.antistressdagbog.model.User;
import com.example.antistressdagbog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findById(username);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

}
