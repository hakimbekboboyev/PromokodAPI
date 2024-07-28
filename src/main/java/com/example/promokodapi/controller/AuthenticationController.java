package com.example.promokodapi.controller;

import com.example.promokodapi.dto.AuthenticationResponse;
import com.example.promokodapi.dto.auth.AuthenticationRequest;
import com.example.promokodapi.dto.auth.RegisterRequest;
import com.example.promokodapi.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticateService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest registerRequest) {
        return ResponseEntity.ok(authenticateService.authenticate(registerRequest));
    }

}
