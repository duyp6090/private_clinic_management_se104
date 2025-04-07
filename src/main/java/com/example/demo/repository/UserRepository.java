package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.User;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    // Find user by username
    Optional<User> findByUsername(String username);
    
    // Find user by id
    Optional<User> findById(long id);

    // // Find all users
    List<User> findAll();

    // // Create a new user
    User save(User user);

    // Delete user by id
    void deleteById(long id);

    // Correct query method for fetching roles by userId
    @Query("SELECT r.role_name FROM Role r JOIN User_Role ur ON r.id = ur.role.id WHERE ur.user.id = :userId")
    List<String> findAllRolesByUserId(@Param("userId") long userId);

    
    @Query("SELECT r.role_name " +
    "FROM Role r " +
    "JOIN User_Role ur ON ur.role.role_id = r.role_id " +
    "JOIN User u ON ur.user.id = u.id " +
    "WHERE u.username = :username")
    List<String> findAllRolesByUsername(@Param("username") String username);

    
}
