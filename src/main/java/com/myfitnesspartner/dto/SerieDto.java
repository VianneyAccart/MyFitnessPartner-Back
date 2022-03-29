package com.myfitnesspartner.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SerieDto {

    @NotNull
    @Min(1)
    private int repetitions;

    @NotNull
    @Min(0)
    private Double weight;

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
}
