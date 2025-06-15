package com.example.demo.dto.patients;

import java.time.Year;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EditPatientDTO {
    @Size(min = 1, max = 50, message = "full_name must be between 1 and 50 characters")
    @NotBlank(message = "FULL_NAME_NOT_BLANK")
    private String fullName;

    private Boolean gender;

    @NotBlank(message = "ADDRESS_NOT_BLANK")
    private String address;

    private Year yearOfBirth;

    @NotBlank(message = "PHONE_NUMBER_NOT_BLANK")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Invalid Vietnamese phone number format")
    private String phoneNumber;

    @NotBlank(message = "RESIDENTAL_IDENTITY_NOT_BLANK")
    @Pattern(regexp = "^[0-9]{12}$", message = "Identity number must be exactly 12 digits")
    private String residentalIdentity;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Year getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Year yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
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
