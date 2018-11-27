package com.example.mapper;

import com.example.model.UserProfile;
import com.example.request.UserProfileRequest;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfile toModel(UserProfileRequest userProfileRequest){
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(userProfileRequest.getFirstName());
        userProfile.setLastName(userProfileRequest.getLastName());
        return userProfile;
    }
}
