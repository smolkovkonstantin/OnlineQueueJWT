package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;

public interface TokenService {
    String create(TokenCreateDto tokenCreateDto);

    void verify(String token);
}
