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
import com.example.demo.dto.RestResponse;
import com.example.demo.dto.doctor.registerDoctorRequest;
import com.example.demo.dto.response.AuthResponse;
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
@PreAuthorize("hasRole('ADMIN')")
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
    public ResponseEntity<RestResponse<Doctor>> registerDoctor(@RequestBody registerDoctorRequest request) {
        return null;
    }   

    @PostMapping("/register-supporter")
    public ResponseEntity<RestResponse<Supporter>> registerSupporter(@RequestBody registerSupporterRequest request) {
        return null;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
    }
    // Create a new user
    @PreAuthorize("hasRole('ADMIN')")
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
