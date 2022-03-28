package com.myfitnesspartner.entity;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstname;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastname;

    @NotBlank
    @Email
    @Size(min = 5, max = 120)
    private String email;

    @NotBlank
    @Size(min = 8, max = 120)
    private String password;

    @NotNull
    @Past
    private LocalDate birthdate;

    @PositiveOrZero
    private int height;

    @PositiveOrZero
    private Double weight;

    @CreatedDate
    @NotNull
    private LocalDate creationDate;

    @UpdateTimestamp
    private LocalDateTime lastLoginDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @OneToOne
    @JoinColumn(name = "picture_id")
    private Picture profilePicture;

    @OneToMany(mappedBy = "user")
    private List<Weighing> weighings;

    @OneToMany(mappedBy = "user")
    private List<Session> sessions;

    @OneToMany(mappedBy = "user")
    private List<Record> records;

    @OneToMany(mappedBy = "user")
    private List<Measurement> measurements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Weighing> getWeighings() {
        return weighings;
    }

    public void setWeighings(List<Weighing> weighings) {
        this.weighings = weighings;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }
}
