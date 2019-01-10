package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileProducer {

    @Autowired
    private KafkaTemplate<String, String> template;
}
