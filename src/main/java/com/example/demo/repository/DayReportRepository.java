package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.DayReport;

public interface DayReportRepository extends JpaRepository<DayReport, Long> {
    // Add any custom query methods if needed
    // For example, you can add methods to find reports by date or other criteria
    List<DayReport> findByMonthReport_MonthReportId(Long monthReportId);
}
