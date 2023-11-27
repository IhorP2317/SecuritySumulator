package com.securitySimulator.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.securitySimulator.exception.TokenRefreshException;
import com.securitySimulator.model.user.RefreshToken;
//import com.securitySimulator.repository.RefreshTokenRepository;
import com.securitySimulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//@Service
//public class RefreshTokenService {
//    @Value("${securitySimulator.app.jwtRefreshExpirationMs}")
//    private Long refreshTokenDurationMs;
//
//    @Autowired
//    RefreshTokenRepository refreshTokenRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    public Optional<RefreshToken> findByToken(String token) {
//        return refreshTokenRepository.findByToken(token);
//    }
//
//    public RefreshToken createRefreshToken(Long userId) {
//        RefreshToken refreshToken = new RefreshToken();
//
//        refreshToken.setUser(userRepository.findById(userId).get());
//        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
//        refreshToken.setToken(UUID.randomUUID().toString());
//
//        refreshToken = refreshTokenRepository.save(refreshToken);
//        return refreshToken;
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken token) {
//        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
//            refreshTokenRepository.delete(token);
//            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
//        }
//
//        return token;
//    }
//
//}
