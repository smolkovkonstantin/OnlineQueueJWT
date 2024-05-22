package org.online.queue.onlinequeuejwt.models.api;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRequest {

    private Long userId;
    private String deviceId;

}
