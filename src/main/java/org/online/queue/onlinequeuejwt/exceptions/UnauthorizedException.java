package org.online.queue.onlinequeuejwt.exceptions;

import lombok.Getter;
import org.online.queue.onlinequeuejwt.models.api.response.ErrorResponse;

@Getter
public class UnauthorizedException extends UniversalException {


    private final ErrorResponse errorResponse;

    public UnauthorizedException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

}
