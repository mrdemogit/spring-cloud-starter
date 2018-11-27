package com.example.controller;

import com.example.mapper.UserProfileMapper;
import com.example.model.UserProfile;
import com.example.request.UserProfileRequest;
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

    @Autowired
    private UserProfileMapper userProfileMapper;

    @GetMapping("/{lastName}")
    public Flux<UserProfile> getUserProfile(@PathVariable String lastName) {
        return userProfileService.findByLastName(lastName);
    }

    @PostMapping
    public Mono<UserProfile> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
        return userProfileService.createProfile(userProfileRequest.getFirstName(), userProfileRequest.getLastName());
    }
}
