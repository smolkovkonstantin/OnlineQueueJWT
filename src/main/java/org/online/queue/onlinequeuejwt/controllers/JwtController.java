package org.online.queue.onlinequeuejwt.controllers;

import org.online.queue.onlinequeuejwt.models.api.CreateRequest;
import org.online.queue.onlinequeuejwt.models.api.DeleteRequest;
import org.online.queue.onlinequeuejwt.models.api.ResponseTokens;
import org.online.queue.onlinequeuejwt.models.api.ValidateRequest;
import org.online.queue.onlinequeuejwt.models.api.ValidateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface JwtController {

    @PostMapping("/token")
    ResponseEntity<ResponseTokens> create(@RequestBody CreateRequest createRequest);

    @PostMapping("/validation")
    ResponseEntity<ValidateResponse> validate(@RequestBody ValidateRequest validateRequest);

    @PutMapping("/token")
    ResponseEntity<ResponseTokens> update(@RequestBody ValidateRequest validateRequest);

    @DeleteMapping("/token")
    ResponseEntity<ResponseTokens> delete(@RequestBody DeleteRequest deleteRequest);

}
