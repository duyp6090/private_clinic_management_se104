/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Supporter;
import com.example.demo.domain.User;

/**
 *
 * @author iset1enloc
 */


public interface IUserService {
    
    User getUser(long id);
    
    List<User> getAllUsers();
    
    User createUser(User user);
    User save(User user);
    
    User updateUser(long id, User user);
    
    void deleteUser(long id);
    
    boolean authenticate(String name, String password);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    List<String> getUserRolesByUserName(String username);
    
    List<String> getRolesByUserId(Long userId);
    
    Optional<User> findByUsername(String username);

    List<String> findAllRolesByUserName(String username);

    List<String> findAllPermissionsByUserName(String username);
    
    List<String> findAllPermissionByUserNameAndUserRoleId(String username,int role_id);

    Boolean assignRoleToUser(String username,int roleId );
}
