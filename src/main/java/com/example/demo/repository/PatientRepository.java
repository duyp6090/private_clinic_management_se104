/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Patients;

/**
 *
 * @author iset1enloc
 */
@Repository
public interface  PatientRepository extends JpaRepository<Patients, Long> {
    //do not need to re-implement built in method such as CRUD
    //write code on the extends method
}
