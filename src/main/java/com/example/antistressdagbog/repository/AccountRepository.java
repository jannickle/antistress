package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
