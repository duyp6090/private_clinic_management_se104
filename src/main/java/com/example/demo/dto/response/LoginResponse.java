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
    private String username;
    private String access_token;
    private List<String>available_roles;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAvailable_roles() {
        return available_roles;
    }

    public void setAvailable_roles(List<String> available_roles) {
        this.available_roles = available_roles;
    }

    // Default constructor
    public LoginResponse() {}

    // Constructor for initializing the fields
    public LoginResponse(String access_token,String username,List<String>available_roles) {
        this.access_token = access_token;
        this.available_roles = available_roles;
        this.username=username;
    }

    // Getters and setters
    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }
}
