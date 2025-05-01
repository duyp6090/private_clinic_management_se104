package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportUsedDrugs.GetUsageReport;
import com.example.demo.service.service_implementation.DrugsUsageReportImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/drugs-usage-report")
public class AdminDrugsUsageReportController {
    private final DrugsUsageReportImpl drugsUsageReportImpl;

    public AdminDrugsUsageReportController(DrugsUsageReportImpl drugsUsageReportImpl) {
        this.drugsUsageReportImpl = drugsUsageReportImpl;
    }

    @GetMapping("/get-drugs-usage-report")
    public ResultPaginationDTO getDrugsUsageReport(@Valid @ModelAttribute GetUsageReport getUsageReport) {
        ResultPaginationDTO drugsUsageReport = this.drugsUsageReportImpl.getDrugsUsageReport(getUsageReport);
        return drugsUsageReport;
    }
}
