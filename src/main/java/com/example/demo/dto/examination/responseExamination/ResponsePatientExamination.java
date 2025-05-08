package com.example.demo.dto.examination.responseExamination;

import java.time.LocalDate;

public class ResponsePatientExamination {
    private String fullName;
    private LocalDate examinationDate;
    private String nameDisease;
    private String symptoms;

    public ResponsePatientExamination(
            String fullName,
            LocalDate examinationDate,
            String nameDisease,
            String symptoms) {
        this.fullName = fullName;
        this.examinationDate = examinationDate;
        this.nameDisease = nameDisease;
        this.symptoms = symptoms;
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

    public String getNameDisease() {
        return nameDisease;
    }

    public void setNameDisease(String nameDisease) {
        this.nameDisease = nameDisease;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

}
