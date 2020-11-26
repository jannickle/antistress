package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<UserCredentials, String> {
    UserCredentials findByUsername(String username);
}
