package org.online.queue.onlinequeuejwt.models.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Date;

@Value
@Builder
public class TokenCreateDto {

    Long userId;
    String deviceId;
    LocalDateTime expirationTime;

}
