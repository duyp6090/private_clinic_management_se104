/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.user;

import java.util.List;

/**
 *
 * @author iset1enloc
 */
public class UserRoleDTO {
    private Long user_id;
    private String username;
    private List<String> role;
    
    public UserRoleDTO(Long user_id, String username, List<String> role) {
        this.user_id = user_id;
        this.username = username;
        this.role = role;
    }
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<String> getRole() {
        return role;
    }
    public void setRole(List<String> role) {
        this.role = role;
    }
    
}
