package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.example.demo.exception.IdInvalidException;
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
    // Get one user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) throws IdInvalidException {
        if (id <= 0) {
            throw new IdInvalidException("Id must be greater than 0");
        }
        User findUser = this.userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(findUser);
    }

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listUsers = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(listUsers);
    }

    // Create a new user
    @PostMapping("/create")
    // Get information from the request body
    public ResponseEntity<User> createUser(@RequestBody User sendUser) {
        User newUser = this.userService.createUser(sendUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatUser(@PathVariable("id") long id, @RequestBody User sendUser) {
        User userCurrent = this.userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCurrent);
    }

    // Delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User deleted successfully");
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

