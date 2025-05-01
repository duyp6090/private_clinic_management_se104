/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import jakarta.persistence.Entity;

/**
 *
 * @author iset1enloc
 */
@Entity
public class Doctor extends User {
    private String qualification;
    private int yearsOfExperience;
    private String specialization;

    public Doctor(){}
    public Doctor(String qualification, int yearsOfExperience, String specialization) {
        this.qualification = qualification;
        this.yearsOfExperience = yearsOfExperience;
        this.specialization = specialization;
    }
    public Doctor(String username, String email, String password, String fullName, String address, String phone,
            String qualification, int yearsOfExperience, String specialization) {
        super(username, email, password, fullName, address, phone);
        this.qualification = qualification;
        this.yearsOfExperience = yearsOfExperience;
        this.specialization = specialization;
    }
    
    @Override
    public String toString() {
        return super.toString()+"Doctor [qualification=" + qualification + ", yearsOfExperience=" + yearsOfExperience
                + ", specialization=" + specialization + "]";
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}
