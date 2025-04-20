package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parameter")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long param_id;
    private long number_patient_max;
    private double exam_fee;
    private double drug_fee_percent;

    public long getParam_id() {
        return param_id;
    }

    public void setParam_id(long param_id) {
        this.param_id = param_id;
    }

    public long getNumber_patient_max() {
        return number_patient_max;
    }

    public void setNumber_patient_max(long number_patient_max) {
        this.number_patient_max = number_patient_max;
    }

    public double getExam_fee() {
        return exam_fee;
    }

    public void setExam_fee(double exam_fee) {
        this.exam_fee = exam_fee;
    }

    public double getDrug_fee_percent() {
        return drug_fee_percent;
    }

    public void setDrug_fee_percent(double drug_fee_percent) {
        this.drug_fee_percent = drug_fee_percent;
    }

}
