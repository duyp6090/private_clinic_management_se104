package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get one user by id
    public User getUser(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Create a new user
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    // Update user by id
    public User updateUser(long id, User user) {
        User userCurrent = this.getUser(id);

        if (userCurrent != null) {
            userCurrent.setName(user.getName());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setPassword(user.getPassword());
            // Update
            return this.userRepository.save(userCurrent);
        }

        return userCurrent;

    }

    // Delete a user by id
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }
}
