package com.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/circuitBreaker/fallback")
    public Mono<String> circuitBreakerFoodFallback(){
        return Mono.just("Food service is down . Contact to service provider.");
    }
}
