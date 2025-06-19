package com.example.demo.dto.reportRevenue;

import java.time.Year;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetMonthReport {
    @Min(value = 1, message = "MONTH_INVALID")
    @Max(value = 12, message = "MONTH_INVALID")
    @NotNull(message = "YEAR_NOT_NULL")
    private int month;

    @Min(value = 2000, message = "YEAR_INVALID")
    @Max(value = 2100, message = "YEAR_INVALID")
    @NotNull(message = "MONTH_NOT_NULL")
    private Year year;

    @Min(value = 1, message = "PAGE_INVALID")
    private int page = 1;

    @Min(value = 1, message = "SIZE_ELEMENT_INVALID")
    private int size = 10;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
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
