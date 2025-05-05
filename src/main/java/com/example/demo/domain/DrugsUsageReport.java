package com.example.demo.domain;

import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "drugs_usage_report", uniqueConstraints = @UniqueConstraint(name = "unique_drug_month_year", columnNames = {
        "drug_id", "month", "year" }))
public class DrugsUsageReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportUsageId;

    @ManyToOne
    @JoinColumn(name = "drug_id", referencedColumnName = "drugId")
    private Drugs drug;

    private int month;
    private Year year;

    private long usageNumber = 0;

    public long getReportUsageId() {
        return reportUsageId;
    }

    public void setReportUsageId(long reportUsageId) {
        this.reportUsageId = reportUsageId;
    }

    public Drugs getDrug() {
        return drug;
    }

    public void setDrug(Drugs drugs) {
        this.drug = drugs;
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

    public long getUsageNumber() {
        return usageNumber;
    }

    public void setUsageNumber(long usageNumber) {
        this.usageNumber = usageNumber;
    }

}
