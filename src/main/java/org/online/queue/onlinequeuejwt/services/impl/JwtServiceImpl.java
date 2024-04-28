package org.online.queue.onlinequeuejwt.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.api.JwtCreateRequest;
import org.online.queue.onlinequeuejwt.models.api.JwtResponse;
import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.online.queue.onlinequeuejwt.services.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    TokenService tokenService;

    static long LIFE_TIME_ACCESS_TOKEN = 15;
    static long LIFE_TIME_REFRESH_TOKEN = 30;

    @Override
    public JwtResponse create(JwtCreateRequest jwtCreateRequest) {

        var accessToken = tokenService.create(
                createTokenDto(jwtCreateRequest, LocalDateTime.now().plusMinutes(LIFE_TIME_ACCESS_TOKEN))
        );

        var refreshToken = tokenService.create(
                createTokenDto(jwtCreateRequest, LocalDateTime.now().plusDays(LIFE_TIME_REFRESH_TOKEN))
        );

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
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
        tokenService.verify(token);
    }
}
