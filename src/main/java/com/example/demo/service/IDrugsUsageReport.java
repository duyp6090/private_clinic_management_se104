package com.example.demo.service;

import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportUsedDrugs.GetUsageReport;

public interface IDrugsUsageReport {
    // Get drug report
    ResultPaginationDTO getDrugsUsageReport(GetUsageReport getUsageReport);
}
