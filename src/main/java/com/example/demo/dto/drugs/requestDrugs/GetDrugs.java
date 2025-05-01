package com.example.demo.dto.drugs.requestDrugs;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class GetDrugs {
    private String drugName;

    @Min(value = 0, message = "Quantity must be greater than or equal to 1")
    @Max(value = 1000000, message = "Quantity must be less than or equal to 1000000")
    private Long minQuantity = 0l;

    @Min(value = 0, message = "Quantity must be greater than or equal to 1")
    @Max(value = 10000000000l, message = "Quantity must be less than or equal to 1000000")
    private Long maxQuantity = 10000000000l;

    @DecimalMin(value = "0.0", inclusive = true, message = "Import price must be greater than or equal to 1.0")
    @DecimalMax(value = "1000000.0", inclusive = true, message = "Import price must be less than or equal to 1,000,000.0")
    private Double minImportPrice = 0.0;

    @DecimalMin(value = "0.0", inclusive = true, message = "Import price must be greater than or equal to 1.0")
    @DecimalMax(value = "1000000000.0", inclusive = true, message = "Import price must be less than or equal to 1,000,000.0")
    private Double maxImportPrice = 1000000000.0;

    private LocalDate minExpirationDate;
    private LocalDate maxExpirationDate;

    private Long unitId;

    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private Integer page = 1;

    @Min(value = 1, message = "Size must be greater than or equal to 1")
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Long getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Long getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Double getMinImportPrice() {
        return minImportPrice;
    }

    public void setMinImportPrice(Double minImportPrice) {
        this.minImportPrice = minImportPrice;
    }

    public Double getMaxImportPrice() {
        return maxImportPrice;
    }

    public void setMaxImportPrice(Double maxImportPrice) {
        this.maxImportPrice = maxImportPrice;
    }

    public LocalDate getMinExpirationDate() {
        return minExpirationDate;
    }

    public void setMinExpirationDate(LocalDate minExpirationDate) {
        this.minExpirationDate = minExpirationDate;
    }

    public LocalDate getMaxExpirationDate() {
        return this.maxExpirationDate;
    }

    public void setMaxExpirationDate(LocalDate maxExpirationDate) {
        this.maxExpirationDate = maxExpirationDate;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

}
