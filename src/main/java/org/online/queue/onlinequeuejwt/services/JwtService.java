package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.api.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.ValidateRequest;

public interface JwtService {
    ResponseTokens create(CreateRequest createRequest);

    Long validate(String accessToken);

    ResponseTokens update(ValidateRequest validateRequest);
}
