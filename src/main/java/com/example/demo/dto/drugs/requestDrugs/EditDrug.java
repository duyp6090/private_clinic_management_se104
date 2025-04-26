package com.example.demo.dto.drugs.requestDrugs;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class EditDrug {
    @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}0-9\\s'-]{1,50}$", message = "Drug name must contain at least one letter and only include letters, numbers, spaces, hyphens, and apostrophes (max 50 characters)")
    private String drugName;

    private String description;

    @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    @Max(value = 1000000, message = "Quantity must be less than or equal to 1000000")
    private Long quantity;

    @DecimalMin(value = "1.0", inclusive = true, message = "Import price must be greater than or equal to 1.0")
    @DecimalMax(value = "1000000.0", inclusive = true, message = "Import price must be less than or equal to 1,000,000.0")
    private Double importPrice;

    private LocalDate expirationDate;

    private Long unitId;

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Double importPrice) {
        this.importPrice = importPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

}
