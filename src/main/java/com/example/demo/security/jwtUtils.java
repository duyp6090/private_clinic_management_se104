package com.example.demo.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.common.util.StringUtils;

@Component
public class jwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.access:86400000}")
    private long accessTokenExpiration;

    @Value("${jwt.expiration.refresh:604800000}") 
    private long refreshTokenExpiration;

    // Generate Access Token
    public String generateAccessToken(String username, List<String> roles) {
        return generateToken(username, roles, accessTokenExpiration);
    }

    // Generate Refresh Token
    public String generateRefreshToken(String username,List<String> roles) {
        return generateToken(username, roles, refreshTokenExpiration);
    }

    // Helper method to generate token
    private String generateToken(String username, List<String> roles, long expirationTime) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        // Add roles to the token if they exist
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .claim("roles", roles != null ? roles : null) // Add roles to the token
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Extract and return the authorities (roles) from the JWT token
     * @param token - The JWT token
     * @return List of granted authorities (roles)
     */
    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = parseClaimsFromToken(token);
        System.out.println(claims);
        List<String> roles = claims.get("roles", List.class);
        System.out.println(roles);
        return roles.stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
        .collect(Collectors.toList());
    }

    /**
     * Validate if the JWT token is valid
     * @param token - The JWT token
     * @return True if token is valid, false otherwise
     */
    public boolean validateJwtToken(String token) {
        try {
            parseClaimsFromToken(token); // If parsing fails, it's invalid
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the error here (e.g., invalid token, expired token, etc.)
            return false;
        }
    }

    /**
     * Get the username (subject) from the JWT token
     * @param token - The JWT token
     * @return Username (subject) from the token
     */
    public String getUserNameFromJwtToken(String token) {
        Claims claims = parseClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * Check if the token is expired
     * @param token - The JWT token
     * @return True if token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        Date expirationDate = parseClaimsFromToken(token).getExpiration();
        return expirationDate.before(new Date());
    }

    /**
     * Private method to parse claims from the JWT token
     * @param token - The JWT token
     * @return Claims extracted from the token
     * @throws JwtException If token is invalid or expired
     */
    private Claims parseClaimsFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new JwtException("Token is empty or null");
        }

        try {
            return Jwts.parserBuilder() // Using the new parser method
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Invalid or expired JWT token", e);
        }
    }
}
