/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Doctor;
import com.example.demo.domain.Supporter;
import com.example.demo.domain.User;
import com.example.demo.dto.doctor.registerDoctorRequest;
import com.example.demo.dto.request.AssignRoleRequest;
import com.example.demo.dto.request.ChangePasswordRequest;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.dto.supporter.registerSupporterRequest;
import com.example.demo.service.IDoctorService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.ISupporterService;
import com.example.demo.service.IUserService;
import com.example.demo.service.service_implementation.UserServiceImpl;

/**
 *
 * @author iset1enloc
 */
@RestController
@RequestMapping("/api/admin")
// @PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {
    private final IUserService userService;
    private final IDoctorService doctorService;
    private final IRoleService roleService;
    private final ISupporterService supporterService;
    private final PasswordEncoder passwordEncoder;

    public AdminUserController(UserServiceImpl userService, IDoctorService doctorService,
            ISupporterService supporterService, IRoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.doctorService = doctorService;
        this.supporterService = supporterService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }
    @PreAuthorize("hasAuthority('PERMISSION_CREATE_USER')")
    @PostMapping("/register-doctor")
    public ResponseEntity<RestResponse<Object>> registerDoctor(@RequestBody registerDoctorRequest request) {
        if (userService.existsByUsername(request.userName)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    RestResponse.error(HttpStatus.BAD_REQUEST.value(), "Username already taken",
                            "Error: Username is already taken!"));
        }

        Doctor doctor = new Doctor();
        doctor.setName(request.userName);
        doctor.setFullName(request.fullName);
        doctor.setEmail(request.email);
        doctor.setPhone(request.phoneNumber);
        doctor.setPassword(passwordEncoder.encode(request.password));
        doctor.setSpecialization(request.specialization);
        doctor.setQualification(request.qualification);
        doctor.setYearsOfExperience(request.yearsOfExperience);

        var res = doctorService.save(doctor);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            RestResponse.success(HttpStatus.CREATED.value(), res)
        );
    }  

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-supporter")
    public ResponseEntity<RestResponse<Object>> registerSupporter(@RequestBody registerSupporterRequest request) {
        RestResponse<String> response = new RestResponse<>();
        Supporter supporter = new Supporter();
        if (userService.existsByUsername(request.userName)) {
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError("Username already taken");
            response.setMessage("Error: Username is already taken!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RestResponse.success(HttpStatus.CREATED.value(), response));
        }
        // Populate common user fields
        // populate fields inherited from User (or Staff, depending on your model)
        supporter.setName(request.userName);
        supporter.setFullName(request.fullName);
        supporter.setEmail(request.email);
        supporter.setPhone(request.phoneNumber);

        supporter.setPassword(passwordEncoder.encode(request.password));

        // populate Doctor-specific fields
        supporter.setTitle(request.staffTitle);

        Supporter res = supporterService.save(supporter);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RestResponse.success(HttpStatus.CREATED.value(), res));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/assign-role")
    public ResponseEntity<RestResponse<String>> assignRoleToUser(@RequestBody AssignRoleRequest request) {
        RestResponse<String> response = new RestResponse<>();

        // Fetch the role ID based on the role name
        int roleId = roleService.getRoleIDByRoleName(request.getRolename());

        if (roleId == -1) { // Assuming -1 means role not found
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setError("Role not found");
            response.setMessage("Error: Role does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Assign the role to the user
        try {
            userService.assignRoleToUser(request.getUsername(), roleId);
            response.setStatusCode(HttpStatus.CREATED.value());
            response.setMessage("Role assigned successfully");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(RestResponse.success(HttpStatus.CREATED.value(), "Role assigned successfully"));
        } catch (Exception e) {
            // Handle any unexpected errors
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError("Server error");
            response.setMessage("An error occurred while assigning the role.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/change-password")
    public ResponseEntity<RestResponse<Object>> changePassword(@RequestBody ChangePasswordRequest changePassword) {
        System.out.println("Enter change-password controller");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Authenticated user: " + username);

        Optional<User> optionalUser = userService.findByUsername(username);
        if (optionalUser.isEmpty()) {
            System.out.println("User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RestResponse.error(HttpStatus.NOT_FOUND.value(), "UserNotFound", "User not found."));
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(changePassword.getOldPasswordRequest(), user.getPassword())) {
            System.out.println("Old password is incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RestResponse.error(HttpStatus.BAD_REQUEST.value(), "InvalidPassword",
                            "Old password is incorrect."));
        }

        user.setPassword(passwordEncoder.encode(changePassword.getNewPasswordRequest()));
        userService.save(user);

        System.out.println("Password changed successfully");

        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.success(HttpStatus.OK.value(), "Password changed successfully"));
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
