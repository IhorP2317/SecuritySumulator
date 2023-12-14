package com.securitySimulator.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import com.securitySimulator.exception.TokenRefreshException;
import com.securitySimulator.model.user.RefreshToken;
import com.securitySimulator.model.user.User;
import com.securitySimulator.repository.RefreshTokenRepository;
import com.securitySimulator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {
    @Value("${securitySimulator.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).get();

        Optional<RefreshToken> verifiedRefreshToken = refreshTokenRepository.findByUser(user);

        if (!verifiedRefreshToken.isEmpty() && verifyExpiration(verifiedRefreshToken.get()) != null) {
            return verifiedRefreshToken.get();
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    @Transactional
    public int deleteByUser(User user) {
        return refreshTokenRepository.deleteByUser(user);
    }

}
