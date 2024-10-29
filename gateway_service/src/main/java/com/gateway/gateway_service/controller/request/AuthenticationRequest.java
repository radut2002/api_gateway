package com.gateway.gateway_service.controller.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;




public class AuthenticationRequest {
    
    public final String username;
    public final String secret;
        

    @JsonCreator
    public AuthenticationRequest( 
      @JsonProperty("username")  String username, @JsonProperty("secret") String secret) {
        this.username = username;
        this.secret = secret;
    }

    @Override
    public String toString() {
        return String.format("Credentials[username=%s, secret=HIDDEN]", username);
    }
}
