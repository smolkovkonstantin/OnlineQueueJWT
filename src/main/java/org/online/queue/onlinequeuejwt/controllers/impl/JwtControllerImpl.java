package org.online.queue.onlinequeuejwt.controllers.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.controllers.JwtController;
import org.online.queue.onlinequeuejwt.models.api.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.DeleteRequest;
import org.online.queue.onlinequeuejwt.models.api.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.ValidateRequest;
import org.online.queue.onlinequeuejwt.models.api.ValidateResponse;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.online.queue.onlinequeuejwt.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtControllerImpl implements JwtController {
    JwtService jwtService;
    SessionService sessionService;

    @Override
    public ResponseEntity<ResponseTokens> create(CreateRequest createRequest) {
        var jwtResponse = jwtService.create(createRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ValidateResponse> validate(ValidateRequest validateRequest) {
        var userId =  jwtService.validate(validateRequest.getToken());
        return ResponseEntity.ok(ValidateResponse.builder()
                        .userId(userId)
                .build());
    }

    @Override
    public ResponseEntity<ResponseTokens> update(ValidateRequest validateRequest) {
        var jwtResponse = jwtService.update(validateRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseTokens> delete(DeleteRequest deleteRequest) {
        sessionService.delete(deleteRequest.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
