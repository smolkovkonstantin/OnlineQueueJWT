package org.online.queue.onlinequeuejwt.enums;


import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.online.queue.onlinequeuejwt.models.api.response.ErrorResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Slf4j
public enum ErrorTypeEnum {

    JWT_EXCEPTION("Jwt exception"),
    INVALID_SESSION("Current session is not present in data base or invalid");

    private final String value;

    public ErrorResponse build(JWTVerificationException exception) {
        log.error(exception.getMessage());

        var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

        return new ErrorResponse()
                .setMessage(exception.getMessage())
                .setDateTime(dateTime);
    }

    public ErrorResponse build(Object... objects) {
        var dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
        var message = String.format(value, objects);

        log.error(message);

        return new ErrorResponse()
                .setMessage(message)
                .setDateTime(dateTime);
    }
}
