package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/userprofile-fallback")
    public Mono<String> fallback() {
        return Mono.just("Service currently is not working, please try again later");
    }
}
