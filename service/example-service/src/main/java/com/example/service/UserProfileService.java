package com.example.service;

import com.example.model.UserProfile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserProfileService {

    Flux<UserProfile> findByLastName(String lastName);

    Mono<UserProfile> createProfile(String firstName, String lastName);

}
