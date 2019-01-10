package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Gateway {

    @Value("${service.userprofile}")
    private String USER_PROFILE_SERVICE;

    public static void main(String[] args) {
        SpringApplication.run(Gateway.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("userProfiles", r -> r
                .path("/api/userprofiles/**")
                .filters(f -> f.rewritePath("(?<param>.*)", "${param}")
                    .hystrix(config -> config.setFallbackUri("forward:/userprofile-fallback"))
                )
                .uri(String.format("lb://%s",USER_PROFILE_SERVICE))
            )
            .build();
    }
}
