package com.example.demo.domain;

import java.time.Year;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long patientId;
    private String fullName;
    private boolean gender;
    private Year yearOfBirth;
    private String address;
    private String phoneNumber;
    private String residentalIdentity;

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getResidentalIdentity() {
        return residentalIdentity;
    }

    public void setResidentalIdentity(String residentalIdentity) {
        this.residentalIdentity = residentalIdentity;
    }

}
