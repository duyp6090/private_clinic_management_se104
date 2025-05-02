/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.MonthReport;

/**
 *
 * @author iset1enloc
 */
@Repository
public class MonthReportRepositoryJdbc {

    private final JdbcTemplate jdbcTemplate;

    public MonthReportRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<MonthReport> findByThangAndNam(int thang, Year nam) {
        String sql = "SELECT * FROM tbl_month_report WHERE thang = ? AND nam = ?";
        List<MonthReport> reports = jdbcTemplate.query(sql, new Object[]{thang, nam}, new BeanPropertyRowMapper<>(MonthReport.class));
        return reports.stream().findFirst();
    }

    public boolean existsByThangAndNam(int thang, Year nam) {
        String sql = "SELECT COUNT(*) FROM tbl_month_report WHERE thang = ? AND nam = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, thang, nam);
        return count != null && count > 0;
    }

    public void save(MonthReport report) {
        String sql = "INSERT INTO tbl_month_report (thang, nam, total_revenue) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, report.getThang(), report.getNam(), report.getTotalRevenue());
    }
}
