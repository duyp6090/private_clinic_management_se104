/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.dto.response;

import java.util.List;

/**
 *
 * @author iset1enloc
 */
public class LoginWithPermissionResponse {
    private String accessToken;
    private String refreshToken;
    private String selected_role;
    private List<ScreenPermission>permissionList;



    public List<ScreenPermission> getPermissionList() {
        return permissionList;
    }


    public void setPermissionList(List<ScreenPermission> permissionList) {
        this.permissionList = permissionList;
    }


    // Default constructor
    public LoginWithPermissionResponse() {}

    // Constructor for initializing the fields
    public LoginWithPermissionResponse(String accessToken, String refreshToken,String selected_role,List<ScreenPermission>permissionList) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.selected_role=selected_role;
        this.permissionList = permissionList;
    }

    // Getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public String getSelected_role() {
        return selected_role;
    }


    public void setSelected_role(String selected_role) {
        this.selected_role = selected_role;
    }
}
