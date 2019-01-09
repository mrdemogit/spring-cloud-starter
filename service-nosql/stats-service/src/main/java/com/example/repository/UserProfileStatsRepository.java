package com.example.repository;

import com.example.model.UserProfileStats;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileStatsRepository extends ReactiveMongoRepository<UserProfileStats, String> {

}
