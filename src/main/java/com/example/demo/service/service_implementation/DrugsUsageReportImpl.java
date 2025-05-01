package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DrugsUsageReport;
import com.example.demo.dto.common.PaginationDTO;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.reportUsedDrugs.GetUsageReport;
import com.example.demo.repository.DrugsUsageReportRepository;
import com.example.demo.service.IDrugsUsageReport;

@Service
public class DrugsUsageReportImpl implements IDrugsUsageReport {
    private final DrugsUsageReportRepository drugsUsageReportRepository;
    private final List<String> VALID_SORT_FIELDS = List.of("drugName", "unitName", "usageNumber", "year", "month");

    public DrugsUsageReportImpl(DrugsUsageReportRepository drugsUsageReportRepository) {
        this.drugsUsageReportRepository = drugsUsageReportRepository;
    }

    @Override
    public ResultPaginationDTO getDrugsUsageReport(GetUsageReport getUsageReport) {
        // Create result to return
        ResultPaginationDTO result = new ResultPaginationDTO();
        PaginationDTO paginationDTO = new PaginationDTO();

        int page = getUsageReport.getPage();
        int size = getUsageReport.getSize();
        List<Sort.Order> orders = new ArrayList<>();

        // Check sort is valid
        if (getUsageReport.getSort() != null) {
            String[] sortFields = getUsageReport.getSort().split("\\|");
            for (String sortField : sortFields) {
                String[] sortParams = sortField.split(",");
                String field = sortParams[0];
                String typeSort = sortParams[1];

                if (VALID_SORT_FIELDS.contains(field)) {
                    Sort.Order order = new Sort.Order(Sort.Direction.fromString(typeSort), field);
                    orders.add(order);
                }
            }
        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(orders));
        Page<DrugsUsageReport> drugsUsageReportPage = drugsUsageReportRepository.findAll(getUsageReport, pageable);

        // Set result
        paginationDTO.setPage(page);
        paginationDTO.setSize(size);
        paginationDTO.setTotalPages(drugsUsageReportPage.getTotalPages());
        paginationDTO.setTotalElements((int) drugsUsageReportPage.getTotalElements());

        result.setPaginationDTO(paginationDTO);
        result.setData(drugsUsageReportPage.getContent());

        return result;
    }

}
