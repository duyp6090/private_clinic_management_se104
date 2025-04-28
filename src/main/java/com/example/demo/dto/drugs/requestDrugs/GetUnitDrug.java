package com.example.demo.dto.drugs.requestDrugs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public class GetUnitDrug {
    private String unitName;

    @Pattern(regexp = "^[a-zA-Z0-9_]+,(asc,desc)(\\|[a-zA-Z0-9_]+,(asc,desc))*$", message = "Sort must be in the format 'field,order'")
    private String sort;

    @Min(value = 1, message = "Page number must be greater than or equal to 0")
    private Integer page = 1;

    @Min(value = 1, message = "Size must be greater than or equal to 1")
    private Integer size = 10;

    public String getUnitName() {
        return unitName;
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

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

}
