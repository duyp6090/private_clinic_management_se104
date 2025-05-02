package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.MonthReport;
import com.example.demo.dto.reportRevenue.GetMonthReport;

public interface MonthReportRepository extends JpaRepository<MonthReport, Long> {
    // Get Month Report filtering
    @Query("""
               SELECT m
               FROM MonthReport m
               WHERE (:#{#filter.month} IS NULL OR m.month = :#{#filter.month})
               AND (:#{#filter.year} IS NULL OR m.year = :#{#filter.year})
            """)
    Page<MonthReport> findAll(@Param("filter") GetMonthReport filter, Pageable pageable);

}
