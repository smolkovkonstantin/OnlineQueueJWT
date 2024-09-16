package org.online.queue.onlinequeuejwt.models.api;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequest {

    @JsonSetter(nulls = Nulls.FAIL)
    private String token;

    @JsonSetter(nulls = Nulls.FAIL)
    private String deviceId;
}
