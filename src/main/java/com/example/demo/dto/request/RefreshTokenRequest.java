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
    @JsonProperty("refreshToken")
    private String refreshToken;
    @NotBlank(message = "access must be not null")
    @JsonProperty("accessToken")
    private String accessToken;

    public String getRefreshToken(){
        return refreshToken;
    }

    public String getRefresh_token() {
        return refreshToken;
    }

    public void setRefresh_token(String refresh_token) {
        this.refreshToken = refresh_token;
    }

    public String getAccess_token() {
        return accessToken;
    }

    public void setAccess_token(String access_token) {
        this.accessToken = access_token;
    }
}
