package com.example.producer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * TODO import API from stats-api and remove this class
 */

@ToString
@Getter
@Setter
public class UserProfileStatsRequest {

    private Long userProfileId;
    private Date date;
}
