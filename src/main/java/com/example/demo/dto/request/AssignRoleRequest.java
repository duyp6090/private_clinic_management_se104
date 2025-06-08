/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author iset1enloc
 */
public class AssignRoleRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("roleNameList")
    private List<String> roleNameList;
    
    public AssignRoleRequest(String username, List<String> roleNameList) {
        this.username = username;
        this.roleNameList=roleNameList;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoleNameList() {
        return roleNameList;
    }
    public void setRoleNameList(List<String> roleNameList) {
        this.roleNameList = roleNameList;
    }
   
}
