package com.example.demo.dto.examination.requestExamination;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class GetPatientExamination {
    private String fullName;
    private LocalDate examinationDate;
    private Long diseaseId;

    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "SORT_INVALID")
    private String sort;

    @Min(value = 1, message = "PAGE_INVALID")
    private Integer page = 1;

    @Min(value = 1, message = "SIZE_ELEMENT_INVALID")
    private Integer size = 10;

    public String getFullName() {
        return fullName;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate) {
        this.examinationDate = examinationDate;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

}
