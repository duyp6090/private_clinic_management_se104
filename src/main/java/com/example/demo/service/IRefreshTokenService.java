package com.example.demo.service;

/**
 *
 * @author iset1enloc
 */
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.RefreshToken;
import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.security.jwtUtils;
import com.example.demo.service.service_implementation.UserServiceImpl;

@Service
public class IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserServiceImpl userService;
    private final jwtUtils jwtProvider;
    public IRefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserServiceImpl userService,jwtUtils jwtProvider) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(jwtProvider.generateRefreshToken(username,userService.getUserRolesByUserName(username)));

        refreshToken.setExpirationDate(LocalDateTime.now().plusDays(7));
        refreshToken.setRevoke(false);
        refreshToken.setExpired(false);

        userService.findByUsername(username).ifPresent(user -> refreshToken.setUser(user));

        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isValidToken(String token) {
        Optional<RefreshToken> optionalToken = refreshTokenRepository.findByToken(token);
        if (optionalToken.isEmpty()) return false;

        RefreshToken refreshToken = optionalToken.get();
        return !refreshToken.isExpired() &&
               !refreshToken.isRevoke() &&
               refreshToken.getExpirationDate().isAfter(LocalDateTime.now());
    }

    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(t -> {
            t.setRevoke(true);
            refreshTokenRepository.save(t);
        });
    }

    public void expireTokenIfNeeded(RefreshToken token) {
        if (token.getExpirationDate().isBefore(LocalDateTime.now())) {
            token.setExpired(true);
            refreshTokenRepository.save(token);
        }
    }

    public void revokeAllTokensForUser(Long userId) {
        List<RefreshToken> tokens = refreshTokenRepository.findByUserIdAndIsExpiredFalseAndIsRevokeFalse(userId);
        for (RefreshToken token : tokens) {
            token.setRevoke(true);
        }
        refreshTokenRepository.saveAll(tokens);
    }

    public void deleteToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public void cleanUpExpiredTokens() {
        List<RefreshToken> expiredTokens = refreshTokenRepository.findAllByExpirationDateBefore(LocalDateTime.now());
        refreshTokenRepository.deleteAll(expiredTokens);
    }

    public Optional<RefreshToken> findByToken(String oldToken) {
        Optional<RefreshToken> optionalOldToken = refreshTokenRepository.findByToken(oldToken);
        return optionalOldToken;
    }
    public RefreshToken createRefreshTokenByExistingToken(String oldToken) {
        Optional<RefreshToken> optionalOldToken = refreshTokenRepository.findByToken(oldToken);
        
        if (optionalOldToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid or expired refresh token");
        }
    
        RefreshToken oldRefreshToken = optionalOldToken.get();
        if (oldRefreshToken.isExpired() || oldRefreshToken.isRevoke()) {
            throw new IllegalArgumentException("Refresh token is expired or revoked");
        }
    
        oldRefreshToken.setRevoke(true);
        refreshTokenRepository.save(oldRefreshToken);
        String username= oldRefreshToken.getUser().getName();
        return createRefreshToken(username);
    }
    
}
