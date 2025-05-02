package com.example.demo.dto.reportRevenue;

import java.time.Year;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class GetMonthReport {
    @Min(value = 1, message = "Month must be greater than or equal to 1")
    @Max(value = 12, message = "Month must be less than or equal to 12")
    @NotNull(message = "Month is required")
    private int month;

    @Min(value = 2000, message = "Year must be greater than or equal to 2000")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    @NotNull(message = "Year is required")
    private Year year;

    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private int page = 1;

    @Min(value = 1, message = "Size must be greater than or equal to 1")
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
