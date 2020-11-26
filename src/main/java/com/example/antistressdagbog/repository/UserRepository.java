package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
