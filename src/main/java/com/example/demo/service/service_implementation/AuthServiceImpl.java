/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.service.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.LoginWithPermissionResponse;
import com.example.demo.dto.response.RestResponse;
import com.example.demo.security.jwtUtils;
import com.example.demo.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final jwtUtils jwtTokenProvider;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserServiceImpl userService,
            jwtUtils jwtTokenProvider,
            RefreshTokenServiceImpl refreshTokenService,
            AuthenticationManager authenticationManager,
            RoleServiceImpl roleService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
    }

    @Override
    public RestResponse<LoginResponse> login(String username, String password) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            List<String>available_roles = userService.findAllRolesByUserName(username);
            String access_token = jwtTokenProvider.generateTempToken(username, available_roles);

            LoginResponse authResponse = new LoginResponse(access_token,username,available_roles);
            return new RestResponse<>(HttpStatus.OK.value(), authResponse);

        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return new RestResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials", "Authentication failed");
        }
    }

    @Override
    public RestResponse<AuthResponse> regainAccessToken(String oldToken,List<String>roles,List<String>permissions) {
        try {
            Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(oldToken);
            System.out.println("Regain access token");
            if (optionalRefreshToken.isPresent()) {
                System.out.println("Regain access token 1");
                RefreshToken refreshToken = optionalRefreshToken.get();
                if (refreshTokenService.isValidToken(oldToken)) {
                    String newAccessToken = jwtTokenProvider.generateAccessToken(refreshToken.getUser().getName(),
                            roles,permissions);

                    AuthResponse authResponse = new AuthResponse(newAccessToken, refreshToken.getToken());
                    return new RestResponse<>(HttpStatus.OK.value(), authResponse);
                }
            }

            return new RestResponse<>(HttpStatus.FORBIDDEN.value(), "Invalid or expired refresh token", "Token error");
        } catch (Exception e) {
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred",
                    "Please try again later");
        }
    }

    @Override
    public RestResponse<AuthResponse> getNewRefreshToken(String oldToken,String accessToken) {
        try {
            System.out.println(oldToken);
            RefreshToken newRefreshToken = refreshTokenService.createRefreshTokenByExistingToken(oldToken);

            //get user permissions
            List<String> permissions = jwtTokenProvider.getPermissionsAuthoritiesFromToken(accessToken);

            List<String> roles = jwtTokenProvider.getRoleAuthoritiesFromToken(accessToken);

            String newAccessToken = jwtTokenProvider.generateAccessToken(newRefreshToken.getUser().getName(), roles,permissions);
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
            return new RestResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred",
                    "Please try again later");
        }
    }

    @Override
    public RestResponse<LoginWithPermissionResponse> loginWithPermission(String userName, String roleName) {
        int roleId = roleService.getRoleIDByRoleName(roleName);
        List<String>permissions = userService.findAllPermissionByUserNameAndUserRoleId(userName, roleId);
        ArrayList<String>roles = new ArrayList<>();
        roles.add(roleName);
        String accessToken = jwtTokenProvider.generateAccessToken(userName,roles,permissions);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userName);

        LoginWithPermissionResponse authResponse = new LoginWithPermissionResponse(accessToken, refreshToken.getToken(),roleName,permissions);
        return new RestResponse<>(HttpStatus.OK.value(), authResponse);
    }
}
