package com.myfitnesspartner.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @NotNull
    private LocalDate date;

    @Size(max = 100)
    private String name;

    @Size(max = 250)
    private String note;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "feeling_id")
    private Feeling feeling;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "session")
    private List<SessionExercise> sessionExercises;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SessionExercise> getSessionExercises() {
        return sessionExercises;
    }

    public void setSessionExercises(List<SessionExercise> sessionExercises) {
        this.sessionExercises = sessionExercises;
    }
}
