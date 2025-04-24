/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author iset1enloc
 */

public class SignInRequest implements Serializable {

    @NotBlank(message = "username must be not null")
    private String username;

    @NotBlank(message = "username must be not blank")
    private String password;

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

}