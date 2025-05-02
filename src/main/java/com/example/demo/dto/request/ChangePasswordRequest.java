/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author iset1enloc
 */
public class ChangePasswordRequest {
    @JsonProperty("oldPasswordRequest")
    public String oldPasswordRequest;
    @JsonProperty("newPasswordRequest")
    public String newPasswordRequest;

    public String getNewPasswordRequest() {
        return newPasswordRequest;
    }

    public String getOldPasswordRequest() {
        return oldPasswordRequest;
    }

    public void setOldPasswordRequest(String oldPasswordRequest) {
        this.oldPasswordRequest = oldPasswordRequest;
    }

    public void setNewPasswordRequest(String newPasswordRequest) {
        this.newPasswordRequest = newPasswordRequest;
    }

    public ChangePasswordRequest(String newPasswordRequest) {
        this.newPasswordRequest = newPasswordRequest;
    }
    
}
