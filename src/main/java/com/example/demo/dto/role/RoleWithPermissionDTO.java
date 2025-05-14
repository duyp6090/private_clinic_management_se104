/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.role;

import java.util.List;

import com.example.demo.dto.response.ScreenPermission;

/**
 *
 * @author iset1enloc
 */
public class RoleWithPermissionDTO {
    

    private String role;
    private List<ScreenPermission> permissions;
    public RoleWithPermissionDTO(String role, List<ScreenPermission> permissions) {
        this.role = role;
        this.permissions = permissions;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public List<ScreenPermission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<ScreenPermission> permissions) {
        this.permissions = permissions;
    }
    
    
}
