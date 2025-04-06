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

    // Constructor injection for UserService and JwtUtils
    public UserNameAndPasswordAuthentication(UserService userService, jwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); 
        String password = (String) authentication.getCredentials(); 

 
        boolean isAuthenticated = userService.authenticate(username, password); 

        if (isAuthenticated) {
           
            String token = jwtUtils.generateAccessToken(username); 


            return new UsernamePasswordAuthenticationToken(
                    new User(username, password, java.util.Collections.emptyList()),
                    token,
                    java.util.Collections.emptyList() 
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
