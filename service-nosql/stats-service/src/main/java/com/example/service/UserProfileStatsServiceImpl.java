package com.example.service;

import com.example.model.UserProfileStats;
import com.example.repository.UserProfileStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserProfileStatsServiceImpl implements UserProfileStatsService {

    @Autowired
    private UserProfileStatsRepository userProfileStatsRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Mono<UserProfileStats> updateOrSaveRenderCount(UserProfileStats userProfileStatsRequest) {
        UserProfileStats userProfileStats = mongoTemplate.findOne(
            Query.query(
                Criteria
                    .where("date")
                    .is(userProfileStatsRequest.getDate())
                    .and("userProfileId")
                    .is(userProfileStatsRequest.getUserProfileId())
            ),
            UserProfileStats.class
        );

        if (userProfileStats == null) {
            userProfileStats = userProfileStatsRequest;
        }

        userProfileStats.setRenderCount(userProfileStats.getRenderCount() + 1);
        return userProfileStatsRepository.save(userProfileStats);
    }
}
