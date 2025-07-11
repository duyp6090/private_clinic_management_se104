package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Patients;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.patients.GetPatientsDTO;

public interface IPatientService {
    // Get all patient by full_name, gender, year_of_birth
    public ResultPaginationDTO getPatients(GetPatientsDTO getPatientsDTO);

    // Get patient by id
    public Patients getPatientByPatientId(Long id);

    // Find patient by phone_number or residental_identity
    public List<Patients> findByPhoneNumberOrResidentalIdentity(String phoneNumber, String residentalIdentity);

    public boolean existsByPhoneNumberOrResidentalIdentity(String phoneNumber, String residentalIdentity, Long id);

    // Check exist patient by id
    public boolean existsByPatientId(Long id);

    // Create and edit patient
    public Patients savePatient(Patients patient);

    // Delete patient by id
    public void deletePatientByPatientId(Long id);
}
