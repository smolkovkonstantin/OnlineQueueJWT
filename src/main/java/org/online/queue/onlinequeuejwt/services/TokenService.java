package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.dto.TokenCreateDto;
import org.online.queue.onlinequeuejwt.models.dto.SessionDto;

public interface TokenService {
    String create(TokenCreateDto tokenCreateDto);

    SessionDto decode(String token);
}
