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
    private long paramId;
    private long numberPatientMax;
    private double examFee;
    private double drugFeePercent;

    public long getParamId() {
        return paramId;
    }

    public void setParamId(long paramId) {
        this.paramId = paramId;
    }

    public long getNumberPatientMax() {
        return numberPatientMax;
    }

    public void setNumberPatientMax(long numberPatientMax) {
        this.numberPatientMax = numberPatientMax;
    }

    public double getExamFee() {
        return examFee;
    }

    public void setExamFee(double examFee) {
        this.examFee = examFee;
    }

    public double getDrugFeePercent() {
        return drugFeePercent;
    }

    public void setDrugFeePercent(double drugFeePercent) {
        this.drugFeePercent = drugFeePercent;
    }

}
