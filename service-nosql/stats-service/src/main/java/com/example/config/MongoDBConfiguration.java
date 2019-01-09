package com.example.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

public class MongoDBConfiguration extends AbstractReactiveMongoConfiguration {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final String DATABASE = "user_profile_stats";

    @Bean
    public MongoClient reactiveMongoClient() {
        MongoClientSettings.builder().credential(
                MongoCredential.createCredential(
                        USERNAME,
                        DATABASE,
                        PASSWORD.toCharArray()
                )
        );
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return DATABASE;
    }
}
