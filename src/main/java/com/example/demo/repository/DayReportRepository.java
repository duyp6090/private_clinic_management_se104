/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.DayReport;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface DayReportRepository extends JpaRepository<DayReport, Long> {

    // Find all DayReports by month and year using JPQL
    @Query("SELECT d FROM DayReport d WHERE d.thang = :thang AND d.nam = :nam")
    List<DayReport> findByMonthAndYear(@Param("thang") int thang, @Param("nam") Year nam);

    // Check if DayReport exists by day, month, and year using JPQL
    @Query("SELECT COUNT(d) > 0 FROM DayReport d WHERE d.ngay = :ngay AND d.thang = :thang AND d.nam = :nam")
    boolean existsByNgayAndThangAndNam(@Param("ngay") int ngay, @Param("thang") int thang, @Param("nam") Year nam);
}
