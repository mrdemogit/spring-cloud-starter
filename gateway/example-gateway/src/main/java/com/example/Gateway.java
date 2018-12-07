package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {

    private final static String USER_PROFILE_SERVICE = "http://localhost:8100";

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("userProfilesByLastName", r -> r
                .path("/userprofiles/**")
                .filters(f -> f.rewritePath("(?<lastName>.*)", "${lastName}")
                    .hystrix(config -> config.setFallbackUri("forward:/userprofile-fallback"))
                )
                .uri(USER_PROFILE_SERVICE)
            )
            .build();
    }
}
