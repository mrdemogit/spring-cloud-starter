package com.example.consumer;


import com.example.mapper.UserProfileStatsMapper;
import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;
import com.example.service.UserProfileStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class UserProfileConsumer {

    @Autowired
    UserProfileStatsService userProfileStatsService;

    @KafkaListener(topics = "user-profile-topic")
    public void process(UserProfileStatsRequest userProfileStatsRequest) {
        UserProfileStats userProfileStats = UserProfileStatsMapper.toModel(userProfileStatsRequest);
        userProfileStatsService.updateOrSaveRenderCount(userProfileStats);
    }

}
