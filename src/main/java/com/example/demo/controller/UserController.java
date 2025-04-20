package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.dto.user.UserDTO;
import com.example.demo.service.IUserService;

@RestController
public class UserController {

    // Dependency Injection (DI)
    private final IUserService userService;

    public UserController(IUserService userService) {
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
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
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

    @GetMapping("/api/current-user")
    public ResponseEntity<UserDTO> getCurrentUser() {
        System.out.println("ENTER USER DTO");
        // Get the current authenticated user from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var username = authentication.getName();

        var optionalUser = userService.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        List<String> user_roles = userService.findAllRolesByUserName(username);
        List<String> user_permision = userService.findAllPermissionsByUserName(username);

        System.out.println(user_roles);
        System.out.println(user_permision);
        System.out.println(username);

        // For demonstration, assume email is the same as username
        final UserDTO userDTO = new UserDTO(user.getId(), username, user_roles, user_permision);

        return ResponseEntity.ok(userDTO);
    }
}
