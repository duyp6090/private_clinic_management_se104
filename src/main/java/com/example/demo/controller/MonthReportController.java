package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportRevenue.GetMonthReport;
import com.example.demo.service.IMonthReport;

@RestController
@RequestMapping("/api/public/month-report")
public class MonthReportController {
    private final IMonthReport monthReportService;

    public MonthReportController(IMonthReport monthReportService) {
        this.monthReportService = monthReportService;
    }

    // Get Month Report filtering
    @GetMapping("/get-month-report")
    public ResultPaginationDTO getMonthReport(@ModelAttribute GetMonthReport getMonthReport) {
        ResultPaginationDTO result = monthReportService.getMonthReport(getMonthReport);
        return result;
    }
}
