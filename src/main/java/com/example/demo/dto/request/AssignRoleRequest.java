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

    @JsonProperty("roleIdList")
    private List<Integer> roleIdList;
    
    public AssignRoleRequest(String username, List<Integer> roleIdList) {
        this.username = username;
        this.roleIdList=roleIdList;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }
    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }
   
}
