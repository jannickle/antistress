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

    Long id;

    String date;

    Integer week;

    Integer dayOfWeek;

    Integer morning;

    Integer afternoon;

    Integer evening;

    Integer sleep;

    Long account;

    String note1;

    String note2;

    String note3;

}
