package com.example.mapper;

import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;


public class UserProfileStatsMapper {

    public static UserProfileStats toModel(UserProfileStatsRequest userProfileStatsRequest) {
        UserProfileStats userProfileStats = new UserProfileStats();
        userProfileStats.setUserProfileId(userProfileStatsRequest.getUserProfileId());
        userProfileStats.setDate(userProfileStatsRequest.getDate());
        userProfileStats.setDevice(userProfileStatsRequest.getDevice());
        userProfileStats.setLocation(userProfileStatsRequest.getLocation());
        return userProfileStats;
    }

}
