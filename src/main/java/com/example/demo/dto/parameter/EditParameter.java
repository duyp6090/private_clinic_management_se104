package com.example.demo.dto.parameter;

import jakarta.validation.constraints.Min;

public class EditParameter {
    // validate the number_patient_max is a number and not a string

    @Min(value = 0, message = "number_patient_max must be a positive number")
    private Long number_patient_max;

    @Min(value = 0, message = "number_patient_min must be a positive number")
    private Double exam_fee;

    @Min(value = 0, message = "number_patient_min must be a positive number")
    private Double drug_fee_percent;

    public Long getNumber_patient_max() {
        return number_patient_max;
    }

    public void setNumber_patient_max(Long number_patient_max) {
        this.number_patient_max = number_patient_max;
    }

    public Double getExam_fee() {
        return exam_fee;
    }

    public void setExam_fee(Double exam_fee) {
        this.exam_fee = exam_fee;
    }

    public Double getDrug_fee_percent() {
        return drug_fee_percent;
    }

    public void setDrug_fee_percent(Double drug_fee_percent) {
        this.drug_fee_percent = drug_fee_percent;
    }
}
