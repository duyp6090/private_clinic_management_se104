/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.supporter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author iset1enloc
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class registerSupporterRequest {
    @JsonProperty("username")
    public String userName;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

    @JsonProperty("password")
    public String password;

    @JsonProperty("staffTitle")
    public String staffTitle;

    @JsonIgnore
    public String getStaffName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private List<String> roles;

    
}
