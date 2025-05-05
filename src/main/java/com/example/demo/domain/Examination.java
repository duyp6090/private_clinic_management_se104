package com.example.demo.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "examinations")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long examinationId;
    private String symptoms;
    private double examFee;
    private double drugsFee = 0.0;
    private boolean isExam = true;
    private LocalDate examinationDate = LocalDate.now();

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExaminationDetail> examinationDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "diseaseId")
    private Diseases disease;

    public long getExaminationId() {
        return examinationId;
    }

    public void setExaminationId(long examinationId) {
        this.examinationId = examinationId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public double getExamFee() {
        return examFee;
    }

    public void setExamFee(double examFee) {
        this.examFee = examFee;
    }

    public double getDrugsFee() {
        return drugsFee;
    }

    public void setDrugsFee(double drugsFee) {
        this.drugsFee = drugsFee;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean isExam) {
        this.isExam = isExam;
    }

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDate examinationDate) {
        this.examinationDate = examinationDate;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Diseases getDisease() {
        return disease;
    }

    public void setDisease(Diseases disease) {
        this.disease = disease;
    }

    public List<ExaminationDetail> getExaminationDetails() {
        return examinationDetails;
    }

    public void setExaminationDetails(List<ExaminationDetail> examinationDetails) {
        this.examinationDetails = examinationDetails;
    }

}
