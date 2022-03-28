package com.myfitnesspartner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CreateExerciseDto {

    @NotBlank
    @Size(min = 1, max = 150)
    private String name;

    @NotBlank
    @Size(min = 1, max = 1500)
    private String description;

    @NotNull
    private Long muscularGroupId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMuscularGroupId() {
        return muscularGroupId;
    }

    public void setMuscularGroupId(Long muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }
}
