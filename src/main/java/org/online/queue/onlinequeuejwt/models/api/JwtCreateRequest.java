package org.online.queue.onlinequeuejwt.models.api;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtCreateRequest {

    private Long userId;
    private String deviceId;

}
