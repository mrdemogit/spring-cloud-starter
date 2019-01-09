package com.example.controller;

import com.example.config.SharedProperties;
import com.example.mapper.UserProfileMapper;
import com.example.request.UserProfileRequest;
import com.example.response.UserProfileResponse;
import com.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/userprofiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private SharedProperties sharedProperties;

    @GetMapping("/attribute")
    public Mono<String> getTestProp() {
        return Mono.just(sharedProperties.getAttribute());
    }

    @GetMapping("/{id}")
    public Mono<UserProfileResponse> getUserProfileById(@PathVariable Long id) {
        return userProfileService.findById(id).map(UserProfileMapper::toResponse);
    }

    @GetMapping("/random")
    public Mono<UserProfileResponse> getUserProfileRandom() {
        return userProfileService.findByRandom().map(UserProfileMapper::toResponse);
    }

    @PostMapping
    public Mono<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        return userProfileService
            .createProfile(userProfileRequest.getFirstName(), userProfileRequest.getLastName())
            .map(UserProfileMapper::toResponse);
    }
}
