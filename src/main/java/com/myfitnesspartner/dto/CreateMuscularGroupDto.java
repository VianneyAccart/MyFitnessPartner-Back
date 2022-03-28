package com.myfitnesspartner.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateMuscularGroupDto {

    @NotBlank
    @Size(min = 1, max = 150)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
