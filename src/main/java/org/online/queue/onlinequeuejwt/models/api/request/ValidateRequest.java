package org.online.queue.onlinequeuejwt.models.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequest {

    @JsonProperty
    @JsonSetter(nulls = Nulls.FAIL)
    private String token;

    @JsonProperty
    @JsonSetter(nulls = Nulls.FAIL)
    private String deviceId;
}
