package org.online.queue.onlinequeuejwt.services;

import org.online.queue.onlinequeuejwt.models.api.request.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.response.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.request.ValidateRequest;

public interface JwtService {
    ResponseTokens create(CreateRequest createRequest);

    Long validate(String accessToken);

    ResponseTokens update(ValidateRequest validateRequest);
}
