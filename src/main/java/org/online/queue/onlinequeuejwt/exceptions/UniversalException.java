package org.online.queue.onlinequeuejwt.exceptions;

import org.online.queue.onlinequeuejwt.models.api.response.ErrorResponse;

public abstract class UniversalException extends RuntimeException {

    public UniversalException(String message) {
        super(message);
    }

    public abstract ErrorResponse getErrorResponse();
}
