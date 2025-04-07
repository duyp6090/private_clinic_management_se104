package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for userRepository and passwordEncoder
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get one user by id
    @Override
    public User getUser(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElse(null);
    }

    // Get all users
    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Create a new user with encoded password
    @Override
    public User createUser(User user) {
        // Encode the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    // Update user by id
    @Override
    public User updateUser(long id, User user) {
        User userCurrent = this.getUser(id);

        if (userCurrent != null) {
            userCurrent.setName(user.getName());
            userCurrent.setEmail(user.getEmail());
            userCurrent.setPassword(passwordEncoder.encode(user.getPassword())); // Update password with encoding
            return this.userRepository.save(userCurrent);
        }

        return null;
    }

    // Authenticate user by username and password
    @Override
    public boolean authenticate(String name, String password) {
        Optional<User> userOptional = userRepository.findByUsername(name);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.print(user.getPassword());
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
            
            return isPasswordMatch;
        } else {
            System.out.println("HUHUH");
        }
        
        return true;
    }
    // Delete a user by id
    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    // Check if a username already exists in the database
    @Override
    public boolean existsByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }

    // Check if an email already exists in the database
    @Override
    public boolean existsByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
    @Override
    public List<String>getUserRolesByUserName(String username){
        var user_roles = userRepository.findAllRolesByUsername(username);
        return user_roles;
    }
    @Override
    public List<String>getRolesByUserId(Long user_id){
        var user_roles = userRepository.findAllRolesByUserId(user_id);
        return user_roles;
    }
    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
