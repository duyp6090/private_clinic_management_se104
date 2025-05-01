package com.example.demo.service.service_implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MonthReport;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportRevenue.GetMonthReport;
import com.example.demo.repository.MonthReportRepository;
import com.example.demo.service.IMonthReport;

@Service
public class MonthReportImpl implements IMonthReport {
    private final MonthReportRepository monthReportRepository;

    public MonthReportImpl(MonthReportRepository monthReportRepository) {
        this.monthReportRepository = monthReportRepository;
    }

    @Override
    public ResultPaginationDTO getMonthReport(GetMonthReport getMonthReport) {
        // Create Result
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO pagination = new PaginationDTO();

        // Filtering, Sorting, Paging
        int page = getMonthReport.getPage();
        int size = getMonthReport.getSize();

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<MonthReport> monthReportPage = monthReportRepository.findAll(getMonthReport, pageable);

        // Return result
        pagination.setPage(page);
        pagination.setSize(size);
        pagination.setTotalPages(monthReportPage.getTotalPages());
        pagination.setTotalElements((int) monthReportPage.getTotalElements());

        result.setData(monthReportPage.getContent());
        result.setPaginationDTO(pagination);

        return result;
    }

}
