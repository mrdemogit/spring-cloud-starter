package com.example.service;

import com.example.model.UserProfileStats;
import reactor.core.publisher.Mono;

public interface UserProfileStatsService {

    Mono<UserProfileStats> updateOrSaveRenderCount(UserProfileStats userProfileStats);
}
