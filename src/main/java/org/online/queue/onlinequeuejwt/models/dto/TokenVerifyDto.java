package org.online.queue.onlinequeuejwt.models.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenVerifyDto {
    String message;
    Boolean hasException;
}
