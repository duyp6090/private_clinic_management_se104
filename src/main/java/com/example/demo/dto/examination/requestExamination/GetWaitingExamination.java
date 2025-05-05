package com.example.demo.dto.examination.requestExamination;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GetWaitingExamination {
    @NotNull(message = "Examination date cannot be null")
    private LocalDate examinationDate;
    private Boolean gender;

    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private Integer page = 1;

    @Min(value = 1, message = "Size must be greater than or equal to 1")
    private Integer size = 10;

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate) {
        this.examinationDate = examinationDate;
    }

}
