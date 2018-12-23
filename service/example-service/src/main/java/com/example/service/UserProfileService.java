package com.example.service;

import com.example.model.UserProfile;
import reactor.core.publisher.Mono;

public interface UserProfileService {

    Mono<UserProfile> findById(Long id);

    Mono<UserProfile> createProfile(String firstName, String lastName);

    Mono<UserProfile> findByRandom();
}
