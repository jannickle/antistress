package com.example.antistressdagbog.dto;

import com.example.antistressdagbog.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntryDto {

    long id;

    String date;

    int week;

    int dayOfWeek;

    int morning;

    int afternoon;

    int evening;

    long account;

}
