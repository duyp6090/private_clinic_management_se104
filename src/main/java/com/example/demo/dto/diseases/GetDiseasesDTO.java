package com.example.demo.dto.diseases;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class GetDiseasesDTO {
    private String diseaseName;
    @Pattern(regexp = "^([a-zA-Z0-9_]+,(asc|desc))(\\|[a-zA-Z0-9_]+,(asc|desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    @Min(value = 1, message = "Page number must be greater than or equal to 1")
    private Integer page = 1;

    @Min(value = 1, message = "Page size must be greater than or equal to 1")
    private Integer size = 10;

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
