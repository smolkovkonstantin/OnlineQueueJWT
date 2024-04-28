package org.online.queue.onlinequeuejwt.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.services.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

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
                .withExpiresAt(tokenCreateDto.getExpirationTime().toInstant(ZoneOffset.UTC))
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public void verify(String token) {

        var verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .build();

        verifier.verify(token);

    }

}
