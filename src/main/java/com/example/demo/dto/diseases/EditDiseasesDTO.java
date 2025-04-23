package com.example.demo.dto.diseases;

import jakarta.validation.constraints.Size;

public class EditDiseasesDTO {
    @Size(min = 1, max = 50, message = "disease_name must be between 1 and 50 characters")
    private String diseaseName;

    @Size(min = 1, max = 255, message = "description must be between 1 and 255 characters")
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
