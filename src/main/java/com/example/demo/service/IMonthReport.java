package com.example.demo.service;

import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportRevenue.GetMonthReport;

public interface IMonthReport {
    ResultPaginationDTO getMonthReport(GetMonthReport getMonthReport);
}
