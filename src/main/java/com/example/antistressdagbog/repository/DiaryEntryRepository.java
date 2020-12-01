package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DiaryEntryRepository extends CrudRepository<DiaryEntry, Long> {
    List<DiaryEntry> findAllByAccountAndWeek(Account account, int week);
}
