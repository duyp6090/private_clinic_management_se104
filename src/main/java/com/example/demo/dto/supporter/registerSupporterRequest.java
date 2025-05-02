/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.supporter;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *
 * @author iset1enloc
 */


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

    public String getStaffName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
