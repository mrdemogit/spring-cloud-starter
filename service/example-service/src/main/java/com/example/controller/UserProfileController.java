package com.example.controller;

import com.example.mapper.UserProfileMapper;
import com.example.request.UserProfileRequest;
import com.example.response.UserProfileResponse;
import com.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/userprofiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{lastName}")
    public Flux<UserProfileResponse> getUserProfile(@PathVariable String lastName) {
        return userProfileService.findByLastName(lastName).map(UserProfileMapper::toResponse);
    }

    @PostMapping
    public Mono<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        return userProfileService
            .createProfile(userProfileRequest.getFirstName(), userProfileRequest.getLastName())
            .map(UserProfileMapper::toResponse);
    }
}
