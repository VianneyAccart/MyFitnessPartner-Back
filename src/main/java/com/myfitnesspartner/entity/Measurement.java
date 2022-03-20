package com.myfitnesspartner.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @NotNull
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToMany
    @JoinTable(name = "measurement_has_parameters",
            joinColumns = @JoinColumn(name = "measurement_id"),
            inverseJoinColumns = @JoinColumn(name = "measurement_parameter_id"))
    private List<MeasurementParameters> measurementParameters;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MeasurementParameters> getMeasurementParameters() {
        return measurementParameters;
    }

    public void setMeasurementParameters(List<MeasurementParameters> measurementParameters) {
        this.measurementParameters = measurementParameters;
    }
}
