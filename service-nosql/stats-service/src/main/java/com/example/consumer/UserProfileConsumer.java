package com.example.consumer;


import com.example.mapper.UserProfileStatsMapper;
import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;
import com.example.service.UserProfileStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserProfileConsumer {

    @Autowired
    private UserProfileStatsService userProfileStatsService;

    @KafkaListener(topics = "${spring.kafka.topic.user-profile}")
    public void process(UserProfileStatsRequest userProfileStatsRequest) {
        UserProfileStats userProfileStats = UserProfileStatsMapper.toModel(userProfileStatsRequest);
        Mono u = userProfileStatsService.save(userProfileStats);
        u.subscribe(f -> {
            System.out.println(f.toString());
        });
    }

}
