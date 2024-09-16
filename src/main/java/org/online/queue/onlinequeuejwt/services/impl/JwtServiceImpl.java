package org.online.queue.onlinequeuejwt.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.api.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.ValidateRequest;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;
import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.online.queue.onlinequeuejwt.services.SessionService;
import org.online.queue.onlinequeuejwt.services.TokenService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    TokenService tokenService;
    SessionService sessionService;

    static long LIFE_TIME_ACCESS_TOKEN = 60 * 5;
    static long LIFE_TIME_REFRESH_TOKEN = 60 * 60 * 24 * 30;

    @Override
    public ResponseTokens create(CreateRequest createRequest) {

        var accessExpiresAt = Instant.now().plusSeconds(LIFE_TIME_ACCESS_TOKEN);
        var accessToken = tokenService.create(createTokenDto(createRequest, accessExpiresAt));

        var refreshExpiresAt = Instant.now().plusSeconds(LIFE_TIME_REFRESH_TOKEN);
        var refreshToken = tokenService.create(createTokenDto(createRequest, refreshExpiresAt));

        saveSession(createRequest, refreshToken);

        return ResponseTokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private TokenCreateDto createTokenDto(CreateRequest createRequest, Instant expiredTime) {
        return TokenCreateDto.builder()
                .userId(createRequest.getUserId())
                .deviceId(createRequest.getDeviceId())
                .expirationTime(expiredTime)
                .build();
    }

    private void  saveSession(CreateRequest createRequest, String refreshToken) {

        var lifeTime = Duration.ofSeconds(LIFE_TIME_REFRESH_TOKEN);

        var sessionDto = SessionDto.builder()
                .userId(createRequest.getUserId())
                .deviceId(createRequest.getDeviceId())
                .refreshToken(refreshToken)
                .lifeTime(lifeTime)
                .build();

        sessionService.create(sessionDto);
    }

    @Override
    public Long validate(String accessToken) {
        return tokenService.decode(accessToken)
                .getUserId();
    }

    @Override
    public ResponseTokens update(ValidateRequest validateRequest) {
        var tokenDto = tokenService.decode(validateRequest.getToken());
        tokenDto.setDeviceId(validateRequest.getDeviceId());

        sessionService.update(tokenDto);

        var jwtCreateRequest = CreateRequest.builder()
                .userId(tokenDto.getUserId())
                .deviceId(tokenDto.getDeviceId())
                .build();

        return create(jwtCreateRequest);
    }
}
