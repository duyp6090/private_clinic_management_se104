/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.doctor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author iset1enloc
 */

 @Getter
 @NoArgsConstructor
 @AllArgsConstructor
public class registerDoctorRequest {

    private Long doctorId;

    @JsonProperty("username")
    public String userName;
    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("password")
    public String password;

    @JsonProperty("specialization")
    public String specialization;

    @JsonProperty("qualification")
    public String qualification;

    @JsonProperty("yearsOfExperience")
    public int yearsOfExperience;

    private List<String> role;
}