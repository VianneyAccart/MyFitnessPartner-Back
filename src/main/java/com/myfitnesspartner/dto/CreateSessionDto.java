package com.myfitnesspartner.dto;

import com.myfitnesspartner.entity.Exercise;
import com.myfitnesspartner.entity.Feeling;
import com.myfitnesspartner.entity.User;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class CreateSessionDto {

    @CreatedDate
    @NotNull
    private LocalDate date;

    @Size(min = 1, max = 100)
    private String name;

    @Size(min = 1, max = 250)
    private String note;

    @NotNull
    private Feeling feeling;

    @NotNull
    private User user;

    @NotNull
    private List<Exercise> exercises;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
