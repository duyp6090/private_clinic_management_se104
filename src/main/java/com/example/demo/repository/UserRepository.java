package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Find user by id
    Optional<User> findById(long id);

    // // Find all users
    // List<User> findAll();

    // // Create a new user
    // User save(User user);

    // Delete user by id
    void deleteById(long id);
}
