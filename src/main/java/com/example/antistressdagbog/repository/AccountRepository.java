package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAllByTherapist(Long therapist);
}
