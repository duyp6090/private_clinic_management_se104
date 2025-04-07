/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author iset1enloc
 */
public class assignPermission {
    @JsonProperty("permission_id")
    private int permissionId;

    // Getters and Setters
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }
}