package com.gateway.gateway_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gateway.gateway_service.controller.request.AuthenticationRequest;
import com.gateway.gateway_service.services.AuthService;

@RestController

public class AuthController {

    @Autowired
    private  AuthService authService;

    @PostMapping(value = "/auth/register")
    public ResponseEntity<Void> registerUser(
            @RequestBody AuthenticationRequest request
    ) {

        authService.register(request);


        // todo: Generate JWT
        return null;
    }
}
