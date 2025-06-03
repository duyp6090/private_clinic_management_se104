/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Doctor;
import com.example.demo.dto.doctor.registerDoctorRequest;

/**
 *
 * @author iset1enloc
 */
public interface IDoctorService {
    Doctor save(Doctor doctor);
    List<registerDoctorRequest> getAllDoctors();

}