package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProfileProducerImpl implements UserProfileProducer{

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private KafkaTemplate<String, UserProfileStatsRequest> template;


    @Value("${spring.kafka.topic.user-profile}")
    private String TOPIC;

    @Override
    public void sendUserProfile(UserProfileStatsRequest userProfileMessage) {
        template.send(TOPIC, userProfileMessage);
    }
}
