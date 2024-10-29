package com.gateway.gateway_service.services;

import com.gateway.gateway_service.controller.request.AuthenticationRequest;

import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<Void> register(AuthenticationRequest request);
    Mono<Void> login();
}