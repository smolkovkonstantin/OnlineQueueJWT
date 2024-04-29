package org.online.queue.onlinequeuejwt.models.dto;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;

@Value
@Builder
public class SessionDto {

    Long userId;
    String deviceId;
    String refreshToken;
    Duration lifeTime;

}
