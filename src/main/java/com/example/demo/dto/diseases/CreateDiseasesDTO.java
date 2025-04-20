package com.example.demo.dto.diseases;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateDiseasesDTO {
    @Size(min = 1, max = 50, message = "disease_name must be between 1 and 50 characters")
    @NotNull(message = "disease_name cannot be null")
    private String disease_name;

    @Size(min = 1, max = 255, message = "description must be between 1 and 255 characters")
    @NotNull(message = "description cannot be null")
    private String description;

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
