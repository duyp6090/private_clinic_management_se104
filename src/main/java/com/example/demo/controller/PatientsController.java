package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Patients;
import com.example.demo.dto.common.ResultPaginationDTO;
import com.example.demo.dto.patients.CreatePatientDTO;
import com.example.demo.dto.patients.EditPatientDTO;
import com.example.demo.dto.patients.GetPatientsDTO;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.service.service_implementation.PatientServiceIml;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/public/patients")
public class PatientsController {
    private final PatientServiceIml patientServiceIml;

    public PatientsController(PatientServiceIml patientServiceIml) {
        this.patientServiceIml = patientServiceIml;
    }

    @GetMapping("/get-patients")
    public ResultPaginationDTO getPatients(@Valid @ModelAttribute GetPatientsDTO getPatientsDTO) {
        ResultPaginationDTO patients = this.patientServiceIml.getPatients(getPatientsDTO);
        return patients;
    }

    @GetMapping("/get-patient-by-id/{id}")
    public Patients getPatientById(@PathVariable("id") Long id) {
        Patients patient = this.patientServiceIml.getPatientByPatientId(id);
        return patient;
    }

    @PostMapping("/add-patient")
    public Patients addPatient(@Valid @ModelAttribute CreatePatientDTO patient) {
        // Check if patient already exists
        this.patientServiceIml
                .findByPhoneNumberOrResidentalIdentity(patient.getPhoneNumber(), patient.getResidentalIdentity());

        // Create new patient
        Patients newPatient = new Patients();
        newPatient.setFullName(patient.getFullName());
        newPatient.setGender(patient.isGender());
        newPatient.setAddress(patient.getAddress());
        newPatient.setYearOfBirth(patient.getYearOfBirth());
        newPatient.setPhoneNumber(patient.getPhoneNumber());
        newPatient.setResidentalIdentity(patient.getResidentalIdentity());

        return this.patientServiceIml.savePatient(newPatient);
    }

    @PatchMapping("/edit-patient/{id}")
    public Patients editPatient(@Valid @ModelAttribute EditPatientDTO editPatientDTO,
            @PathVariable("id") Long id) {
        Patients patient = this.getPatientById(id);

        // Update patient information
        if (editPatientDTO.getFullName() != null) {
            patient.setFullName(editPatientDTO.getFullName());
        }
        if (editPatientDTO.getGender() != null) {
            patient.setGender(editPatientDTO.getGender());
        }
        if (editPatientDTO.getAddress() != null) {
            patient.setAddress(editPatientDTO.getAddress());
        }
        if (editPatientDTO.getYearOfBirth() != null) {
            patient.setYearOfBirth(editPatientDTO.getYearOfBirth());
        }
        if (editPatientDTO.getPhoneNumber() != null) {
            patient.setPhoneNumber(editPatientDTO.getPhoneNumber());
        }
        if (editPatientDTO.getResidentalIdentity() != null) {
            patient.setResidentalIdentity(editPatientDTO.getResidentalIdentity());
        }
        return this.patientServiceIml.savePatient(patient);
    }

    @DeleteMapping("/delete-patient/{id}")
    public ResponseEntity<RestResponse<Object>> deletePatientById(@PathVariable("id") Long id) {
        // Delete patient by id
        this.patientServiceIml.deletePatientByPatientId(id);

        // Return response
        RestResponse<Object> response = new RestResponse<>();
        response.setMessage("Call API successfully!");
        response.setStatusCode(200);
        response.setData("Delete patient successfully!");
        return ResponseEntity.ok(response);
    }
}
