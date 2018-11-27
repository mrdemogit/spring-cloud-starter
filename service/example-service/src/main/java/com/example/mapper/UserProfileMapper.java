package com.example.mapper;

import com.example.model.UserProfile;
import com.example.response.UserProfileResponse;


public class UserProfileMapper {

    public static UserProfileResponse toResponse(UserProfile userProfile){
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setFirstName(userProfile.getFirstName());
        userProfileResponse.setLastName(userProfile.getLastName());
        return userProfileResponse;
    }
}
