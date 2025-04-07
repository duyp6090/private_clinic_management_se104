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
public class User_Role_Id implements Serializable {

    private Long user_id;         // Role ID
    private int role_id;   // Permission ID

    // Default constructor
    public User_Role_Id() {}

    // Parameterized constructor
    public User_Role_Id(Long user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Role_Id that = (User_Role_Id) o;
        return Objects.equals(user_id, that.user_id) && Objects.equals(role_id, that.role_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role_id, role_id);
    }

}

