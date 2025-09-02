/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.User;
import com.example.demo.dto.user.UserInformationDTO;
import com.example.demo.dto.user.UserRoleDTO;

/**
 *
 * @author iset1enloc
 */


public interface IUserService {
    
    User getUser(long id);
    
    List<UserRoleDTO> getAllUsers();
    
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
    List<Object[]> findAllPermissionsByRoleId(Integer roleId);
    
    List<Object[]> findAllPermissionByUserNameAndUserRoleId(String username,int role_id);

    Boolean assignRoleToUser(String username, List<String>roleNameList);
    Boolean revokeRoleFromUser(String username,int roleId );
    int getRole_id(String rolename);

    Boolean updateUserInfo(int id,UserInformationDTO requestUserInfo);
    boolean existsByPhoneNumberOrUsernameOrEmail(String phoneNumber, String username, String email, Long id);
}
