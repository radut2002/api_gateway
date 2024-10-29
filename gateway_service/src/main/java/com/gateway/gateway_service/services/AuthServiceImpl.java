package com.gateway.gateway_service.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.gateway.gateway_service.controller.request.AuthenticationRequest;

import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired   
    private WebClient webClient;

    @Override
    public Mono<Void> register(AuthenticationRequest request) {
       return webClient
                .method(HttpMethod.POST)
                //.uri("http://localhost:8080/auth" + "/register")
                //.contentType(MediaType.APPLICATION_JSON)
                //.accept(MediaType.APPLICATION_JSON)
                //.body(BodyInserters.fromValue(request))
                .bodyValue(request)                
                .retrieve()
                .onStatus(HttpStatus::isError, response -> {
                    //log.error("An Error Occurred Getting Iced Coffees");
                    return Mono.just(new Exception("An Error Occurred on register call"));
                })
                .bodyToMono(Void.class);
                
    }

    @Override
    public Mono<Void> login() {
        return null;
    }
}