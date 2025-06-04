package org.online.queue.onlinequeuejwt.models.api.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResponseTokens {

    String accessToken;
    String refreshToken;

}
