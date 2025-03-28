package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    // DI: Dependency Injection
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    // Get one user by id
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        return this.userService.getUser(id);
    }

    // Get all users
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/user/create")
    // Get information from the request body
    public User createUser(@RequestBody User sendUser) {
        User newUser = this.userService.createUser(sendUser);
        return newUser;
    }

    // Update user by id
    @PutMapping("/user/update/{id}")
    public User updatUser(@PathVariable("id") long id, @RequestBody User sendUser) {
        User userCurrent = this.userService.getUser(id);
        return userCurrent;
    }

    // Delete user by id
    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return "User deleted successfully";
    }
}
