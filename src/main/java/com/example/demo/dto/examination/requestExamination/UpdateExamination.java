package com.example.demo.dto.examination.requestExamination;

public class UpdateExamination {
    private Long staffId;
    private Long diseaseId;
    private String symptoms;
    private boolean isExam = true;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isExam() {
        return isExam;
    }

    public void setExam(boolean isExam) {
        this.isExam = isExam;
    }

}
