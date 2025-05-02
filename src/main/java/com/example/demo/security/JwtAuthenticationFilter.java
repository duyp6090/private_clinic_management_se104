package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        String path = request.getRequestURI();

        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);

            // Get user roles (already as GrantedAuthority list)
            List<GrantedAuthority> authorities = jwtUtils.getRoleGrantAuthoritiesFromToken(jwt);

            if (!path.equals("/api/auth/login-with-permission")) {
                //Get user permissions (raw strings)
                List<String> permissions = jwtUtils.getPermissionsAuthoritiesFromToken(jwt);
                if (permissions == null) {
                    permissions = new ArrayList<>(); // safely default to empty
                }
                // Add permissions as authorities
                for (String permission : permissions) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
            }

            System.out.println("Granted Authorities: " + authorities);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        return (bearerToken != null && bearerToken.startsWith("Bearer ")) ? bearerToken.substring(7) : null;
    }
}
