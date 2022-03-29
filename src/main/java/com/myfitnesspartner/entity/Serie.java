package com.myfitnesspartner.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private int repetitions;

    @NotNull
    @PositiveOrZero
    private Double weight;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "session_exercise_id")
    private SessionExercise sessionExercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public SessionExercise getSessionExercise() {
        return sessionExercise;
    }

    public void setSessionExercise(SessionExercise sessionExercise) {
        this.sessionExercise = sessionExercise;
    }
}
