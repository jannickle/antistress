package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import com.example.antistressdagbog.model.DiaryEntry;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DtoMapperTest {

    DtoMapper mapper = new DtoMapper();

    @ParameterizedTest
    @CsvSource({
            "1, 2020-11-23, 1, 1, 5, 3, 2, 5, travlt derhjemme,,,,1",
            "2, 1978-02-05, 49, 2, 9, 4, 6, 5, overarbejde,,,,1",
            "3, 2021-09-30, 25, 5, 1, 10, 7, 7,,,,,2"
    })
    public void testToDiaryEntryDtoNotNull(Long id, String date, Integer week, Integer dayOfWeek, Integer morning, Integer afternoon, Integer evening, Integer sleep, String note1, String note2, String note3, String noteSleep, Long accountId){
        Account account = new Account();
        LocalDate localDate = LocalDate.parse(date);
        account.setId(accountId);
        DiaryEntry entry = new DiaryEntry(id, account, localDate, dayOfWeek, week, morning, afternoon, evening, sleep, note1, note2, note3, noteSleep);
        DiaryEntryDto dto = mapper.toDiaryEntryDto(entry);
        assertNotNull(dto);
    }

    @ParameterizedTest
    @CsvSource({
            "2020-11-23, 2, 5",
            "1978-02-05, 2, 6",
            "2021-09-30, 1, 7"
    })
    public void testToEmptyDiaryEntryDtoNotNull(String date, Long accountId, Integer dayOfWeek){
        Account account = new Account();
        LocalDate localDate = LocalDate.parse(date);
        DiaryEntryDto emptyDto = mapper.toEmptyDiaryDto(localDate, account, dayOfWeek);
        assertNotNull(emptyDto);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2020-11-23, 1, 1, 5, 3, 2, 5, 1, travlt derhjemme,,,",
            "2, 1978-02-05, 2, 49, 9, 4, 6, 5, 1, overarbejde,,,,1",
            "3, 2021-09-30, 5, 25, 1, 10, 7, 2, 7,,,,,2"
    })
    public void testToDiaryEntryNotNull(Long id, String date, Integer dayOfWeek, Integer week, Integer morning, Integer afternoon, Integer evening, Integer sleep, Long accountId, String note1, String note2, String note3){
        Account account = new Account();
        account.setId(accountId);
        DiaryEntryDto dto = new DiaryEntryDto(id, date, dayOfWeek, week, morning, afternoon, evening, sleep, accountId, note1, note2, note3);
        DiaryEntry entry = mapper.toDiaryEntry(dto, account);
        assertNotNull(entry);
    }

}