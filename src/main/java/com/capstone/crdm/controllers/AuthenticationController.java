package com.capstone.crdm.controllers;

import com.capstone.crdm.request.AuthenticationRequest;
import com.capstone.crdm.security.authentication.AuthenticationResponse;
import com.capstone.crdm.security.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/oauth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(this.authenticationService.getAccessToken(authenticationRequest));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(this.authenticationService.refreshToken(authenticationRequest));
    }

}
