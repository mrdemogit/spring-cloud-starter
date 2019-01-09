package com.example.service;

import com.example.model.UserProfileStats;
import com.example.repository.UserProfileStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserProfileStatsServiceImpl implements UserProfileStatsService {

    @Autowired
    private UserProfileStatsRepository userProfileStatsRepository;

    @Override
    public Mono<UserProfileStats> save(UserProfileStats userProfileStats) {
        return userProfileStatsRepository.save(userProfileStats);
    }
}
