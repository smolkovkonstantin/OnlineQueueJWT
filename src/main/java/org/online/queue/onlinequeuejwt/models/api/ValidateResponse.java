package org.online.queue.onlinequeuejwt.models.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidateResponse {
    Long userId;
}
