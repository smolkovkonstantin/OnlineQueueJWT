package org.online.queue.onlinequeuejwt.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;
import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private final static String USER_ID = "userId";
    private final static String DEVICE_ID = "deviceId";

    @Value("${secret}")
    private String secret;

    @Value("${spring.application.name}")
    private String issuer;

    @Override
    public String create(TokenCreateDto tokenCreateDto) {
        return JWT.create()
                .withClaim(USER_ID, tokenCreateDto.getUserId())
                .withClaim(DEVICE_ID, tokenCreateDto.getDeviceId())
                .withIssuer(issuer)
                .withIssuedAt(Instant.now())
                .withExpiresAt(tokenCreateDto.getExpirationTime())
                .sign(Algorithm.HMAC512(secret));
    }

    @Override
    public SessionDto decode(String token) {

        var verifier = JWT.require(Algorithm.HMAC512(secret))
                .withIssuer(issuer)
                .withClaimPresence(DEVICE_ID)
                .withClaimPresence(USER_ID)
                .build();

        var decodedTwt = verifier.verify(token);

        return SessionDto.builder()
                .userId(decodedTwt.getClaim(USER_ID).asLong())
                .deviceId(decodedTwt.getClaim(DEVICE_ID).asString())
                .refreshToken(decodedTwt.getToken())
                .build();
    }

}
