package com.example.demo.dto.examination.responseExamination;

import java.time.LocalDate;

public class ResponsePatientExamination {
    private long examId;
    private String fullName;
    private LocalDate examinationDate;
    private String nameDisease;
    private String symptoms;

    public ResponsePatientExamination(
            long examId,
            String fullName,
            LocalDate examinationDate,
            String nameDisease,
            String symptoms) {
        this.examId = examId;
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

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

}
