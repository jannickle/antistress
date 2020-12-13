package com.example.antistressdagbog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_entry")
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diaryentry")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account", nullable = false)
    private Account account;

    private LocalDate date;
    private Integer dayOfWeek;
    private Integer week;
    private Integer morning;
    private Integer afternoon;
    private Integer evening;
    private Integer sleep;
    private String note1;
    private String note2;
    private String note3;
    private String noteSleep;

}
