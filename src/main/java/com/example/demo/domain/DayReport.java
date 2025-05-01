package com.example.demo.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "day_report", uniqueConstraints = @UniqueConstraint(name = "unique_day_month_year", columnNames = {
        "date", "month_report_id" }))
public class DayReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dayReportId;

    @ManyToOne
    @JoinColumn(name = "month_report_id", referencedColumnName = "monthReportId")
    @JsonBackReference
    private MonthReport monthReport;
    private LocalDate date;
    private int numberOfPatients;
    private double revenue;
    private double ratio;

    public long getDayReportId() {
        return dayReportId;
    }

    public void setDayReportId(long dayReportId) {
        this.dayReportId = dayReportId;
    }

    public MonthReport getMonthReport() {
        return monthReport;
    }

    public void setMonthReport(MonthReport monthReport) {
        this.monthReport = monthReport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

}
