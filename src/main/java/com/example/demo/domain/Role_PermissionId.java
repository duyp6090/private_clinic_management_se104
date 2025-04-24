/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
/**
 *
 * @author iset1enloc
 */
@Embeddable
public class Role_PermissionId implements Serializable {

    private Integer role_id;         // Role ID
    private Integer permission_id;   // Permission ID

    // Default constructor
    public Role_PermissionId() {}

    // Parameterized constructor
    public Role_PermissionId(Integer role_id, Integer permission_id) {
        this.role_id = role_id;
        this.permission_id = permission_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role_PermissionId that = (Role_PermissionId) o;
        return Objects.equals(role_id, that.role_id) && Objects.equals(permission_id, that.permission_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, permission_id);
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role) {
        this.role_id = role;
    }

    public Integer getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(Integer permission) {
        this.permission_id = permission;
    }
}
