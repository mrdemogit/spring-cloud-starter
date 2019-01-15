package com.example.service;

import com.example.model.UserProfile;
import com.example.producer.UserProfileProducer;
import com.example.producer.UserProfileStatsRequest;
import com.example.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Date;
import java.util.concurrent.Callable;

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
        return async(() -> userProfileRepository
                .findById(id)
                .orElse(null)
        );
    }

    @Override
    public Mono<UserProfile> findByRandom() {
        return asyncInTransaction(
                (status) -> {
                    Long countAll = userProfileRepository.count();
                    int randomIndex = (int) (Math.random() * countAll);
                    Page<UserProfile> userProfilePage = userProfileRepository.findAll(PageRequest.of(randomIndex, 1));
                    UserProfile userProfile = null;
                    if (userProfilePage.hasContent()) {
                        userProfile = userProfilePage.getContent().get(0);
                    }

                    return userProfile;
                });
    }

    public Mono<UserProfile> create(String firstName, String lastName) {
        return asyncInTransaction(
                (status) -> {
                    UserProfile userProfile = new UserProfile();
                    userProfile.setFirstName(firstName);
                    userProfile.setLastName(lastName);
                    return userProfileRepository.save(userProfile);
                }
        );
    }

    private <T> Mono<T> async(Callable<T> callable) {
        return Mono.fromCallable(callable).publishOn(jdbcScheduler);
    }

    private <T> Mono<T> asyncInTransaction(TransactionCallback<T> transactionCallback) {
        return async(() -> transactionTemplate.execute(transactionCallback));
    }

}
