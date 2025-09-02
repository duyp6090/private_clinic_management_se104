package com.example.demo.dto.examination.responseExamination;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExaminationRecord {
    private long examId;
    private String fullName;
    private LocalDate examinationDate;
    private String symptoms;
    private String diseaseName;
    private List<ResponseDrugsRecord> examinationDetails;
    private Boolean isExam;

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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<ResponseDrugsRecord> getExaminationDetails() {
        return examinationDetails;
    }

    public void setExaminationDetails(List<ResponseDrugsRecord> examinationDetails) {
        this.examinationDetails = examinationDetails;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

}
