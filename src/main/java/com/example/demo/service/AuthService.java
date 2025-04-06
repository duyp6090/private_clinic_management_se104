/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service;

/**
 *
 * @author iset1enloc
 */
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.domain.User;
import com.example.demo.dto.RestResponse;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwtUtils;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtils jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            jwtUtils jwtTokenProvider,
            RefreshTokenService refreshTokenService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }


    public RestResponse<AuthResponse> login(String username, String password) {
        try {
            User user = authenticateUser(username, password);

            String accessToken = jwtTokenProvider.generateAccessToken(user.getName());
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);

            AuthResponse authResponse = new AuthResponse(accessToken, refreshToken.getToken());
            return new RestResponse<>(HttpStatus.OK.value(), authResponse);
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials", "Authentication failed");
        }
    }

    private User authenticateUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return userOpt.get();
    }

    public RestResponse<AuthResponse> regainAccessToken(String oldToken) {
        try {
            Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(oldToken);

            if (optionalRefreshToken.isPresent()) {
                RefreshToken refreshToken = optionalRefreshToken.get();

                if (refreshTokenService.isValidToken(oldToken)) {
                    String newAccessToken = jwtTokenProvider.generateAccessToken(refreshToken.getUser().getName());

                    AuthResponse authResponse = new AuthResponse(newAccessToken, refreshToken.getToken());
                    return new RestResponse<>(HttpStatus.OK.value(), authResponse);
                }
            }

            return new RestResponse<>(HttpStatus.FORBIDDEN.value(), "Invalid or expired refresh token", "Token error");
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", "Please try again later");
        }
    }

    public RestResponse<AuthResponse> getNewRefreshToken(String oldToken) {
        try {
            RefreshToken newRefreshToken = refreshTokenService.createRefreshTokenByExistingToken(oldToken);

            String newAccessToken = jwtTokenProvider.generateAccessToken(newRefreshToken.getUser().getName());

            AuthResponse authResponse = new AuthResponse(newAccessToken, newRefreshToken.getToken());
            return new RestResponse<>(HttpStatus.OK.value(), authResponse);
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.FORBIDDEN.value(), "Invalid or expired refresh token", "Token error");
        }
    }

    public RestResponse<Void> logout(String refreshToken) {
        try {
            refreshTokenService.revokeToken(refreshToken);
            return new RestResponse<>(HttpStatus.OK.value(), null);
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", "Please try again later");
        }
    }
}
