/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.LoginWithPermissionResponse;
import com.example.demo.dto.response.RestResponse;

/**
 *
 * @author iset1enloc
 */

public interface IAuthService {

    RestResponse<LoginResponse> login(String username, String password);
    RestResponse<LoginWithPermissionResponse>loginWithPermission(String userName,String roleName);

    RestResponse<AuthResponse> regainAccessToken(String oldToken,List<String> roles);

    RestResponse<AuthResponse> getNewRefreshToken(String oldToken,String accessToken);

    RestResponse<Void> logout(String refreshToken);
}
