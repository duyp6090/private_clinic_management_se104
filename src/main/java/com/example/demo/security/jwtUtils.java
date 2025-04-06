package com.example.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.access}")
    private long accessTokenExpiration;

    @Value("${jwt.expiration.refresh}")
    private long refreshTokenExpiration;

    // Generate Access Token
    public String generateAccessToken(String username) {
        return generateToken(username, accessTokenExpiration);
    }

    // Generate Refresh Token
    public String generateRefreshToken(String username) {
        return generateToken(username, refreshTokenExpiration);
    }

    // Helper method to generate token
    @SuppressWarnings("deprecation")
    private String generateToken(String username, long expirationTime) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    // Validate the JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder() // Updated method for JWT parsing
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // Log invalid token scenario if needed
        }
    }

    // Get the username from JWT token
    public String getUserNameFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder() // Updated method for JWT parsing
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
