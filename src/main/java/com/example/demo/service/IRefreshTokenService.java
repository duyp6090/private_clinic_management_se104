/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.example.demo.service;

import java.util.Optional;

import com.example.demo.domain.RefreshToken;

/**
 *
 * @author iset1enloc
 */

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(String username);

    RefreshToken createRefreshTokenByExistingToken(String oldToken);

    boolean isValidToken(String token);

    void revokeToken(String token);

    void revokeAllTokensForUser(Long userId);

    void expireTokenIfNeeded(RefreshToken token);

    void deleteToken(String token);

    void cleanUpExpiredTokens();

    Optional<RefreshToken> findByToken(String token);
}
