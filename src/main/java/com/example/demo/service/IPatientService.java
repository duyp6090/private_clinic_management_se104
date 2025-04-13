/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Patients;

/**
 *
 * @author iset1enloc
 */
public interface IPatientService {
    Patients savePatient(Patients patient);
    List<Patients> getAllPatients();
    Optional<Patients> getPatientById(Long id);
    Patients updatePatient(Long id, Patients patient);
    void deletePatient(Long id);
}
