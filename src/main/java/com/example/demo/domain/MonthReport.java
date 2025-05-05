package com.example.demo.domain;

import java.time.Year;
import java.util.List;

import org.hibernate.query.sqm.CastType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "month_report", uniqueConstraints = @UniqueConstraint(name = "unique_month_year", columnNames = { "month",
        "year" }))

public class MonthReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long monthReportId;
    private int month;
    private Year year;
    private double totalRevenue;

    @OneToMany(mappedBy = "monthReport", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DayReport> dayReports;

    public long getMonthReportId() {
        return monthReportId;
    }

    public void setMonthReportId(long monthReportId) {
        this.monthReportId = monthReportId;
    }

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

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<DayReport> getDayReports() {
        return dayReports;
    }

    public void setDayReports(List<DayReport> dayReports) {
        this.dayReports = dayReports;
    }

}
