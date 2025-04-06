package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.security.jwtUtils;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    // Dependency Injection (DI)
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final jwtUtils jwtUtils;

    public UserController(UserService userService, AuthenticationManager authenticationManager, jwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    // Get the home page message
    @GetMapping("/home")
    public String getHomePage() {
        return "Welcome to the server!";
    }

    // Get one user by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        return this.userService.getUser(id);
    }

    // Get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/create")
    public User createUser(@RequestBody User sendUser) {
        return this.userService.createUser(sendUser);
    }

    // Update user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") long id, @RequestBody User sendUser) {
        return this.userService.updateUser(id, sendUser);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return "User deleted successfully";
    }

    // Sign Up endpoint
    @PostMapping("/auth/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userService.existsByUsername(user.getName())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        // Save the user
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Sign In endpoint
    @PostMapping("/auth/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtUtils.generateToken(user.getName());
        return ResponseEntity.ok(jwt);
    }
}
