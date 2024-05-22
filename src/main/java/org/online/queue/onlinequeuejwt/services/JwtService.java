package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.api.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.ResponseTokens;

public interface JwtService {
    ResponseTokens create(CreateRequest createRequest);

    void validate(String accessToken);

    ResponseTokens update(String refreshToken);
}
