/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RestResponse;
import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.request.SignInRequest;
import com.example.demo.dto.request.SignOutRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.service.service_implementation.AuthServiceImpl;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthServiceImpl authService;

    public AuthenticationController(AuthServiceImpl authService) {
        System.out.println("Init Authentication");
        this.authService = authService;
    }

    // User Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<RestResponse<AuthResponse>> login(@RequestBody SignInRequest request) {
        RestResponse<AuthResponse> response = new RestResponse<>();
       
        try {
            response = authService.login(request.getUsername(), request.getPassword());
            System.out.println(response);
            System.out.println("CUUU TUI VOI");
            System.out.println(response);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
        
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setError("Invalid credentials");
            response.setMessage("Error: Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    // Refresh Token Endpoint
    @PostMapping("/newaccess")
    public ResponseEntity<RestResponse<AuthResponse>> regainAccessToken(@RequestBody RefreshTokenRequest request) {
        RestResponse<AuthResponse> response = new RestResponse<>();
        try {
            // AuthService handles refresh and returns a RestResponse
            response = authService.regainAccessToken(request.getRefreshToken());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setError("Invalid refresh token");
            response.setMessage("Error: Invalid or expired refresh token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // Refresh Token Endpoint
    @PostMapping("/refresh")
    public ResponseEntity<RestResponse<AuthResponse>> refreshToken(@RequestBody RefreshTokenRequest request) {
        RestResponse<AuthResponse> response = new RestResponse<>();
        try {
            // AuthService handles refresh and returns a RestResponse
            response = authService.getNewRefreshToken(request.getRefreshToken());
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setError("Invalid refresh token");
            response.setMessage("Error: Invalid or expired refresh token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // Logout Endpoint
    @PostMapping("/logout")
    public ResponseEntity<RestResponse<String>> logout(@RequestBody SignOutRequest request) {
        RestResponse<String> response = new RestResponse<>();
        try {
            authService.logout(request.getRefreshToken());
            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Logout successful");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError("Logout failed");
            response.setMessage("Error: An unexpected error occurred during logout");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // // User Registration Endpoint
    // @PostMapping("/signup")
    // public ResponseEntity<RestResponse<String>> registerUser(@RequestBody SignUpRequest user) {
    //     RestResponse<String> response = new RestResponse<>();
    //     try {
    //         if (userService.existsByUsername(user.getUsername())) {
    //             response.setStatusCode(HttpStatus.BAD_REQUEST.value());
    //             response.setError("Username already taken");
    //             response.setMessage("Error: Username is already taken!");
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    //         }

    //         if (userService.existsByEmail(user.getEmail())) {
    //             response.setStatusCode(HttpStatus.BAD_REQUEST.value());
    //             response.setError("Email already in use");
    //             response.setMessage("Error: Email is already in use!");
    //             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    //         }

    //         User usermodel= new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getFullName(), user.getAddress(), user.getPhone());

    //         // Save the user
    //         userService.createUser(usermodel);

    //         response.setStatusCode(HttpStatus.CREATED.value());
    //         response.setMessage("User registered successfully!");
    //         response.setData("Success");
    //         return ResponseEntity.status(HttpStatus.CREATED).body(response);
    //     } catch (Exception e) {
    //         response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    //         response.setError("Internal server error");
    //         response.setMessage("Error: An unexpected error occurred during user registration");
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    //     }
    // }


}
