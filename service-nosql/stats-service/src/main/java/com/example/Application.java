package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@EnableMongoAuditing
@EnableDiscoveryClient
@SpringBootApplication
public class Application {

    @Bean
    public LoggingEventListener mongoEventListener() {
        return new LoggingEventListener();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}