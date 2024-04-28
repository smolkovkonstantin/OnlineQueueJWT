package org.online.queue.onlinequeuejwt.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.online.queue.onlinequeuejwt.models.api.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ControllerAdvice
public class ExceptionServiceHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerificationException exception) {
        log.error(exception.getMessage());

        var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        var errorResponse =  new ErrorResponse()
                .setMessage(exception.getMessage())
                .setDateTime(dateTime);

        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
