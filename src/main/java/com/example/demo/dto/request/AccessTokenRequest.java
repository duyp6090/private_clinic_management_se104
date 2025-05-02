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
public class AccessTokenRequest {
    @NotBlank(message = "refreshtoken must be not null")
    @JsonProperty("accessToken")
    private String accessToken;

    public String getAccessToken(){
        return accessToken;
    }
}
