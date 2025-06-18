package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Patients;
import com.example.demo.dto.patients.GetPatientsDTO;

public interface PatientsRepository extends JpaRepository<Patients, Long> {
        // Get all patients by full_name, gender, year_of_birth
        @Query("""
                            SELECT p FROM Patients p
                            WHERE (:#{#filter.getFullName()} IS NULL OR LOWER(p.fullName) LIKE LOWER(CONCAT('%', :#{#filter.getFullName()}, '%')))
                                AND (:#{#filter.getGender()} IS NULL OR p.gender = :#{#filter.getGender()})
                                AND (:#{#filter.getYearOfBirth()} IS NULL OR p.yearOfBirth = :#{#filter.getYearOfBirth()})
                        """)
        Page<Patients> findPatients(@Param("filter") GetPatientsDTO getPatientsDTO, Pageable pageable);

        // Get patient by id
        public Optional<Patients> findByPatientId(Long id);

        // Find patient by phone_number or residental_identity
        @Query("""
                            SELECT p FROM Patients p
                            WHERE p.phoneNumber = :phoneNumber OR p.residentalIdentity = :residentalIdentity
                        """)
        public List<Patients> findByPhoneNumberOrResidentalIdentity(@Param("phoneNumber") String phoneNumber,
                        @Param("residentalIdentity") String residentalIdentity);

        // Check exist patient by id
        public boolean existsByPatientId(Long id);

        // Check exist patient by phone_number or residental_identity
        public Optional<Patients> findByPhoneNumber(String phoneNumber);

        public Optional<Patients> findByResidentalIdentity(String residentalIdentity);

        // Delete Patient
        public void deleteByPatientId(Long id);

}
