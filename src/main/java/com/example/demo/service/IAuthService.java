/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.RestResponse;

/**
 *
 * @author iset1enloc
 */

public interface IAuthService {

    RestResponse<AuthResponse> login(String username, String password);

    RestResponse<AuthResponse> regainAccessToken(String oldToken);

    RestResponse<AuthResponse> getNewRefreshToken(String oldToken);

    RestResponse<Void> logout(String refreshToken);
}
