package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.example.demo.service.UserService;

@Component
public class UserNameAndPasswordAuthentication implements AuthenticationProvider {

    private final UserService userService;
    private final jwtUtils jwtUtils;

    // Constructor injection for UserService and jwtUtils
    public UserNameAndPasswordAuthentication(UserService userService,jwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // Authenticate the user using the UserService
        boolean isAuthenticated = userService.authenticate(username, password); // Assuming this method exists

        if (isAuthenticated) {
            // Generate the JWT token if authentication is successful
            String token = jwtUtils.generateToken(username);

            // Create an authentication token with user details and token
            return new UsernamePasswordAuthenticationToken(
                    new User(username, password, java.util.Collections.emptyList()), // User object can be enhanced for roles/authorities
                    token, 
                    java.util.Collections.emptyList() // Authorities
            );
        } else {
            throw new AuthenticationException("Invalid username or password") {};
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Return true if the authentication type is UsernamePasswordAuthenticationToken
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
