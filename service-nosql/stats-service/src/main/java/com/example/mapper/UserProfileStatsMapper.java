package com.example.mapper;

import com.example.model.UserProfileStats;
import com.example.request.UserProfileStatsRequest;
import org.joda.time.DateTime;

import java.util.Date;


public class UserProfileStatsMapper {

    public static UserProfileStats toModel(UserProfileStatsRequest userProfileStatsRequest) {
        UserProfileStats userProfileStats = new UserProfileStats();
        userProfileStats.setUserProfileId(userProfileStatsRequest.getUserProfileId());
        userProfileStats.setDate(resetMinutesSecondsMillis(userProfileStatsRequest.getDate()));
        return userProfileStats;
    }

    public static Date resetMinutesSecondsMillis(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime.withMinuteOfHour(0);
        dateTime.withSecondOfMinute(0);
        dateTime.withMillisOfSecond(0);
        return dateTime.toDate();
    }
}
