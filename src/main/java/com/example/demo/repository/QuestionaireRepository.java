package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Questionaire;

@Repository
public interface QuestionaireRepository extends JpaRepository<Questionaire, Integer>{
    List<Questionaire> findByPatient_PatientId(Long patientId);
}
