package com.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes().
                route(
                        "food-service",route->route.path("/foods/**")
                                .filters(f->f.rewritePath("/foods/(?<segment>.*)", "/${segment}"))
                                .uri("lb://food-service")
                )
                .build();
    }
}
