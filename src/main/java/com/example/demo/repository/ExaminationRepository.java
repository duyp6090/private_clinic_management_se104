package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Examination;
import com.example.demo.dto.examination.requestExamination.GetPatientExamination;
import com.example.demo.dto.examination.requestExamination.GetWaitingExamination;
import com.example.demo.dto.examination.responseExamination.ResponsePatientExamination;
import com.example.demo.dto.examination.responseExamination.ResponseWaitingExamination;

public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    // Get person to patient
    @Query("""
                SELECT new com.example.demo.dto.examination.responseExamination.ResponseWaitingExamination(
                    e.examinationId,
                    e.patient.fullName,
                    e.patient.gender,
                    e.patient.yearOfBirth,
                    e.patient.address
                )
                FROM Examination e
                WHERE (:#{#filter.getExaminationDate()} IS NULL OR e.examinationDate = :#{#filter.getExaminationDate()})
                AND (:#{#filter.getGender()} IS NULL OR e.patient.gender = :#{#filter.getGender()})
            """)
    Page<ResponseWaitingExamination> findWaitingPatient(@Param("filter") GetWaitingExamination getExamination,
            Pageable pageable);

    // Get patient
    @Query("""
                SELECT new com.example.demo.dto.examination.responseExamination.ResponsePatientExamination(
                    e.examinationId,
                    e.patient.fullName,
                    e.examinationDate,
                    d.diseaseName,
                    e.symptoms
                )
                FROM Examination e
                LEFT JOIN e.disease d
                WHERE (:#{#filter.getExaminationDate()} IS NULL OR e.examinationDate = :#{#filter.getExaminationDate()})
                AND (:#{#filter.getFullName()} IS NULL OR e.patient.fullName LIKE %:#{#filter.getFullName()}%)
                AND (:#{#filter.getDrugId()} IS NULL OR d.diseaseId = :#{#filter.getDrugId()})
                AND (e.isExam = true)
            """)
    Page<ResponsePatientExamination> findPatientExamination(
            @Param("filter") GetPatientExamination getPatientExamination,
            Pageable pageable);

    // Get examination by id
    Optional<Examination> findByExaminationId(Long examinationId);

    // Get examinations by filter bill
    @Query("""
            SELECT e
            FROM Examination e
            WHERE (:startDate IS NULL OR e.examinationDate >= :startDate)
            AND (:endDate IS NULL OR e.examinationDate <= :endDate)
            AND (:fullName IS NULL OR LOWER(e.patient.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')))
            """)
    List<Examination> findAllBills(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("fullName") String fullName);

    // Get all examination by examinationDate
    List<Examination> findByExaminationDate(LocalDate examinationDate);

    // Get examination by isExam and examinationDate
    List<Examination> findByIsExamAndExaminationDate(boolean isExam, LocalDate examinationDate);

    // Count examination by examinationDate
    @Query("SELECT COUNT(e) FROM Examination e WHERE e.examinationDate = :examinationDate")
    int countByExaminationDate(@Param("examinationDate") LocalDate examinationDate);

    // Sum revenue by examinationDate
    @Query("SELECT SUM(e.examFee + e.drugsFee) FROM Examination e WHERE e.examinationDate = :examinationDate")
    Double sumRevenueByExaminationDate(@Param("examinationDate") LocalDate examinationDate);

}
