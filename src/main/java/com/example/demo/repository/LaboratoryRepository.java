package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Laboratory;
import com.example.demo.domain.Patients;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {
    List<Laboratory> findByPatient(Patients patient);

    List<Laboratory> findByPatient_PatientId(Long patientId);

    @Query("SELECT l FROM Laboratory l WHERE l.patient.patientId = :patientId")
    List<Laboratory> findByPatientId(@Param("patientId") Long patientId);

}
