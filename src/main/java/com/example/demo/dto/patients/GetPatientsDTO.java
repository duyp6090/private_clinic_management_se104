package com.example.demo.dto.patients;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class GetPatientsDTO {
    private String fullName;
    private Boolean gender;
    private String yearOfBirth;

    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private Integer page = 1;

    @Min(value = 1, message = "Size must be greater than or equal to 1")
    private Integer size = 10;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
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

}
