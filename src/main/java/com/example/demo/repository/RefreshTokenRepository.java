/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo.repository;

/**
 *
 * @author iset1enloc
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // Find a token by its string value
    Optional<RefreshToken> findByToken(String token);

    // Check if a token exists
    boolean existsByToken(String token);

    // Delete all tokens by a user's ID
    void deleteByUserId(Long userId);

    // Get all tokens for a user
    List<RefreshToken> findAllByUserId(Long userId);

    // Get valid (non-expired & not revoked) tokens by user
    List<RefreshToken> findByUserIdAndIsExpiredFalseAndIsRevokeFalse(Long userId);

    // Find all expired tokens
    List<RefreshToken> findAllByExpirationDateBefore(LocalDateTime dateTime);

    // Find all revoked tokens
    List<RefreshToken> findAllByIsRevokeTrue();

    // Find all active tokens (not expired and not revoked)
    List<RefreshToken> findAllByIsExpiredFalseAndIsRevokeFalse();

    // Optional: delete specific token by token string
    void deleteByToken(String token);
}
