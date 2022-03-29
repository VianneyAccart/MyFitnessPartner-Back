package com.myfitnesspartner.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ExerciseDto {

    @NotNull
    private Long muscularGroupId;

    @NotNull
    private Long exerciseId;

    @NotNull
    private List<SerieDto> series;

    public Long getMuscularGroupId() {
        return muscularGroupId;
    }

    public void setMuscularGroupId(Long muscularGroupId) {
        this.muscularGroupId = muscularGroupId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public List<SerieDto> getSeries() {
        return series;
    }

    public void setSeries(List<SerieDto> series) {
        this.series = series;
    }
}
