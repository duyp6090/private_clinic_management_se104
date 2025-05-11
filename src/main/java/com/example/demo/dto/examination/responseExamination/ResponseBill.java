package com.example.demo.dto.examination.responseExamination;

import java.time.LocalDate;

public class ResponseBill {
    private long examId;
    private String fullName;
    private LocalDate examinationDate;
    private double drugsFee;
    private double examFee;

    public ResponseBill(long examId, String fullName, LocalDate examinationDate, double drugsFee, double examFee) {
        this.examId = examId;
        this.fullName = fullName;
        this.examinationDate = examinationDate;
        this.drugsFee = drugsFee;
        this.examFee = examFee;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate) {
        this.examinationDate = examinationDate;
    }

    public double getDrugsFee() {
        return drugsFee;
    }

    public void setDrugsFee(double drugsFee) {
        this.drugsFee = drugsFee;
    }

    public double getExamFee() {
        return examFee;
    }

    public void setExamFee(double examFee) {
        this.examFee = examFee;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

}
