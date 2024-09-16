package org.online.queue.onlinequeuejwt.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.online.queue.onlinequeuejwt.models.api.ErrorResponse;

@Getter
public class UnauthorizedException extends UniversalException {


    private final ErrorResponse errorResponse;

    public UnauthorizedException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

}
