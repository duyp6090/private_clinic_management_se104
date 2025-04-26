package com.example.demo.dto.drugs.responseDrugs;

import java.time.LocalDate;

public class ResponseGetDrugs {
    private Long drugId;
    private String drugName;
    private String description;
    private long quantity;
    private double importPrice;
    private LocalDate expirationDate;
    private String unitName;
    private Long unitId;
    private String unitDescription;

    public ResponseGetDrugs(
            Long drugId,
            String drugName,
            String description,
            long quantity,
            double importPrice,
            LocalDate expirationDate,
            String unitName,
            long unitId,
            String unitDescription) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.description = description;
        this.quantity = quantity;
        this.importPrice = importPrice;
        this.expirationDate = expirationDate;
        this.unitName = unitName;
        this.unitId = unitId;
        this.unitDescription = unitDescription;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

}
