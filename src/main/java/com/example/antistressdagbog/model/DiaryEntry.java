package com.example.antistressdagbog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "diary_entry")
public class DiaryEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diaryentry")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;

    private Date date;
    private Integer dayOfWeek;
    private Integer morning;
    private Integer afternoon;
    private Integer evening;
    private Integer sleep;
    private String note1;
    private String note2;
    private String note3;
    private String noteSleep;

}
