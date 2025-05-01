/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.response;

import java.util.List;

/**
 *
 * @author iset1enloc
 */
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private List<String>available_roles;

    public List<String> getAvailable_roles() {
        return available_roles;
    }

    public void setAvailable_roles(List<String> available_roles) {
        this.available_roles = available_roles;
    }

    // Default constructor
    public LoginResponse() {}

    // Constructor for initializing the fields
    public LoginResponse(String accessToken, String refreshToken,List<String>available_roles) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.available_roles = available_roles;
    }

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
