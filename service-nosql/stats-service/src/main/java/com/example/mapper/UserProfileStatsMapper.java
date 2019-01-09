package com.example.mapper;

import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;
import com.example.response.UserProfileStatsResponse;


public class UserProfileStatsMapper {

    public static UserProfileStatsResponse toResponse(UserProfileStats userProfile){
        UserProfileStatsResponse userProfileStatsResponse = new UserProfileStatsResponse();
        userProfileStatsResponse.setId(userProfile.getId());
        userProfileStatsResponse.setUserProfileId(userProfile.getUserProfileId());
        userProfileStatsResponse.setStatsDate(userProfile.getStatsDate());
        return userProfileStatsResponse;
    }

    public static UserProfileStats toModel(UserProfileStatsRequest userProfileStatsRequest){
        UserProfileStats userProfileStats = new UserProfileStats();
        userProfileStats.setUserProfileId(userProfileStatsRequest.getUserProfileId());
        userProfileStats.setStatsDate(userProfileStatsRequest.getStatsDate());
        return userProfileStats;
    }

}
