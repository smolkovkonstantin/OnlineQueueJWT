package org.online.queue.onlinequeuejwt.exceptions;

import lombok.Getter;
import org.online.queue.onlinequeuejwt.models.api.ErrorResponse;

@Getter
public class ForbiddenException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public ForbiddenException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

}
