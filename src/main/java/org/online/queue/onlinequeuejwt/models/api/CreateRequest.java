package org.online.queue.onlinequeuejwt.models.api;


import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRequest {

    @JsonSetter(nulls = Nulls.FAIL)
    private Long userId;

    @JsonSetter(nulls = Nulls.FAIL)
    private String deviceId;

}
