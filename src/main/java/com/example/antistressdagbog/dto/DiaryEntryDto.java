package com.example.antistressdagbog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryEntryDto {

    String date;

    int dayOfWeek;

    int morning;

    int afternoon;

    int evening;

}
