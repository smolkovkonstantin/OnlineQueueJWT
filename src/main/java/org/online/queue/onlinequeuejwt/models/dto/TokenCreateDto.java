package org.online.queue.onlinequeuejwt.models.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDateTime;

@Value
@Builder
public class TokenCreateDto {

    Long userId;
    String deviceId;
    Instant expirationTime;

}
