/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Doctor;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.IDoctorService;

/**
 *
 * @author iset1enloc
 */
@Service
public class DoctorServiceImpl implements IDoctorService{
    private final DoctorRepository doctorRepository;
    

    // Constructor injection for userRepository and passwordEncoder
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);
        
    }

    // @Override
    // public Doctor update(Long staffId, Doctor doctor) {
    //     return this.doctorRepository.update(staffId, doctor);
    // }

}
