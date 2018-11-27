package com.example.service;

import com.example.model.UserProfile;
import com.example.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private Scheduler jdbcScheduler;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public Flux<UserProfile> findByLastName(String lastName) {
        return Flux.defer(() -> Flux.fromIterable(
            userProfileRepository.findByLastNameAllIgnoringCase(lastName)
        )).subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<UserProfile> createProfile(String firstName, String lastName) {
        return Mono.fromCallable(() -> transactionTemplate.execute(
            (status) -> {
                UserProfile userProfile = new UserProfile();
                userProfile.setFirstName(firstName);
                userProfile.setLastName(lastName);
                return userProfileRepository.save(userProfile);
            }
        )).subscribeOn(jdbcScheduler);
    }
}
