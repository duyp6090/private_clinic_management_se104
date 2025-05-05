package com.example.demo.dto.examination.responseExamination;

import java.time.Year;

public class ResponseWaitingExamination {
    private String fullName;
    private boolean gender;
    private Year yearOfBirth;
    private String address;

    public ResponseWaitingExamination(
            String fullName,
            boolean gender,
            Year yearOfBirth,
            String address) {
        this.fullName = fullName;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Year getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Year yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
