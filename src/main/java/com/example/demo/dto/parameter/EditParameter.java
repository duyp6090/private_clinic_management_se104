package com.example.demo.dto.parameter;

import jakarta.validation.constraints.Min;

public class EditParameter {
    // validate the number_patient_max is a number and not a string

    @Min(value = 0, message = "number_patient_max must be a positive number")
    private Long numberPatientMax;

    @Min(value = 0, message = "number_patient_min must be a positive number")
    private Double examFee;

    @Min(value = 0, message = "number_patient_min must be a positive number")
    private Double drugFeePercent;

    public Long getNumberPatientMax() {
        return numberPatientMax;
    }

    public void setNumberPatientMax(Long numberPatientMax) {
        this.numberPatientMax = numberPatientMax;
    }

    public Double getExamFee() {
        return examFee;
    }

    public void setExamFee(Double examFee) {
        this.examFee = examFee;
    }

    public Double getDrugFeePercent() {
        return drugFeePercent;
    }

    public void setDrugFeePercent(Double drugFeePercent) {
        this.drugFeePercent = drugFeePercent;
    }

}
