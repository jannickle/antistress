package com.example.antistressdagbog.utility;

import com.example.antistressdagbog.dto.DiaryEntryDto;
import com.example.antistressdagbog.model.Account;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DiaryPopulator {

    public List<DiaryEntryDto> populateRestOfWeekWithEmptyEntries(List<DiaryEntryDto> diaryEntryDtos, LocalDate localDate, Account account, DtoMapper mapper){
        for(int i = 1; i <= 7 - diaryEntryDtos.size() + 1; i++){
            LocalDate currentDate = localDate.plusDays(i);
            diaryEntryDtos.add(
                    mapper.toEmptyDiaryDto(currentDate, account, i)
            );
        }
        return diaryEntryDtos;
    }

}
