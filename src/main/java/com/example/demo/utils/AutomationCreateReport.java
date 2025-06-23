package com.example.demo.utils;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.domain.DayReport;
import com.example.demo.domain.Drugs;
import com.example.demo.domain.DrugsUsageReport;
import com.example.demo.domain.Examination;
import com.example.demo.domain.ExaminationDetail;
import com.example.demo.domain.MonthReport;
import com.example.demo.repository.DayReportRepository;
import com.example.demo.repository.DrugsUsageReportRepository;
import com.example.demo.repository.ExaminationRepository;
import com.example.demo.repository.MonthReportRepository;

@Component
public class AutomationCreateReport {
    private final ExaminationRepository examinationRepository;
    private final MonthReportRepository monthReportRepository;
    private final DayReportRepository dayReportRepository;
    private final DrugsUsageReportRepository drugsUsageReportRepository;

    public AutomationCreateReport(ExaminationRepository examinationRepository, MonthReportRepository monthReport,
            DayReportRepository dayReportRepository, DrugsUsageReportRepository drugsUsageReportRepository) {
        this.dayReportRepository = dayReportRepository;
        this.monthReportRepository = monthReport;
        this.examinationRepository = examinationRepository;
        this.drugsUsageReportRepository = drugsUsageReportRepository;
    }

    // @Scheduled(cron = "0 0 18 * * ?")
    @Scheduled(cron = "0 15 23 20 6 ?")
    public void reportCurrentTime() {
        // Calculate day report
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        Year year = Year.now();

        // Get all examination for today
        int numberOfPatients = this.examinationRepository.countByExaminationDate(currentDate);
        double revenue = this.examinationRepository.sumRevenueByExaminationDate(currentDate);
        double ratio = 0.0;

        // Create day report
        MonthReport monthReport = this.monthReportRepository.findByMonthAndYear(currentDate.getMonthValue(), Year.now())
                .orElseThrow(() -> new RuntimeException("Month report not found for the current month."));
        DayReport dayReport = new DayReport();
        dayReport.setDate(currentDate);
        dayReport.setNumberOfPatients(numberOfPatients);
        dayReport.setRevenue(revenue);
        dayReport.setRatio(ratio);
        dayReport.setMonthReport(monthReport);

        this.dayReportRepository.save(dayReport);

        // Calculate and update drug report every day
        List<Examination> examinations = this.examinationRepository.findByExaminationDate(currentDate);
        System.out.println("Testing:" + examinations);

        for (Examination examination : examinations) {
            List<ExaminationDetail> examinationDetails = examination.getExaminationDetails();
            for (ExaminationDetail examinationDetail : examinationDetails) {
                Drugs drug = examinationDetail.getDrugs();
                long usedNumber = examinationDetail.getQuantity();
                System.out.println("Testing:" + examinationDetail);
                System.out.println("Testing:" + examinationDetail);
                // Check drug report is exist
                DrugsUsageReport drugsUsageReport = this.drugsUsageReportRepository
                        .findByDrugAndMonthAndYear(drug, month, year).orElse(null);

                drugsUsageReport = drugsUsageReport == null ? new DrugsUsageReport() : drugsUsageReport;
                // Update drug report
                drugsUsageReport.setMonth(month);
                drugsUsageReport.setYear(year);
                drugsUsageReport.setDrug(drug);
                drugsUsageReport.setUsageNumber(drugsUsageReport.getUsageNumber() + usedNumber);

                // Save drug report
                this.drugsUsageReportRepository.save(drugsUsageReport);
            }
        }

    }

    // @Scheduled(cron = "0 0 1 1 * ?")
    @Scheduled(cron = "0 35 22 20 6 ?")
    public void createReportMonth() {
        // Get current month and year
        int month = LocalDate.now().getMonthValue();
        Year year = Year.now();

        // Create month report
        MonthReport monthReport = new MonthReport();
        monthReport.setMonth(month);
        monthReport.setYear(year);
        monthReport.setTotalRevenue(0.0); // Initialize total revenue to 0

        this.monthReportRepository.save(monthReport);
    }

    // @Scheduled(cron = "0 0 23 L * ?")
    @Scheduled(cron = "0 40 22 20 6 ?")
    public void updateReportMonth() {
        // Get month report for the current month
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        Year year = Year.now();

        MonthReport monthReport = this.monthReportRepository.findByMonthAndYear(month, year)
                .orElseThrow(() -> new RuntimeException("Month report not found for the current month."));

        List<DayReport> dayReports = monthReport.getDayReports();

        double totalRevenue = 0.0;
        if (dayReports != null && !dayReports.isEmpty()) {
            // Calculate total revenue for the month
            double calTotalRevenue = dayReports.stream().mapToDouble(DayReport::getRevenue).sum();
            totalRevenue = calTotalRevenue;

            // Set ratio for each day report
            if (totalRevenue > 0)
                dayReports.stream().forEach(dayReport -> dayReport.setRatio(dayReport.getRevenue() / calTotalRevenue));
        }
        monthReport.setTotalRevenue(totalRevenue);

        // Save the updated month report and day reports
        this.monthReportRepository.save(monthReport);
    }
}
