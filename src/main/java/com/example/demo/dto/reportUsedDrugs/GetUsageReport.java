package com.example.demo.dto.reportUsedDrugs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GetUsageReport {
    @NotNull(message = "month is required")
    private Integer month;

    @NotNull(message = "year is required")
    private Integer year;

    private String drugName;

    private Long unitId;

    @Min(value = 1, message = "page must be greater than or equal to 1")
    private Integer page = 1;

    @Min(value = 1, message = "size must be greater than or equal to 1")
    private Integer size = 10;

    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

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

}
