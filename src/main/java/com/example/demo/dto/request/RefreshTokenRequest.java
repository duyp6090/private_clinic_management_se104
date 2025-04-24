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

    public String getRefreshToken(){
        return refresh_token;
    }
}
