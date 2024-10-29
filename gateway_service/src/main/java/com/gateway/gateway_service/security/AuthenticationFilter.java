package com.gateway.gateway_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RouterValidator routerValidator;
    //@Autowired
    //private JwtUtil jwtUtil;

    //@Value("${jwt.prefix}")
    //public String TOKEN_PREFIX;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routerValidator.isSecured.test(request)) {
            

            //final String token = this.getAuthHeader(request);

            //if (jwtUtil.isInvalid(token))
              //  return this.onError(exchange, "Authorization header is invalid", HttpStatus.UNAUTHORIZED);

            this.populateRequestWithHeaders(exchange);
        }
        return chain.filter(exchange);
    }


    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);        
        return response.setComplete();
    }

   

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    

    private void populateRequestWithHeaders(ServerWebExchange exchange) {        
        exchange.getRequest().mutate()
            .header(HttpHeaders.SET_COOKIE, getCookieValue(exchange.getRequest(), "r_token")).build();
    }

    private String getCookieValue(ServerHttpRequest req, String cookieName) {
    return req.getCookies()
            .getFirst(cookieName)
            .getValue();
    }
}
