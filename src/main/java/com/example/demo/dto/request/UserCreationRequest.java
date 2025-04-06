package com.example.demo.dto.request;

import jakarta.validation.constraints.Size;

public class UserCreationRequest {
    private String name;
    private String email;

    @Size(min = 8, message = "PassWord_Invalid")
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
