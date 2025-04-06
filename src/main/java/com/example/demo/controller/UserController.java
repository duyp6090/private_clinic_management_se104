package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    // DI: Dependency Injection
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get one user by id
    @GetMapping("/home")
    public String getHomePage() {
        System.out.print("hiihi");
        return "Welcome to server";
    }
    // Get one user by id
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") long id) {
        return this.userService.getUser(id);
    }

    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    // Create a new user
    @PostMapping("/create")
    // Get information from the request body
    public User createUser(@RequestBody User sendUser) {
        System.out.print("hihi");
        User newUser = this.userService.createUser(sendUser);
        return newUser;
    }

    // Update user by id
    @PutMapping("/{id}")
    public User updatUser(@PathVariable("id") long id, @RequestBody User sendUser) {
        User userCurrent = this.userService.getUser(id);
        return userCurrent;
    }

    // Delete user by id
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        this.userService.deleteUser(id);
        return "User deleted successfully";
    }
}
