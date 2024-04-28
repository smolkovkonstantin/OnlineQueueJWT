package org.online.queue.onlinequeuejwt.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.online.queue.onlinequeuejwt.models.api.JwtCreateRequest;
import org.online.queue.onlinequeuejwt.models.api.JwtResponse;
import org.online.queue.onlinequeuejwt.models.api.JwtValidateRequest;
import org.online.queue.onlinequeuejwt.services.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/jwt")
public class JwtController {

    JwtService jwtService;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> create(@RequestBody JwtCreateRequest jwtCreateRequest) {
        var jwtResponse = jwtService.create(jwtCreateRequest);
        return new ResponseEntity<>(jwtResponse, HttpStatus.CREATED);
    }

    @GetMapping("/token")
    public ResponseEntity<Void> validate(@RequestBody JwtValidateRequest jwtValidateRequest) {
        jwtService.validate(jwtValidateRequest.getToken());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
