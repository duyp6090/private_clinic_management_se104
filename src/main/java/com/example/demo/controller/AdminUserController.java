/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Supporter;
import com.example.demo.domain.User;
import com.example.demo.dto.doctor.registerDoctorRequest;
import com.example.demo.dto.supporter.registerSupporterRequest;
import com.example.demo.service.IDoctorService;
import com.example.demo.service.ISupporterService;
import com.example.demo.service.IUserService;
import com.example.demo.service.service_implementation.UserServiceImpl;

/**
 *
 * @author iset1enloc
 */
@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    private final IUserService userService;
    private final IDoctorService doctorService;
    private final ISupporterService supporterService;

    public AdminUserController(UserServiceImpl userService, IDoctorService doctorService, ISupporterService supporterService) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.supporterService = supporterService;
    }

    @PostMapping("/register-doctor")
    public ResponseEntity<Doctor> registerDoctor(@RequestBody registerDoctorRequest request) {
        Doctor doctor = new Doctor();
        System.out.println("ENTER LINE 52");
        System.out.println(request.qualification);
        doctor.setName(request.staffName);
        doctor.setFullName(request.fullName);
        doctor.setEmail(request.email);
        doctor.setPhone(request.phoneNumber);
        doctor.setPassword(request.password); // consider encoding before saving!

        doctor.setSpecialization(request.specialization);
        doctor.setQualification(request.qualification);
        doctor.setYearsOfExperience(request.yearsOfExperience);
        Doctor res = doctorService.save(doctor);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }   

    @PostMapping("/register-supporter")
    public ResponseEntity<Supporter> registerSupporter(@RequestBody registerSupporterRequest request) {

        Supporter supporter = new Supporter();

        // populate fields inherited from User (or Staff, depending on your model)
        supporter.setName(request.staffName);
        supporter.setFullName(request.fullName);
        supporter.setEmail(request.email);
        supporter.setPhone(request.phoneNumber);
        supporter.setPassword(request.password); // consider encoding before saving!

        // populate Doctor-specific fields
        supporter.setTitle(request.staffTitle);

        Supporter res = supporterService.save(supporter);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }
    // Create a new user
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('CREATE_USER')")
    @PostMapping("/create")
    // Get information from the request body
    public ResponseEntity<User> createUser(@RequestBody User sendUser) {
        User newUser = this.userService.createUser(sendUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }
}
