package com.example.demo.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dto.RestResponse;
import com.example.demo.exception.IdInvalidException;
import com.example.demo.service.service_implementation.UserServiceImpl;

@RestController
@RequestMapping("/admin")
public class UserController {

    // Dependency Injection (DI)
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/home")
    public ResponseEntity<RestResponse<String>> getHomePage() {
        RestResponse<String> response = new RestResponse<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("Welcome to the home page!");
        response.setData("home");
    
        return ResponseEntity.ok(response);
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

    // Update user by id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updatUser(@PathVariable("id") long id, @RequestBody User sendUser) {
        User userCurrent = this.userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCurrent);
    }
}

