package com.myfitnesspartner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 150)
    private String name;

    @NotBlank
    @Size(min = 1, max = 1500)
    private String description;

    @NotNull
    private LocalDateTime lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise")
    private List<Serie> series;

    @JsonIgnore
    @ManyToMany(mappedBy = "exercises")
    private List<Session> sessions;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "muscular_group_id")
    private MuscularGroup muscularGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise")
    private List<Record> records;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;

    public static Comparator<Exercise> sortByAscendingName = Comparator.comparing(s -> s.name);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public MuscularGroup getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(MuscularGroup muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
