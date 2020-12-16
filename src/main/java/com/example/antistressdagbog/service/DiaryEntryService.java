package com.example.antistressdagbog.service;

import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import com.example.antistressdagbog.repository.DiaryEntryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiaryEntryService {

    final private DiaryEntryRepository repo;

    public DiaryEntryService(DiaryEntryRepository repository){
        this.repo = repository;
    }

    public List<DiaryEntry> listAllByAccount(Account account){
        return repo.findAllByAccount(account);
    }


}
