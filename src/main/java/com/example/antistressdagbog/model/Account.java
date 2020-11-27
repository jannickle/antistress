package com.example.antistressdagbog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "therapist")
    private String therapist;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    public List<DiaryEntry> diaryentries;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTherapist() {
        return therapist;
    }

    public void setTherapist(String therapist) {
        this.therapist = therapist;
    }

    public List<DiaryEntry> getDiaryentries() {
        return diaryentries;
    }

    public void setDiaryentries(List<DiaryEntry> diaryEntries) {
        this.diaryentries = diaryEntries;
    }

}
