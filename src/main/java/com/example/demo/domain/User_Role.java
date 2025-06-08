/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.domain;

import java.util.Objects;

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
@Table(name = "tbl_user_role")
public class User_Role {
    @EmbeddedId
    private User_Role_Id id;
    
    @ManyToOne
    @MapsId("user_id") 
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("role_id")  
    @JoinColumn(name = "role_id")

    private Role role;   

    // Default constructor
    public User_Role() {}

    public User_Role_Id getId() {
        return id;
    }

    public void setId(User_Role_Id id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Constructor that sets the ID and entities
    public User_Role(User user, Role role) {
        this.role = role;
        this.user = user;
        this.id = new User_Role_Id(user.getId(), role.getRole_id());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Role that = (User_Role) o;
        return Objects.equals(user.getId(), that.user.getId()) &&
               Objects.equals(role.getRole_id(), that.role.getRole_id());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), role.getRole_id());
    }
    
}
