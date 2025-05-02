/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.dto.user;

import java.util.List;

/**
 *
 * @author iset1enloc
 */
public class UserDTO {
    private Long user_id;
    private String username;
    private String role;
    private List<String>permissions;
    public String getRoles() {
        return role;
    }

    public void setRoles(String roles) {
        this.role = role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    // Constructors
    public UserDTO(Long user_id, String username, String role,List<String>permissions) {
        this.username = username;
        this.role = role;
        this.permissions = permissions;
        this.user_id = user_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}