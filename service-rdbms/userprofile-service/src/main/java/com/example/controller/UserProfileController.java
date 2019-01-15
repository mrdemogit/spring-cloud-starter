package com.example.controller;

import com.example.config.SharedProperties;
import com.example.mapper.UserProfileMapper;
import com.example.producer.UserProfileProducer;
import com.example.producer.UserProfileStatsRequest;
import com.example.response.UserProfileResponse;
import com.example.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/userprofiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private SharedProperties sharedProperties;

    @Autowired
    private UserProfileProducer userProfileProducer;

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
        return userProfileService.findByRandom().doOnSuccess(userProfile -> {
            UserProfileStatsRequest userProfileStatsRequest = new UserProfileStatsRequest();
            userProfileStatsRequest.setDate(new Date());
            userProfileStatsRequest.setUserProfileId(userProfile.getId());
            userProfileProducer.sendUserProfile(userProfileStatsRequest);
        }).map(UserProfileMapper::toResponse);
    }

//    @PostMapping
//    public Mono<UserProfileResponse> createUserProfile(@RequestBody UserProfileRequest userProfileRequest) {
//        return userProfileService
//            .create(userProfileRequest.getFirstName(), userProfileRequest.getLastName())
//            .map(UserProfileMapper::toResponse);
//    }
}
