package com.example.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

@Configuration
public class MongoDBConfiguration extends AbstractReactiveMongoConfiguration {

    @Value("${mongodb.username}")
    private String USERNAME;

    @Value("${mongodb.password}")
    private String PASSWORD;

    @Value("${mongodb.database}")
    private String DATABASE;

    @Override
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
