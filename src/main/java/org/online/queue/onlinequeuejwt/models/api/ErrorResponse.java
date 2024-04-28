package org.online.queue.onlinequeuejwt.models.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ErrorResponse {
    private String message;
    private String dateTime;
}
