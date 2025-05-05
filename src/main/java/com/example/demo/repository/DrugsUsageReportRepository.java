package com.example.demo.repository;

import java.time.Year;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Drugs;
import com.example.demo.domain.DrugsUsageReport;
import com.example.demo.dto.reportUsedDrugs.GetUsageReport;

public interface DrugsUsageReportRepository extends JpaRepository<DrugsUsageReport, Long> {
    // Get drug report by filtering information
    @Query("""
               SELECT r
               FROM DrugsUsageReport r
               WHERE (:#{#filter.month} IS NULL OR r.month = :#{#filter.month})
                AND (:#{#filter.year} IS NULL OR r.year = :#{#filter.year})
                AND (:#{#filter.drugName} IS NULL OR r.drug.drugName LIKE CONCAT('%', :#{#filter.drugName}, '%'))
                AND (:#{#filter.unitId} IS NULL OR r.drug.drugsUnit.unitId = :#{#filter.unitId})
            """)
    Page<DrugsUsageReport> findAll(@Param("filter") GetUsageReport filter, Pageable pageable);

    // Find by drug and month and year
    Optional<DrugsUsageReport> findByDrugAndMonthAndYear(Drugs drug, int month, Year year);
}
