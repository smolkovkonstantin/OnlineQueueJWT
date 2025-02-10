package org.online.queue.onlinequeuejwt.exceptions.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.online.queue.onlinequeuejwt.enums.ErrorTypeEnum;
import org.online.queue.onlinequeuejwt.exceptions.ForbiddenException;
import org.online.queue.onlinequeuejwt.models.api.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionServiceHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerificationException exception) {

        var errorResponse = ErrorTypeEnum.JWT_EXCEPTION.build(exception);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException exception) {
        return new ResponseEntity<>(exception.getErrorResponse(), HttpStatus.FORBIDDEN);
    }
}
