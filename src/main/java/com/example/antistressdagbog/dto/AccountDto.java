package com.example.antistressdagbog.dto;

import com.example.antistressdagbog.model.DiaryEntry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    Long id;

    private String therapist;

    public List<DiaryEntryDto> diaryEntries;

}
