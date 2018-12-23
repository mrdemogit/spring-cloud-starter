package com.example.service;

import com.example.model.UserProfile;
import com.example.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
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
    public Mono<UserProfile> findById(Long id) {
        return Mono.fromCallable(() ->
                userProfileRepository
                        .findById(id)
                        .orElse(null)
        )
                .subscribeOn(jdbcScheduler);
    }

    @Override
    public Mono<UserProfile> findByRandom() {
        return Mono.fromCallable(() -> transactionTemplate.execute(
                (status) -> {
                    Long countAll = userProfileRepository.count();
                    int randomIndex = (int) (Math.random() * countAll);
                    Page<UserProfile> userProfilePage = userProfileRepository.findAll(PageRequest.of(randomIndex, 1));
                    UserProfile userProfile = null;
                    if (userProfilePage.hasContent()) {
                        userProfile = userProfilePage.getContent().get(0);
                    }
                    return userProfile;
                }
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
