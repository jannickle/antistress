package com.example.antistressdagbog.repository;

import com.example.antistressdagbog.model.DiaryEntry;
import org.springframework.data.repository.CrudRepository;

public interface DiaryEntryRepository extends CrudRepository<DiaryEntry, Long> {
}
