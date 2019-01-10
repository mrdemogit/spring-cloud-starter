package com.example.mapper;

import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;
import org.joda.time.DateTime;

import java.util.Date;


public class UserProfileStatsMapper {

    public static UserProfileStats toModel(UserProfileStatsRequest userProfileStatsRequest) {
        UserProfileStats userProfileStats = new UserProfileStats();
        userProfileStats.setUserProfileId(userProfileStatsRequest.getUserProfileId());
        userProfileStats.setDate(userProfileStatsRequest.getDate());
        return userProfileStats;
    }

}
