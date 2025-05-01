/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author iset1enloc
 */
public class RefreshTokenRequest implements Serializable  {

    @NotBlank(message = "refreshtoken must be not null")
    @JsonProperty("refresh_token")
    private String refresh_token;
    @NotBlank(message = "access must be not null")
    @JsonProperty("access_token")
    private String access_token;

    public String getRefreshToken(){
        return refresh_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
