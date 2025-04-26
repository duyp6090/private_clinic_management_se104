package com.example.demo.dto.drugs.requestDrugs;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EditUnitDrug {
    @Size(max = 50, message = "Unit name must be less than 50 characters")
    @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}0-9\\s'-]{1,50}$", message = "characters and can only contain letters, numbers, spaces, hyphens, and apostrophes")
    private String unitName;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
