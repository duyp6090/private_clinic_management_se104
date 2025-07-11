package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dto.request.RefreshTokenRequest;
import com.example.demo.dto.request.SignInRequest;
import com.example.demo.dto.request.SignInRequestPermission;
import com.example.demo.dto.request.SignOutRequest;
import com.example.demo.dto.request.SignUpRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.LoginWithPermissionResponse;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.security.jwtUtils;
import com.example.demo.service.IAuthService;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final IAuthService authService;
    private final IUserService userService;
    private final jwtUtils jwtTokenProvider;
    public AuthenticationController(IAuthService authService,IUserService userservice,jwtUtils jwtProvider) {
        System.out.println("Init Authentication");
        this.authService = authService;
        this.userService=userservice;
        this.jwtTokenProvider = jwtProvider;
    }

    // User Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<RestResponse<LoginResponse>> login(@RequestBody SignInRequest request) {
        RestResponse<LoginResponse> response = new RestResponse<>();

        try {
             response = authService.login(request.getUsername(), request.getPassword());
             return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setError("Invalid credentials");
            response.setMessage("Error: Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
    // User Login Endpoint
    @PostMapping("/login-with-permission")
    public ResponseEntity<RestResponse<LoginWithPermissionResponse>> loginWithPermission(@RequestBody SignInRequestPermission request) {
        RestResponse<LoginWithPermissionResponse> response = new RestResponse<>();

        try {
             response = authService.loginWithPermission(request.getUsername(), request.getRoleName());
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
            List<String> roles = jwtTokenProvider.getRoleAuthoritiesFromToken(request.getAccess_token());
            System.out.print(roles);
            response = authService.regainAccessToken(request.getRefreshToken(),roles);
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
            System.out.println(request.getRefresh_token());
            // AuthService handles refresh and returns a RestResponse
            response = authService.getNewRefreshToken(request.getRefreshToken(),request.getAccess_token());
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

    // User Registration Endpoint
    @PostMapping("/signup")
    public ResponseEntity<RestResponse<String>> registerUser(@RequestBody SignUpRequest user) {
        RestResponse<String> response = new RestResponse<>();
        try {
            if (userService.existsByUsername(user.getUsername())) {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setError("Username already taken");
                response.setMessage("Error: Username is already taken!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            if (userService.existsByEmail(user.getEmail())) {
                response.setStatusCode(HttpStatus.BAD_REQUEST.value());
                response.setError("Email already in use");
                response.setMessage("Error: Email is already in use!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            User usermodel= new User(user.getUsername(), user.getEmail(), user.getPassword(), user.getFullName(), user.getAddress(), user.getPhone());

            // Save the user
            userService.createUser(usermodel);

            response.setStatusCode(HttpStatus.CREATED.value());
            response.setMessage("User registered successfully!");
            response.setData("Success");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setError("Internal server error");
            response.setMessage("Error: An unexpected error occurred during user registration");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // if (userService.existsByEmail(user.getEmail())) {
    // response.setStatusCode(HttpStatus.BAD_REQUEST.value());
    // response.setError("Email already in use");
    // response.setMessage("Error: Email is already in use!");
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    // }

    // User usermodel= new User(user.getUsername(), user.getEmail(),
    // user.getPassword(), user.getFullName(), user.getAddress(), user.getPhone());

    // // Save the user
    // userService.createUser(usermodel);

    // response.setStatusCode(HttpStatus.CREATED.value());
    // response.setMessage("User registered successfully!");
    // response.setData("Success");
    // return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // } catch (Exception e) {
    // response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    // response.setError("Internal server error");
    // response.setMessage("Error: An unexpected error occurred during user
    // registration");
    // return
    // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    // }
    // }

}
