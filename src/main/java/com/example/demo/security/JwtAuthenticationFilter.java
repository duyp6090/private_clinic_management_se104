package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;





@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final jwtUtils jwtUtils;

    public JwtAuthenticationFilter(jwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var jwt = getJwtFromRequest(request);
        System.out.println(jwt);

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            var username = jwtUtils.getUserNameFromJwtToken(jwt);
            List<GrantedAuthority> authorities = jwtUtils.getAuthoritiesFromToken(jwt);
            var authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            System.out.println("Granted Authorities: " + authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        System.out.println("Bearer Token");
        System.out.println(bearerToken);
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ? bearerToken.substring(7) : null;
    }
}
