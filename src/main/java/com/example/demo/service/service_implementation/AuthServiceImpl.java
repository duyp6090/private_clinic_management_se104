/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.dto.RestResponse;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.security.jwtUtils;
import com.example.demo.service.IAuthService;


@Service
public class AuthServiceImpl implements  IAuthService{

    private final UserServiceImpl userService;
    private final jwtUtils jwtTokenProvider;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final AuthenticationManager authenticationManager;
    
    public AuthServiceImpl(
            UserServiceImpl userService,
            jwtUtils jwtTokenProvider,
            RefreshTokenServiceImpl refreshTokenService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public RestResponse<AuthResponse> login(String username, String password) {
        try {

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            List<String> roles = userService.getUserRolesByUserName(username);
            //List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            String accessToken = jwtTokenProvider.generateAccessToken(username, roles);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);
            
            AuthResponse authResponse = new AuthResponse(accessToken, refreshToken.getToken());
            return new RestResponse<>(HttpStatus.OK.value(), authResponse);
    
        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return new RestResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials", "Authentication failed");
        }
    }
    
    // private User authenticateUser(String username, String password) {
    //     Optional<User> userOpt = userService.findByUsername(username);

    //     if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
    //         throw new IllegalArgumentException("Invalid username or password");
    //     }
    //     return userOpt.get();
    // }
    @Override
    public RestResponse<AuthResponse> regainAccessToken(String oldToken) {
        try {
            Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(oldToken);

            if (optionalRefreshToken.isPresent()) {
                RefreshToken refreshToken = optionalRefreshToken.get();
                List<String> roles = userService.getUserRolesByUserName(refreshToken.getUser().getUsername());
                if (refreshTokenService.isValidToken(oldToken)) {
                    String newAccessToken = jwtTokenProvider.generateAccessToken(refreshToken.getUser().getName(),roles);

                    AuthResponse authResponse = new AuthResponse(newAccessToken, refreshToken.getToken());
                    return new RestResponse<>(HttpStatus.OK.value(), authResponse);
                }
            }

            return new RestResponse<>(HttpStatus.FORBIDDEN.value(), "Invalid or expired refresh token", "Token error");
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", "Please try again later");
        }
    }
    @Override
    public RestResponse<AuthResponse> getNewRefreshToken(String oldToken) {
        try {
            RefreshToken newRefreshToken = refreshTokenService.createRefreshTokenByExistingToken(oldToken);
            List<String> roles = userService.getUserRolesByUserName(newRefreshToken.getUser().getUsername());
            String newAccessToken = jwtTokenProvider.generateAccessToken(newRefreshToken.getUser().getName(),roles);

            AuthResponse authResponse = new AuthResponse(newAccessToken, newRefreshToken.getToken());
            return new RestResponse<>(HttpStatus.OK.value(), authResponse);
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.FORBIDDEN.value(), "Invalid or expired refresh token", "Token error");
        }
    }
    @Override
    public RestResponse<Void> logout(String refreshToken) {
        try {
            refreshTokenService.revokeToken(refreshToken);
            return new RestResponse<>(HttpStatus.OK.value(), null);
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", "Please try again later");
        }
    }
}
