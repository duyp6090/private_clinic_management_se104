/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author iset1enloc
 */
public enum  UserType {
    @JsonProperty("admin")
    ADMIN,
    @JsonProperty("doctor")
    DOCTOR,
    @JsonProperty("supportor")
    SUPPORTER
}
