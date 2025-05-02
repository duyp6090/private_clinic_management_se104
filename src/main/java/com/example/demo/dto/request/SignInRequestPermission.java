/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author iset1enloc
 */
public class SignInRequestPermission {
    @NotBlank(message = "username must be not null")
    @JsonProperty("user_name")
    private String username;

    @NotBlank(message = "username must be not blank")
    @JsonProperty("role_name")
    private String roleName;

    public String getUsername() {
        return username;
    }


    public String getRoleName() {
        return roleName;
    }
}
