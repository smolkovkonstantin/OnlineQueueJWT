package org.online.queue.onlinequeuejwt.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.api.request.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.request.DeleteRequest;
import org.online.queue.onlinequeuejwt.models.api.response.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.request.ValidateRequest;
import org.online.queue.onlinequeuejwt.models.api.response.ValidateResponse;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.online.queue.onlinequeuejwt.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtController {
    JwtService jwtService;
    SessionService sessionService;

    @PostMapping("/token")
    public ResponseEntity<ResponseTokens> create(@RequestBody CreateRequest createRequest) {
        var jwtResponse = jwtService.create(createRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED);
    }

    @PostMapping("/validation")
    public ResponseEntity<ValidateResponse> validate(@RequestBody ValidateRequest validateRequest) {
        var userId =  jwtService.validate(validateRequest.getToken());
        return ResponseEntity.ok(ValidateResponse.builder()
                        .userId(userId)
                .build());
    }

    @PutMapping("/token")
    public ResponseEntity<ResponseTokens> update(@RequestBody ValidateRequest validateRequest) {
        var jwtResponse = jwtService.update(validateRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @DeleteMapping("/token")
    public ResponseEntity<ResponseTokens> delete(@RequestBody DeleteRequest deleteRequest) {
        sessionService.delete(deleteRequest.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
