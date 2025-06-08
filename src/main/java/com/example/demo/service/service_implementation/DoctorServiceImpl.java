/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Doctor;
import com.example.demo.dto.doctor.registerDoctorRequest;
import com.example.demo.dto.user.UserRoleDTO;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.service.IDoctorService;
import com.example.demo.service.IUserService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author iset1enloc
 */
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements IDoctorService {
    private final DoctorRepository doctorRepository;
    private final IUserService iUserService;

    // Constructor injection for userRepository and passwordEncoder
    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepository.save(doctor);

    }

    @Override
    public List<registerDoctorRequest> getAllDoctors() {
        List<Doctor> dr = doctorRepository.findAll();
        List<registerDoctorRequest> regis = new ArrayList<>();
        List<UserRoleDTO> userRoles = iUserService.getAllUsers();

        for (Doctor dt : dr) {

            List<String> roles = userRoles.stream()
                    .filter(r -> r.getUser_id() == dt.getId())
                    .flatMap(r -> r.getRole().stream())
                    .collect(Collectors.toList());

            registerDoctorRequest regisDr = new registerDoctorRequest(
                    dt.getUsername(),
                    dt.getFullName(),
                    dt.getEmail(),
                    dt.getPhone(),
                    dt.getPassword(),
                    dt.getSpecialization(),
                    dt.getQualification(),
                    dt.getYearsOfExperience(),
                    roles);

            regis.add(regisDr);
        }

        return regis;
    }

    // @Override
    // public Doctor update(Long staffId, Doctor doctor) {
    // return this.doctorRepository.update(staffId, doctor);
    // }

}