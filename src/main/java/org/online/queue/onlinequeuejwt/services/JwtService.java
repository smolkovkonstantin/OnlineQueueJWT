package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.api.JwtCreateRequest;
import org.online.queue.onlinequeuejwt.models.api.JwtResponse;

public interface JwtService {
    JwtResponse create(JwtCreateRequest jwtCreateRequest);

    void validate(String token);
}
