package com.example.demo.dto.diseases;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateDiseasesDTO {
    @Size(min = 1, max = 50, message = "disease_name must be between 1 and 50 characters")
    @NotNull(message = "disease_name cannot be null")
    @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}0-9\\s'-]{1,50}$", message = "characters and can only contain letters, numbers, spaces, hyphens, and apostrophes")
    private String diseaseName;

    @Size(min = 1, max = 255, message = "description must be between 1 and 255 characters")
    @NotNull(message = "description cannot be null")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

}
