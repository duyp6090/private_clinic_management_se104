/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
/**
 *
 * @author iset1enloc
 */

@Entity
@Table(name = "tbl_role_permission")
public class Role_Permission {

    @EmbeddedId
    private Role_PermissionId id;  // Composite Key

    @ManyToOne
    @MapsId("role_id")   // Maps the "role" part of the embedded ID to the Role entity
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @MapsId("permission_id")  // Maps the "permission" part of the embedded ID to the Permission entity
    @JoinColumn(name = "permission_id")
    private Permission permission;

    // Default constructor
    public Role_Permission() {}

    // Constructor that sets the ID and entities
    public Role_Permission(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
        this.id = new Role_PermissionId(role.getRole_id(), permission.getPermission_id());
    }

    // Getters and Setters
    public Role_PermissionId getId() {
        return id;
    }

    public void setId(Role_PermissionId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

}
