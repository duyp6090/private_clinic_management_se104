package com.example.demo.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.service.service_implementation.UserServiceImpl;


@Component
public class UserNameAndPasswordAuthentication implements AuthenticationProvider {

    private final UserServiceImpl userService;
    private final jwtUtils jwtUtils;

    // Constructor injection for UserService and JwtUtils
    public UserNameAndPasswordAuthentication(UserServiceImpl userService, jwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); 
        String password = (String) authentication.getCredentials(); 

        // Authenticate the user with UserService
        boolean isAuthenticated = userService.authenticate(username, password); 

        if (isAuthenticated) {
            // Retrieve user roles (authorities) from UserService or Database
            List<String> roles = userService.getUserRolesByUserName(username);
            // Generate the JWT token with roles included as claims
            String token = jwtUtils.generateAccessToken(username, roles);  // Updated method signature to include roles

            // Map roles to authorities for authentication context
            var authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(
                    new User(username, password, authorities), // Attach authorities here
                    token,
                    authorities // Use authorities here too
            );
        } else {
            throw new AuthenticationException("Invalid username or password") {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
