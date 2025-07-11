/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Doctor;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Doctor save(Doctor doctor);
    // Doctor update(Long staffId, Doctor doctor);
}