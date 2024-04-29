package org.online.queue.onlinequeuejwt.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.api.JwtCreateRequest;
import org.online.queue.onlinequeuejwt.models.api.JwtResponse;
import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.online.queue.onlinequeuejwt.services.SessionService;
import org.online.queue.onlinequeuejwt.services.TokenService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    TokenService tokenService;
    SessionService sessionService;

    static long LIFE_TIME_ACCESS_TOKEN = 15;
    static long LIFE_TIME_REFRESH_TOKEN = 30;

    @Override
    public JwtResponse create(JwtCreateRequest jwtCreateRequest) {

        var accessExpiresAt = LocalDateTime.now().plusMinutes(LIFE_TIME_ACCESS_TOKEN);
        var accessToken = tokenService.create(createTokenDto(jwtCreateRequest, accessExpiresAt));

        var refreshExpiresAt = LocalDateTime.now().plusDays(LIFE_TIME_REFRESH_TOKEN);
        var refreshToken = tokenService.create(createTokenDto(jwtCreateRequest, refreshExpiresAt));

        saveSession(jwtCreateRequest, refreshToken);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void  saveSession(JwtCreateRequest jwtCreateRequest, String refreshToken) {

        var lifeTime = Duration.ofDays(LIFE_TIME_REFRESH_TOKEN);

        var sessionDto = SessionDto.builder()
                .userId(jwtCreateRequest.getUserId())
                .deviceId(jwtCreateRequest.getDeviceId())
                .refreshToken(refreshToken)
                .lifeTime(lifeTime)
                .build();

        sessionService.create(sessionDto);
    }

    private TokenCreateDto createTokenDto(JwtCreateRequest jwtCreateRequest, LocalDateTime expiredTime) {
        return TokenCreateDto.builder()
                .userId(jwtCreateRequest.getUserId())
                .deviceId(jwtCreateRequest.getDeviceId())
                .expirationTime(expiredTime)
                .build();
    }

    @Override
    public void validate(String token) {
        var tokenDto = tokenService.verify(token);

        sessionService.validate(tokenDto);
    }
}
