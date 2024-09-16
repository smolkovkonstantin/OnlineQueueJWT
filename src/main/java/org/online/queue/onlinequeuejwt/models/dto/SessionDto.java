package org.online.queue.onlinequeuejwt.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.Duration;

@Builder
@Setter
@Getter
public class SessionDto {

    private Long userId;
    private String deviceId;
    private String refreshToken;
    private Duration lifeTime;

}
